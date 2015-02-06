/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import dto.BookCommentsDTO;
import dto.BookDTO;
import dto.BookQuantityDTO;
import dto.CommentDTO;
import dto.OrderAndOrderLineDTO;
import dto.OrderLineDTO;
import dto.UserDTO;
import java.rmi.RemoteException;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
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
public class Main {

    private static CustomerRemote cr;
    private static GregorianCalendar currentTime = new GregorianCalendar();
    private static long timeInMillis = currentTime.getTimeInMillis();
    //private static Date today = new Date(timeInMillis);
    private static Date today = Calendar.getInstance().getTime();

    public static void main(String[] args) throws RemoteException, NamingException, CreateException, FinderException, SQLException {

        cr = lookupCustomerRemote();
        System.out.println("|------>TEST REGISTER<------|");
        testRegisterFunc();

        System.out.println("\n\n-------->TEST LOGIN<---------");
        testLoginFunc();

        System.out.println("\n\n-------->TEST EDIT<----------");
        testEditFunc();

        System.out.println("\n\n-------->TEST BROWSE BOOKS<--------");
        testBrowseFunc();


        System.out.println("\n\n--------->TEST SEARCH BOOKS<---------");
        testSearchBooks();

        System.out.println("\n\n--------->TEST VIEW BOOK DETAIL<----------");
        testViewBookDetail();

        System.out.println("\n\n--------->TEST ADD COMMENT<------------");
        testAddComment();

        System.out.println("\n\n--------->TEST ADD PRODUCT TO SHOPPING CART & VIEW CART<---------");
        testShoppingCart();

        System.out.println("\n\n--------->TEST CONFIRM AN ORDER<---------");
        testOrderConfirmation();

        System.out.println("\n\n--------->TEST VIEW PAST ORDERS<---------");
        testViewPastOrders();

    }

    private static CustomerRemote lookupCustomerRemote() throws NamingException, CreateException, RemoteException {
        Context c = new InitialContext();
        Object remote = c.lookup("java:comp/env/Customer");
        CustomerRemoteHome rv = (CustomerRemoteHome) PortableRemoteObject.narrow(remote, CustomerRemoteHome.class);
        return rv.create();
    }

    private static void testRegisterFunc() throws RemoteException, FinderException {
        UserDTO user1 = new UserDTO("user1", "user1@rmit.edu.vn", "user1", "0916938970", "user1", today, today);
        System.out.println("Register with login name has not been used: " + cr.register(user1));
        UserDTO user2 = new UserDTO("asdf", "asdf@rmit.edu.vn", "asdf", "0916938970", "asdf", today, today);
        System.out.println("Register with login name have been registered: " + cr.register(user2));
    }

    private static void testLoginFunc() throws RemoteException, FinderException {
        String validLoginName = "asdf", validPassword = "2103", invalidPassword = "xxxxxx";
        String fakeLoginName = "anonymous", fakePassword = "anonymous";
        System.out.println("Register user login with true login name '" + validLoginName
                + "' and true password '" + validPassword + "' : " + (cr.login(validLoginName, validPassword) != null));

        System.out.println("Registered user login with true login name '" + validLoginName
                + "' and wrong password '" + invalidPassword + "' : " + (cr.login(validLoginName, invalidPassword) != null));

        System.out.println("Registered user login with wrong login name '" + fakeLoginName
                + "' and true password '" + validPassword + "' : " + (cr.login(fakeLoginName, validPassword) != null));

        System.out.println("Registered user login with wrong login name '" + fakeLoginName
                + "' and wrong password '" + invalidPassword + "' : " + (cr.login(fakeLoginName, invalidPassword) != null));

        System.out.println("Anonymous user tries to login: " + (cr.login(fakeLoginName, fakePassword) != null));
    }

    private static void testEditFunc() throws RemoteException {
        String validLoginName = "user1", validPassword = "user1";
        String fakeLoginName = "anonymous", fakePassword = "anonymous";
        String newEmail = "newemail@yahoo.com", newPassword = "myPassword", newPhone = "0909999999", newFullName = "barend scholtus";
        System.out.println("Registered user edited account: " + (cr.edit(validLoginName, validPassword, newEmail, newPassword, newPhone, newFullName, today) != null));
        System.out.println("User wannna edit without success login: " + (cr.edit(fakeLoginName, fakePassword, newEmail, newPassword, newPhone, newFullName, today) != null));
    }

