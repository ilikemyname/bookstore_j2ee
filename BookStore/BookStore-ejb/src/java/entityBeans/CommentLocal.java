/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.util.Date;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Phat Huy
 */
public interface CommentLocal extends EJBLocalObject {

    Integer getId();

    //void setId(Integer id);

    int getRating();

    void setRating(int rating);

    String getContent();

    void setContent(String content);

    Date getCommentDate();

    void setCommentDate(Date commentDate);

    UsersLocal getUsers();

    void setUsers(UsersLocal users);

    BookLocal getBook();

    void setBook(BookLocal book);


}
