/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import dto.OrderLineDTO;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Phat Huy
 */
public interface OrderLineLocalHome extends EJBLocalHome {

    entityBeans.OrderLineLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
    entityBeans.OrderLineLocal create(OrderLineDTO orderLineDTO)  throws CreateException;

    Collection findById(Integer id) throws FinderException;

    Collection findByPrice(double price) throws FinderException;

    Collection findByQuantity(int quantity) throws FinderException;

}