    private static void testBrowseFunc() throws RemoteException {
        String existedCategoryName = "programming";
        String unexistedCategoryName = "cartoon";
        int existedCategoryID = 1;
        int unexistedCategoryID = 10;

        TreeSet c = cr.browseBook(existedCategoryID);
        System.out.println("Browse book with existed category '" + existedCategoryName + "': " + (c != null));
        System.out.println("'" + existedCategoryName + "' has " + c.size() + " book(s)");
        displayBooks(c);

        System.out.println();

        c = cr.browseBook(unexistedCategoryID);
        System.out.println("Browse book with unexisted category '" + unexistedCategoryName + "': " + (c != null));
    }

    private static void displayBooks(TreeSet c) {
        System.out.println("\t---------Display book by this category---------");
//        ArrayList a = new ArrayList(c);
//        for (int i = 0; i < a.size(); i++) {
//            BookDTO bookdto = (BookDTO) a.get(i);
//            System.out.println("\tBook No " + (i + 1) + "\tTitle: '" + bookdto.getTitle() + "'");
//        }
        Iterator it = c.iterator();
        int i = 0;
        while (it.hasNext()) {
            BookDTO dto = (BookDTO)it.next();
            System.out.println("\tBook No " + (i + 1) + "\tTitle: '" + dto.getTitle() + "'");
            i++;
        }
    }

    private static void testSearchBooks() throws RemoteException {
        int defaultCategory = 0;
        String bookNameKeyword = "head first";
        String comicCategory = "comic";
        String validAuthorKeyword = "gosho aoyama";
        String programmingCategory = "programming";
        String invalidCategory = "cartoon";
        int validCagID = 1;
        int invalidCagID = 10;

        TreeSet ts = cr.searchBookByTitle(bookNameKeyword, defaultCategory);
        System.out.println("Search matching books in all category: " + (ts != null));
        if (ts != null) {
            if (ts.isEmpty()) {
                System.out.println("keywords '" + bookNameKeyword + "' has total of " + ts.size() + " books in all categories");
            } else {
                System.out.println("keywords '" + bookNameKeyword + "' has total of " + ts.size() + " books in all categories");
            }
        }
        System.out.println();

        ts = cr.searchBookByTitle(bookNameKeyword, validCagID);
        System.out.println("Search matching books in category '" + comicCategory + "': " + (ts != null));
        if (ts != null) {
            if (ts.isEmpty()) {
                System.out.println("keywords '" + bookNameKeyword + "' has total of " + ts.size() + " book(s) in category '" + comicCategory + "'");
            } else {
                System.out.println("keywords '" + bookNameKeyword + "' has total of " + ts.size() + " book(s) in category '" + comicCategory + "'");
            }
        }
        System.out.println();

        ts = cr.searchBookByTitle(bookNameKeyword, invalidCagID);
        System.out.println("Search matching books with unexisted category '" + comicCategory + "': " + (ts != null));
        System.out.println();

        ts = cr.searchBookByAuthorName(validAuthorKeyword, defaultCategory);
        System.out.println("Search books by author name '" + validAuthorKeyword + "' in all categories: " + (ts != null));
        if (ts != null) {
            if (ts.isEmpty()) {
                System.out.println("keywords '" + validAuthorKeyword + "' has total of " + ts.size() + " book(s) in all categories");
            } else {
                System.out.println("keywords '" + validAuthorKeyword + "' has total of " + ts.size() + " book(s) in all categories");
            }
        }
        System.out.println();

        ts = cr.searchBookByAuthorName(validAuthorKeyword, validCagID);
        System.out.println("Search books by author name '" + validAuthorKeyword + "' in category '" + programmingCategory + "': " + (ts != null));
        if (ts != null) {
            if (ts.isEmpty()) {
                System.out.println("keywords '" + validAuthorKeyword + "' has total of " + ts.size() + " book(s) in categories '" + programmingCategory + "'");
            } else {
                System.out.println("keywords '" + validAuthorKeyword + "' has total of " + ts.size() + " book(s) in all categories '" + programmingCategory + "'");
            }
        }
        System.out.println();

        ts = cr.searchBookByAuthorName(validAuthorKeyword, validCagID);
        System.out.println("Search books by author name '" + validAuthorKeyword + "' with unexisted category: " + (ts != null));
    }

