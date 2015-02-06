/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Phat Huy
 */
public class BookCommentsDTO implements Serializable {

    private BookDTO bookdto;
    private ArrayList commentsList;

    public BookCommentsDTO(BookDTO bookdto, ArrayList commentsList) {
        this.bookdto = bookdto;
        this.commentsList = commentsList;
    }

    public BookDTO getBookdto() {
        return bookdto;
    }

    public void setBookdto(BookDTO bookdto) {
        this.bookdto = bookdto;
    }

    public ArrayList getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(ArrayList commentsList) {
        this.commentsList = commentsList;
    }
}
