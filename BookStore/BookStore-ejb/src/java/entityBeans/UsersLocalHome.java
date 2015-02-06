/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import dto.UserDTO;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Phat Huy
 */
public interface UsersLocalHome extends EJBLocalHome {

    entityBeans.UsersLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
    entityBeans.UsersLocal create(UserDTO userdto)  throws CreateException;

    Collection findById(Integer id) throws FinderException;

    entityBeans.UsersLocal findByLoginname(String loginname) throws FinderException;

    entityBeans.UsersLocal findByEmail(String email) throws FinderException;

    Collection findByPassword(String password) throws FinderException;

    Collection findByPhone(String phone) throws FinderException;

    Collection findByFullname(String fullname) throws FinderException;

}