    private static void testViewBookDetail() throws RemoteException, FinderException {
        int validBookID = 4; // head first design patterns
        int invalidBookID = 20; // 18 is max id in database

        BookCommentsDTO bookCommentDTO = cr.viewBookDetail(validBookID);
        System.out.println("View book detail with valid id '4'" + (bookCommentDTO != null));
        displayBookDetail(bookCommentDTO);

        System.out.println();

        bookCommentDTO = cr.viewBookDetail(invalidBookID);
        System.out.println("View book detail with invalid id '20'" + (bookCommentDTO != null));
    }

    private static void displayBookDetail(BookCommentsDTO bookCommentDTO) throws RemoteException, FinderException {
        if (bookCommentDTO != null) {
            BookDTO bookdto = bookCommentDTO.getBookdto();
            System.out.println("\t\t+ Title: " + bookdto.getTitle());
            System.out.println("\t\t+ Description: " + bookdto.getDescription());
            System.out.println("\t\t+ Author name: " + bookdto.getAuthorName());
            System.out.println("\t\t+ Publisher name: " + bookdto.getPublisherName());
            System.out.println("\t\t+ Publishing date: " + bookdto.getPublishingDate());
            System.out.println("\t\t+ Unit price: " + bookdto.getPrice());
            System.out.println("\t\t+ Total rating count: " + bookdto.getRatingCount());
            System.out.println("\t\t+ Total rating value: " + bookdto.getRatingValue());
            System.out.println("\t\t+ Average rating value: "
                    + ((double) bookdto.getRatingValue() / bookdto.getRatingCount()));
            System.out.println("\t\t+ Category name: " + bookdto.getCategoryName());
            ArrayList CommentsList = bookCommentDTO.getCommentsList();
            int CommentsListsize = CommentsList.size();
            int i = 0, count = 0;
            if (CommentsListsize != 0) {
                System.out.println("\t\t+ There" + (CommentsListsize > 1 ? " are " + CommentsListsize + " comments " : " is " + CommentsListsize + " comment ") + "for book:" + bookdto.getTitle());
                for (i = CommentsListsize - 1, count = 1; i >= 0; i--, count++) { // To get newest comments
                    CommentDTO commentdto = (CommentDTO) CommentsList.get(i);
                    System.out.println("\t\t\t@ Comment " + count + ":");
                    System.out.println("\t\t\t\t* User id: " + cr.getUserID(commentdto.getLoginName()));
                    System.out.println("\t\t\t\t* Login name: " + commentdto.getLoginName());
                    System.out.println("\t\t\t\t* Rating: " + commentdto.getRating());
                    System.out.println("\t\t\t\t* Content: " + commentdto.getContent());
                    System.out.println("\t\t\t\t* Comment date: " + commentdto.getCommentDate());
                }
            } else {
                System.out.println("\t\t+ This book has not been commented.");
            }
        }
    }

    private static void testAddComment() throws RemoteException, FinderException {
        int valid_BookID = 6; // head first rails
        int invalid_BookID = 20;
        int valid_UserID = 2; // iphathuy
        int invalid_UserID = 4;
        int rating = 5;
        String content = "Fantastic book! I never see.";

        boolean result = cr.addComment(valid_BookID, valid_UserID, rating, content);
        System.out.println("Test comment is added to book success by user who hasn't ever commented on: " + result);
        System.out.println("Registered user id: " + valid_UserID);
        System.out.println("haven't ever written a comment for valid book id : " + valid_BookID);
        System.out.println("Operation Result: " + result);
        System.out.println("-----Display book detail after a comment is added------ ");
        displayBookDetail(cr.viewBookDetail(valid_BookID));

        System.out.println();

        result = cr.addComment(valid_BookID, valid_UserID, rating, content);
        System.out.println("Test comment is failed add to book by user who has commented on: " + result);
        System.out.println("Registered user id: " + valid_UserID);
        System.out.println("Wanna write more comments for book id: " + valid_BookID);
        System.out.println("Operation result: " + result);

        result = cr.addComment(invalid_BookID, valid_UserID, rating, content);
        System.out.println("Adding comment for book with invalid id(" + invalid_BookID
                + ") by user with valid id(" + valid_UserID + "): " + result);

        result = cr.addComment(valid_BookID, invalid_UserID, rating, content);
        System.out.println("Adding comment for book with valid id(" + valid_BookID
                + ") by user with invalid id(" + invalid_UserID + "): " + result);

        result = cr.addComment(invalid_BookID, invalid_UserID, rating, content);
        System.out.println("Adding comment for book with invalid id(" + invalid_BookID
                + ") by user with invalid id(" + invalid_UserID + "): " + result);
    }

