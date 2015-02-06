/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Phat Huy
 */
public class OrderLineDTO implements Serializable {

    private int id;
    private double price;
    private int quantity;
    private int bookid;
    private String bookTitle;

    public OrderLineDTO(int id, double price, int quantity, String bookTitle) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.bookTitle = bookTitle;
    }

    public OrderLineDTO(double price, int quantity, int bookid) {
        this.price = price;
        this.quantity = quantity;
        this.bookid = bookid;
    }

    public OrderLineDTO(int id, double price, int quantity, int bookid) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.bookid = bookid;
    }

    public OrderLineDTO(int bookid) {
        this.bookid = bookid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookTitle() {
        return bookTitle;
    }
    
}
