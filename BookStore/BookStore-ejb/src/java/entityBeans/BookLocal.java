/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.util.Collection;
import java.util.Date;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Phat Huy
 */
public interface BookLocal extends EJBLocalObject {

    Integer getId();

    //void setId(Integer id);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    String getAuthor();

    void setAuthor(String author);

    String getPublisher();

    void setPublisher(String publisher);

    Date getPublishdate();

    void setPublishdate(Date publishdate);

    double getPrice();

    void setPrice(double price);

    String getPhoto();

    void setPhoto(String photo);

    int getRatingcount();

    void setRatingcount(int ratingcount);

    int getRatingvalue();

    void setRatingvalue(int ratingvalue);

    Collection getCommentCollection();

    void setCommentCollection(Collection commentCollection);

    CategoryLocal getCategory();

    void setCategory(CategoryLocal category);

    Collection getOrderLineCollection();

    void setOrderLineCollection(Collection orderLineCollection);


}
