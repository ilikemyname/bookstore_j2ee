/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Phat Huy
 */
public class CommentDTO implements Serializable {

    private int id;
    private int rating;
    private String content;
    private Date commentDate;
    //private int userName;
    private String loginName;

    public CommentDTO(int rating, String content, Date commentDate) {
        this.rating = rating;
        this.content = content;
        this.commentDate = commentDate;
    }

    public CommentDTO(int id, int rating, String content, Date commentDate, String loginName) {
        this.id = id;
        this.rating = rating;
        this.content = content;
        this.commentDate = commentDate;
        this.loginName = loginName;
    }

    public CommentDTO(int rating, String content, Date commentDate, String loginName) {
        this.rating = rating;
        this.content = content;
        this.commentDate = commentDate;
        this.loginName = loginName;
    }

    public CommentDTO(int id, int rating, String content, Date commentDate) {
        this.id = id;
        this.rating = rating;
        this.content = content;
        this.commentDate = commentDate;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLoginName() {
        return loginName;
    }
}
