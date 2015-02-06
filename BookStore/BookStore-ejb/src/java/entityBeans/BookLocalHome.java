/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Phat Huy
 */
public interface BookLocalHome extends EJBLocalHome {

    entityBeans.BookLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
    entityBeans.BookLocal create(java.lang.Integer key)  throws CreateException;

    entityBeans.BookLocal findById(Integer id) throws FinderException;

    Collection findByTitle(String title) throws FinderException;

    Collection findByDescription(String description) throws FinderException;

    Collection findByAuthor(String author) throws FinderException;

    Collection findByPublisher(String publisher) throws FinderException;

    Collection findByPrice(double price) throws FinderException;

    Collection findByPhoto(String photo) throws FinderException;

    Collection findByRatingcount(int ratingcount) throws FinderException;

    Collection findByRatingvalue(int ratingvalue) throws FinderException;

}
