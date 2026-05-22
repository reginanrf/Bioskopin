/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author regina
 */
public class BookingFnBDetail {
    private int idDetailFnb;
    private FnBItem fnbItem;
    private int quantity;
    private double subtotalFnb;

    public BookingFnBDetail() {
    }

    public BookingFnBDetail(int idDetailFnb,
                            FnBItem fnbItem,
                            int quantity,
                            double subtotalFnb) {

        this.idDetailFnb = idDetailFnb;
        this.fnbItem = fnbItem;
        this.quantity = quantity;
        this.subtotalFnb = subtotalFnb;
    }

    public int getIdDetailFnb() {
        return idDetailFnb;
    }

    public void setIdDetailFnb(int idDetailFnb) {
        this.idDetailFnb = idDetailFnb;
    }

    public FnBItem getFnbItem() {
        return fnbItem;
    }

    public void setFnbItem(FnBItem fnbItem) {
        this.fnbItem = fnbItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotalFnb() {
        return subtotalFnb;
    }

    public void setSubtotalFnb(double subtotalFnb) {
        this.subtotalFnb = subtotalFnb;
    }
}
