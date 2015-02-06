/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionBean;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 *
 * @author Phat Huy
 */
public interface CustomerRemoteHome extends EJBHome {

    sessionBean.CustomerRemote create()  throws CreateException, RemoteException;
    
}
