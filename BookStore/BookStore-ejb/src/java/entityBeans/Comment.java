/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import dto.CommentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public abstract class Comment implements EntityBean {

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
    public java.lang.Integer ejbCreate(CommentDTO commentdto) throws CreateException {
        setId(getNewID());
        setRating(commentdto.getRating());
        setContent(commentdto.getContent());
        setCommentDate(commentdto.getCommentDate());

        return null;
    }

    public void ejbPostCreate(CommentDTO commentdto) {
        // TODO populate relationships here if appropriate
    }

    private Integer getNewID() {
        Integer id = null;
        try {
            Connection connection = getBookstoredb().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select max(id) from comment");
            ResultSet rs = pstm.executeQuery();
            id = new Integer(1);
            if (rs.next()) {
                id = new Integer(rs.getInt(1) + 1);
            }
            //return id;
        } catch (NamingException be) {
        } catch (SQLException se) {
        }
        return id;
    }

    private DataSource getBookstoredb() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/bookstoredb");
    }

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract int getRating();

    public abstract void setRating(int rating);

    public abstract String getContent();

    public abstract void setContent(String content);

    public abstract Date getCommentDate();

    public abstract void setCommentDate(Date commentDate);

    public abstract UsersLocal getUsers();

    public abstract void setUsers(UsersLocal users);

    public abstract BookLocal getBook();

    public abstract void setBook(BookLocal book);
}
