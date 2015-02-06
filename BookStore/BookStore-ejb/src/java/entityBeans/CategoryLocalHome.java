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
public interface CategoryLocalHome extends EJBLocalHome {

    entityBeans.CategoryLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
    entityBeans.CategoryLocal create(java.lang.Integer key)  throws CreateException;

    CategoryLocal findById(Integer id) throws FinderException;

    entityBeans.CategoryLocal findByName(String name) throws FinderException;

    Collection findBySortorder(String sortorder) throws FinderException;

    Collection findAllCategories() throws FinderException;

}