    private static void testShoppingCart() throws RemoteException {
        int bookID1 = 1, quantity1 = 2;
        int bookID2 = 2, quantity2 = 2;
        int invalidBookID = 20;

        // add 2 books to shopping cart and view cart after.
        boolean result = cr.addBookToCart(bookID1, quantity1);
        System.out.println("Valid book ID '" + bookID1 + "' was stored in cart: " + result);

        result = cr.addBookToCart(bookID2, quantity2);
        System.out.println("Valid book ID '" + bookID2 + "' was stored in cart: " + result);
        viewCart(cr.getShoppingCart());

        result = cr.addBookToCart(invalidBookID, quantity1);
        System.out.println("Can invalid ID '" + invalidBookID + "' be added to cart? - " + result);
        viewCart(cr.getShoppingCart());
        //update quantity of book id 1 to become 4
        quantity1 = 4;
        System.out.println("Quantity of book ID " + bookID1 + " is updated to " + quantity1
                + ": " + cr.updateBookQuantityInCart(bookID1, quantity1));
        System.out.println("After updating quantity for books: ");
        viewCart(cr.getShoppingCart());

        System.out.println();
    }

    private static void viewCart(ArrayList shoppingCart) {
        System.out.println("\n*******Viewing Shopping Cart********");
        double totalAmount = 0;
        for (int i = 0; i < shoppingCart.size(); i++) {
            BookQuantityDTO bookQDTO = (BookQuantityDTO) shoppingCart.get(i);
            BookDTO bookdto = bookQDTO.getBookdto();
            System.out.println("\t\tBook Detail: '" + i + "'");
            System.out.println("\tTitle - " + bookdto.getTitle()
                    + "\tAuthor - " + bookdto.getAuthorName()
                    + "\n\tPrice - $" + bookdto.getPrice()
                    + "\n\tQuantity - " + bookQDTO.getQuantity());
            double subTotal = bookQDTO.getQuantity() * bookdto.getPrice();
            System.out.println("\tSubtotal of book '" + i + "': $" + subTotal);
            totalAmount += subTotal;
        }
        System.out.println("\t----------------------------------------"
                + "\n\tTotal Amount: $" + totalAmount);
    }

    private static void testOrderConfirmation() throws RemoteException, NamingException, SQLException {
        String myAddress = "lo c28 khu dan cu 3e Phong phu Ward Binh Chanh District";
        int validUserID = 1;
        int invalidUserID = 11;
        System.out.println("Valid user id " + validUserID + "checkout & confirm: "
                + cr.confirmOrder(validUserID, myAddress));
        System.out.println("Invalid user id " + invalidUserID + " checkout & confirm: "
                + cr.confirmOrder(invalidUserID, myAddress));
    }

    private static void testViewPastOrders() throws RemoteException {
        int validUserID = 1;
        int invalidUserID = 11;
        ArrayList a = cr.viewPassOrders(validUserID);
        System.out.println("Registered user id '" + validUserID + "' view his/her pass orders: "
                + (a != null));
        showPassOrders(a);
        System.out.println();

        a = cr.viewPassOrders(invalidUserID);
        System.out.println("Anonymous user view his/her pass orders: "
                + (a != null));
    }

    private static void showPassOrders(ArrayList ordersList) {
        System.out.println("\n**********Show All Orders***********");
        for (int i = 0; i < ordersList.size(); i++) {
            OrderAndOrderLineDTO order = (OrderAndOrderLineDTO) ordersList.get(i);
            System.out.println("Order ID: '" + order.getOrderDTO().getId() + "'");
            System.out.println("Order Date: " + order.getOrderDTO().getOrderDate());
            System.out.println("Order Total Amount: " + order.getOrderDTO().getTotalAmount());
            System.out.println("Included Books such as: ");
            for (int q = 0; q < order.getOrderLinesList().size(); q++) {
                OrderLineDTO orderline = (OrderLineDTO) order.getOrderLinesList().get(q);
                System.out.println("Book ID: " + orderline.getBookid());
            }
            System.out.println();
        }
    }
}
