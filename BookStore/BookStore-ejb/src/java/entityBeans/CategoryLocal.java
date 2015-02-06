/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import java.util.Collection;
import java.util.List;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author Phat Huy
 */
public interface CategoryLocal extends EJBLocalObject {

    Integer getId();

    //void setId(Integer id);

    String getName();

    void setName(String name);

    String getSortorder();

    void setSortorder(String sortorder);

    Collection getBookCollection();

    void setBookCollection(Collection bookCollection);

    Collection searchBooksByName(String keywords);

    Collection searchBooksByAuthorName(String keywords);

}
