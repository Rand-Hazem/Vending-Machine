/**
 * Using to represent vending machine products
 */
package com.machine.component;

public class Product {
    private String name;
    private long price;

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return product price in cents
     */
    public long getPrice() {
        return price;
    }

    /**
     * @param price item price in cents
     */
    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=$" + price / 100.0 +
                '}';
    }
}
