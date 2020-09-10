/**
 * Represent returned result from vending machine to user
 */
package com.machine.component;

import com.machine.api.Money;

import java.util.List;
import java.util.stream.Collectors;

public class Bucket {

    private Product product;
    private List<Money> monies;
    private UserCard card;

    public Bucket(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Money> getMonies() {
        return monies;
    }

    public void setMonies(List<Money> monies) {
        this.monies = monies;
    }

    public UserCard getCard() {
        return card;
    }

    public void setCard(UserCard card) {
        this.card = card;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bucket____\n");
        builder.append(product.toString());
        if (monies != null) {
            String str = monies.stream()
                    .collect(Collectors
                            .groupingBy(m -> String.format("$%.2f", m.getValue() / 100.0), Collectors.counting()))
                    .toString();
            builder.append("\nremain money : ");
            builder.append(str);

        }
        if (card != null) {
            builder.append("\ncard: " + card.hashCode());
        }
        return builder.toString();
    }
}
