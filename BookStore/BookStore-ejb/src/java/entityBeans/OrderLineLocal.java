/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import javax.ejb.EJBLocalObject;

/**
 *
 * @author Phat Huy
 */
public interface OrderLineLocal extends EJBLocalObject {

    Integer getId();

    //void setId(Integer id);

    double getPrice();

    void setPrice(double price);

    int getQuantity();

    void setQuantity(int quantity);

    OrdersLocal getOrders();

    void setOrders(OrdersLocal orders);

    BookLocal getBook();

    void setBook(BookLocal book);


}
