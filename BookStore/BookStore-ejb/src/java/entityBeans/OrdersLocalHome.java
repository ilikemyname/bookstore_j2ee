/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import dto.OrderDTO;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Phat Huy
 */
public interface OrdersLocalHome extends EJBLocalHome {

    entityBeans.OrdersLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
    entityBeans.OrdersLocal create(OrderDTO orderdto)  throws CreateException;

    Collection findById(Integer id) throws FinderException;

    Collection findByTotalAmount(double totalAmount) throws FinderException;

    Collection findByShippingAddress(String shippingAddress) throws FinderException;

}
