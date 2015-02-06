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
public interface OrdersLocal extends EJBLocalObject {

    Integer getId();

    //void setId(Integer id);

    Date getOrderDate();

    void setOrderDate(Date orderDate);

    double getTotalAmount();

    void setTotalAmount(double totalAmount);

    String getShippingAddress();

    void setShippingAddress(String shippingAddress);

    UsersLocal getUsers();

    void setUsers(UsersLocal users);

    Collection getOrderLineCollection();

    void setOrderLineCollection(Collection orderLineCollection);


}
