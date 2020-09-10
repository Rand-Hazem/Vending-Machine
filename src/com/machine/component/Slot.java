/**
 * Slot used to map slot-number with spesifec product
 */
package com.machine.component;

public class Slot {

    private int num;
    private Product product;

    public Slot(int num, Product product) {
        this.num = num;
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object obj) {
        return this.num == ((Slot) obj).num;
    }

}
