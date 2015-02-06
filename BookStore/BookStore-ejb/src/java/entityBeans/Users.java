/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Phat Huy
 */
public abstract class Users implements EntityBean {

    private EntityContext context;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click on the + sign on the left to edit the code.">
    // TODO Consider creating Transfer Object to encapsulate data
    // TODO Review finder methods
    /**
     * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
     */
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.EntityBean#ejbActivate()
     */
    public void ejbActivate() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbPassivate()
     */
    public void ejbPassivate() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */
    public void ejbRemove() {
    }

    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    public void unsetEntityContext() {
        context = null;
    }

    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
    }

    // </editor-fold>
    public java.lang.Integer ejbCreate(UserDTO userdto) throws CreateException, NamingException, SQLException {
        setId(getNewID());
        setLoginname(userdto.getLoginName());
        setEmail(userdto.getEmail());
        setPassword(userdto.getPassword());
        setPhone(userdto.getPhone());
        setFullname(userdto.getFullName());
        setBirthday(userdto.getBirthDate());
        setCreatedate(userdto.getCreateDate());
        return null;
    }

    public void ejbPostCreate(UserDTO userdto) {
    }

    private Integer getNewID() throws NamingException, SQLException{
        Connection connection = getBookstoredb().getConnection();
        PreparedStatement psmt = connection.prepareStatement("select max(id) from users");
        ResultSet rs = psmt.executeQuery();
        Integer id = new Integer(1);
        if(rs.next()){
            id = new Integer(rs.getInt(1) + 1);
        }
        return id;
    }

    private DataSource getBookstoredb() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/bookstoredb");
    }

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getLoginname();

    public abstract void setLoginname(String loginname);

    public abstract String getEmail();

    public abstract void setEmail(String email);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract String getPhone();

    public abstract void setPhone(String phone);

    public abstract String getFullname();

    public abstract void setFullname(String fullname);

    public abstract Date getBirthday();

    public abstract void setBirthday(Date birthday);

    public abstract Date getCreatedate();

    public abstract void setCreatedate(Date createdate);

    public abstract Collection getOrdersCollection();

    public abstract void setOrdersCollection(Collection ordersCollection);

    public abstract Collection getCommentCollection();

    public abstract void setCommentCollection(Collection commentCollection);

    
}
