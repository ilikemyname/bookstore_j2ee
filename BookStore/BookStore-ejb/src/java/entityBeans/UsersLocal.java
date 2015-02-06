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
public interface UsersLocal extends EJBLocalObject {

    Integer getId();

    //void setId(Integer id);

    String getLoginname();

    void setLoginname(String loginname);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getPhone();

    void setPhone(String phone);

    String getFullname();

    void setFullname(String fullname);

    Date getBirthday();

    void setBirthday(Date birthday);

    Date getCreatedate();

    void setCreatedate(Date createdate);

    Collection getOrdersCollection();

    void setOrdersCollection(Collection ordersCollection);

    Collection getCommentCollection();

    void setCommentCollection(Collection commentCollection);

//    void setBirthday(Calendar birthday);


}
