/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import dto.CommentDTO;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Phat Huy
 */
public interface CommentLocalHome extends EJBLocalHome {

    entityBeans.CommentLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
    entityBeans.CommentLocal create(CommentDTO commentdto)  throws CreateException;

    Collection findById(Integer id) throws FinderException;

    Collection findByRating(int rating) throws FinderException;

    Collection findByContent(String content) throws FinderException;

}
