package com.machine;

import com.machine.api.CardService;
import com.machine.api.Money;
import com.machine.api.VendingMachine;
import com.machine.component.*;
import com.machine.enums.Coin;
import com.machine.enums.Note;
import com.machine.exceptions.NotSufficientChangeException;
import com.machine.exceptions.ProductNotFoundException;
import com.machine.exceptions.SoldOutException;
import com.machine.factory.CardServiceFactory;

import java.util.stream.Collectors;
import java.util.*;

public class VendingMachineImpl implements VendingMachine {

    private Inventory<Integer, Integer> slotInventory; // slot key, product count
    private Inventory<Money, Integer> moneyInventory;
    private Map<Integer, Slot> slots;
    private Product selectedProduct;
    private int selectedSlotKey;
    private long insertedMoney;
    private UserCard currentUserCard;

    public VendingMachineImpl() {
        slotInventory = new Inventory<>();
        moneyInventory = new Inventory<>();
        slots = new HashMap<>();
        initialize();
    }

    /**
     * Set up vending machine
     */
    private void initialize() {
        // Define products
        List<Product> products = Arrays
                .stream(new String[]{"Snickers", "Twix", "kitKat", "M&M's", "Oreos",
                        "Corn Nuts", "Doritos", "Cookies", "Chewing gum", "kitKat"})
                .map(str -> {
                    Product p = new Product(str, (long) (Math.random() * 100));
                    return p;
                })
                .collect(Collectors.toList());
        // Map slot number to products, add to inventory
        for (int i = 0, j = 0; i < 5 * 5; i++, j = i / 5) {
            Product p = j < products.size() ? products.get(j) : null;
            Slot slot = new Slot(i + 1, p);
            slotInventory.putItem(i + 1, (int) (Math.random() * 15));
            slots.put(i + 1, slot);
        }
        // Fill Coins Inventory
        for (Coin c : Coin.values()) {
            moneyInventory.putItem(c, (int) (Math.random() * 100));
        }
        // Fill Note Inventory
        for (Note n : Note.values()) {
            moneyInventory.putItem(n, (int) (Math.random() * 100));
        }
    }

    /**
     * @param key slot number user select fron keypad
     * @return product proce in coins
     * @throws ProductNotFoundException if no such slot, or not product on the slot
     * @throws SoldOutException         if product sold out
     */
    @Override
    public long selectItem(int key) throws ProductNotFoundException, SoldOutException {
        Slot slot = slots.getOrDefault(key, null);
        if (slot == null || slot.getProduct() == null) {
            throw new ProductNotFoundException("No available " + key + " slot");
        } else if (slotInventory.getItem(key) < 1) {
            throw new SoldOutException("Solid out");
        }
        selectedSlotKey = key;
        selectedProduct = slot.getProduct();
        long price = selectedProduct.getPrice();
        displayMessage(String.format("selected product %s $%.2f", selectedProduct.getName(), price / 100.0));
        return price;
    }

    @Override
    public void insertMoney(Money money) {
        incrementMoneyInventory(money);
        displayMessage(String.format("Total: $%.2f", money.getValue() / 100.0));
        monitorMoney();
    }

    @Override
    public void insertCard(UserCard userCard) {
        displayMessage("Fetch card num " + userCard.hashCode());
        currentUserCard = userCard;
        monitorMoney();
    }

    private void incrementMoneyInventory(Money money) {
        int x = 1 + moneyInventory.getInventory().getOrDefault(money, 0);
        moneyInventory.putItem(money, x);
        insertedMoney += money.getValue();
    }

    private void decrementMoneyInventory(Money money) {
        int x = moneyInventory.getInventory().getOrDefault(money, 0) - 1;
        moneyInventory.putItem(money, x);
    }

    /**
     * decrement product count in the slot
     *
     * @param slotKey
     */
    private void decrementSlotInventory(int slotKey) {
        slotInventory.putItem(slotKey, slotInventory.getItem(slotKey) - 1);
    }

    /**
     * Monitor money inserted by user, if enough if will dispenses  product
     *
     * @throws ProductNotFoundException if user insert money without selected a slot 'product'
     */
    private void monitorMoney() throws ProductNotFoundException {
        if (selectedProduct == null) {
            throw new ProductNotFoundException("Please select slot");
        }
        if (selectedProduct.getPrice() <= insertedMoney
                || currentUserCard != null) {
            getProductAndChange();
        }
    }

    private void getProductAndChange() {
        Bucket bucket = new Bucket(selectedProduct);
        if (isCardPayment()) {
            CardService cardService = CardServiceFactory.getCardService();
            cardService.deductAmount(currentUserCard, selectedProduct.getPrice());
            bucket.setCard(currentUserCard);
        } else {
            evaluateChange(bucket, insertedMoney - selectedProduct.getPrice());
        }
        decrementSlotInventory(selectedSlotKey);
        displayMessage(String.format("\nSuccess, your bucket :\n%s \n", bucket.toString()));
        reset();
    }

    /**
     * determine if customer insert card to pay
     *
     * @return
     */
    private boolean isCardPayment() {
        return currentUserCard != null;
    }

    /**
     * Find sufficient chage for customer. if the amount value less than 10 cents,
     * it will be ignored and not returned to customer
     * Example: products has $0.53 price, so $0.03 will be ignored
     *
     * @param bucket resulted bucket for customer
     * @param amount changed value need to find suitable representation form inventory
     * @throws NotSufficientChangeException if the machine couldn't evaluate sufficient change
     */
    private void evaluateChange(Bucket bucket, long amount) throws NotSufficientChangeException {
        List<Money> change = new ArrayList<>();
        while (amount >= 10) {
            if (Note.TWENTY_DOLLAR.getValue() <= amount
                    && moneyInventory.getItem(Note.TWENTY_DOLLAR) > 0) {
                amount -= Note.TWENTY_DOLLAR.getValue();
                change.add(Note.TWENTY_DOLLAR);
            } else if (Coin.ONE_DOLLAR.getValue() <= amount
                    && moneyInventory.getItem(Coin.ONE_DOLLAR) > 0) {
                amount -= Coin.ONE_DOLLAR.getValue();
                change.add(Coin.ONE_DOLLAR);
            } else if (Coin.FIFTY_CENT.getValue() <= amount
                    && moneyInventory.getItem(Coin.FIFTY_CENT) > 0) {
                amount -= Coin.FIFTY_CENT.getValue();
                change.add(Coin.FIFTY_CENT);
            } else if (Coin.TWENTY_CENTS.getValue() <= amount
                    && moneyInventory.getItem(Coin.TWENTY_CENTS) > 0) {
                amount -= Coin.TWENTY_CENTS.getValue();
                change.add(Coin.TWENTY_CENTS);
            } else if (Coin.TEN_CENTS.getValue() <= amount
                    && moneyInventory.getItem(Coin.TEN_CENTS) > 0) {
                amount -= Coin.TEN_CENTS.getValue();
                change.add(Coin.TEN_CENTS);
            } else if (amount >= 10) {
                throw new NotSufficientChangeException("Can't proceed your request, no enough change");
            }
        }
        bucket.setMonies(change);
        subChangedFromInventory(change);
    }

    /**
     * substract change money returent to user from money inventory
     *
     * @param change
     */
    private void subChangedFromInventory(List<Money> change) {
        for (Money m : change) {
            decrementMoneyInventory(m);
        }
    }

    @Override
    public void reset() {
        selectedSlotKey = -1;
        selectedProduct = null;
        insertedMoney = 0;
        currentUserCard = null;
    }

    private void displayMessage(String msg) {
        System.out.println(msg);
    }
}
