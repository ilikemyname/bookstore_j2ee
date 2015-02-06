/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import sessionBean.CustomerRemote;
import sessionBean.CustomerRemoteHome;

/**
 *
 * @author Phat Huy
 */
public class CallSessionBean {

    public static CustomerRemote lookupCustomerRemote() {
        try {
            Context c = new InitialContext();
            Object remote = c.lookup("java:comp/env/Customer");
            CustomerRemoteHome rv = (CustomerRemoteHome) PortableRemoteObject.narrow(remote, CustomerRemoteHome.class);
            return rv.create();
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        } catch (CreateException ce) {
            throw new RuntimeException(ce);
        } catch (RemoteException re) {
            throw new RuntimeException(re);
        }
    }
}
