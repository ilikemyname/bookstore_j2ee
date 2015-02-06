/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.util.Collection;
import java.util.Date;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;

/**
 *
 * @author Phat Huy
 */
public abstract class Book implements EntityBean {

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
    
    
    public java.lang.Integer ejbCreate(java.lang.Integer key)  throws CreateException {
        if (key == null) {
            throw new CreateException("The field \"key\" must not be null");
        }
        
        // TODO add additional validation code, throw CreateException if data is not valid

        return null;
    }

    public void ejbPostCreate(java.lang.Integer key) {
        // TODO populate relationships here if appropriate
    }

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getTitle();

    public abstract void setTitle(String title);

    public abstract String getDescription();

    public abstract void setDescription(String description);

    public abstract String getAuthor();

    public abstract void setAuthor(String author);

    public abstract String getPublisher();

    public abstract void setPublisher(String publisher);

    public abstract Date getPublishdate();

    public abstract void setPublishdate(Date publishdate);

    public abstract double getPrice();

    public abstract void setPrice(double price);

    public abstract String getPhoto();

    public abstract void setPhoto(String photo);

    public abstract int getRatingcount();

    public abstract void setRatingcount(int ratingcount);

    public abstract int getRatingvalue();

    public abstract void setRatingvalue(int ratingvalue);

    public abstract Collection getCommentCollection();

    public abstract void setCommentCollection(Collection commentCollection);

    public abstract CategoryLocal getCategory();

    public abstract void setCategory(CategoryLocal category);

    public abstract Collection getOrderLineCollection();

    public abstract void setOrderLineCollection(Collection orderLineCollection);

}
