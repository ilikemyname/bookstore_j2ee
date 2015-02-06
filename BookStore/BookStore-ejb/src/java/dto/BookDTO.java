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
public class BookDTO implements Serializable, Comparable {

    private int id;
    private String title;
    private String description;
    private String authorName;
    private String publisherName;
    private Date publishingDate;
    private double price;
    private String photo;
    private int ratingCount;
    private int ratingValue;
    private String categoryName;

    public BookDTO(int id, String title, String description, String authorName, String publisherName, Date publishingDate, double price, String photo, int ratingCount, int ratingValue, String categoryName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publishingDate = publishingDate;
        this.price = price;
        this.photo = photo;
        this.ratingCount = ratingCount;
        this.ratingValue = ratingValue;
        this.categoryName = categoryName;
    }

    public BookDTO(int id, String title, String authorName, double price) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.price = price;
    }

    public BookDTO(String title, String description, String authorName, String publisherName, Date publishingDate, double price, String photo, int ratingCount, int ratingValue, String categoryName) {
        this.title = title;
        this.description = description;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publishingDate = publishingDate;
        this.price = price;
        this.photo = photo;
        this.ratingCount = ratingCount;
        this.ratingValue = ratingValue;
        this.categoryName = categoryName;
    }

    public BookDTO() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int compareTo(Object o) {
        if (getTitle().compareTo(((BookDTO) o).getTitle()) < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}