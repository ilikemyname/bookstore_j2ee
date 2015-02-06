/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import dto.BookCommentsDTO;
import dto.UserDTO;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import javax.naming.NamingException;

/**
 *
 * @author Phat Huy
 */
public interface CustomerRemote extends EJBObject {

    boolean register(UserDTO userdto) throws RemoteException, FinderException;

    UserDTO login(String loginName, String password) throws RemoteException, FinderException;

    UserDTO edit(String loginName, String password, String updatedEmail, String updatedPassword, String updatedPhone, String updatedFullName, Date today) throws RemoteException;

    List browseBooks(String categoryName) throws RemoteException;

    List searchBooks(String bookNameKeyword, String categoryName) throws RemoteException;

    List searchBooksByAuthorName(String authorKeyword, String defaultCategory) throws RemoteException;

    BookCommentsDTO viewBookDetail(int bookID) throws RemoteException;

    boolean addComment(int valid_BookID, int valid_UserID, int rating, String content) throws RemoteException;

    boolean updateBookQuantityInCart(int bookID, int quantity) throws RemoteException;

    boolean addBookToCart(int bookID, int quantity) throws RemoteException;

    ArrayList getShoppingCart() throws RemoteException;

    boolean confirmOrder(int userID, String myAddress) throws RemoteException, NamingException, SQLException;

    ArrayList viewPassOrders(int userid) throws RemoteException;

    int getUserID(String loginName) throws RemoteException, FinderException;

    boolean findExistLoginName(String loginName) throws RemoteException;

    boolean findExistEmail(String email) throws RemoteException;

    ArrayList getAllCategories() throws RemoteException;

    ArrayList browseBookByCagID(int cagID) throws RemoteException;

    ArrayList browseBooksInPage(ArrayList bookList, int pageNumber, int pageSize) throws RemoteException;

    TreeSet browseBook(int categoryID) throws RemoteException;

    int getBookAmountInCart() throws RemoteException;

    double getTotalPrice() throws RemoteException;

    boolean removeBookInCart(int bookID) throws RemoteException;

    TreeSet searchBookByTitle(String keyword, int categoryid) throws RemoteException;

    TreeSet searchBookByAuthorName(String keyword, int categoryid) throws RemoteException;

//    UserDTO edit(String loginName, String oldPass, String email, String password, String phone, String fullname, Calendar birthday);
    
}
