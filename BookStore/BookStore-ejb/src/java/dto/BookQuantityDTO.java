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
public class BookQuantityDTO implements Serializable{

    private BookDTO bookdto;
    private int quantity;

    public BookQuantityDTO(int quantity, BookDTO bookdto) {
        this.quantity = quantity;
        this.bookdto = bookdto;
    }

    public BookDTO getBookdto() {
        return bookdto;
    }

    public void setBookdto(BookDTO bookdto) {
        this.bookdto = bookdto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
