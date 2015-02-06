/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import dto.BookCommentsDTO;
import dto.BookDTO;
import dto.BookQuantityDTO;
import dto.CategoryDTO;
import dto.CommentDTO;
import dto.OrderAndOrderLineDTO;
import dto.OrderDTO;
import dto.OrderLineDTO;
import dto.UserDTO;
import entityBeans.BookLocal;
import entityBeans.BookLocalHome;
import entityBeans.CategoryLocal;
import entityBeans.CategoryLocalHome;
import entityBeans.CommentLocal;
import entityBeans.CommentLocalHome;
import entityBeans.OrderLineLocal;
import entityBeans.OrderLineLocalHome;
import entityBeans.OrdersLocal;
import entityBeans.OrdersLocalHome;
import entityBeans.UsersLocal;
import entityBeans.UsersLocalHome;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Phat Huy
 */
public class Customer implements SessionBean {

    private SessionContext context;
    UsersLocalHome userHome;
    UsersLocal userLocal;
    CategoryLocalHome categoryHome;
    BookLocalHome bookHome;
    private static ArrayList shoppingCart = new ArrayList();
    private GregorianCalendar currentTime = new GregorianCalendar();
    private long timeInMillis = currentTime.getTimeInMillis();
    private Date today = new Date(timeInMillis);

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">;
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
    }

    // </editor-fold>;
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
    }

    private UsersLocalHome lookupUsersLocal() {
        try {
            Context c = new InitialContext();
            UsersLocalHome rv = (UsersLocalHome) c.lookup("java:comp/env/Users");
            return rv;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private CategoryLocalHome lookupCategoryLocal() {
        try {
            Context c = new InitialContext();
            CategoryLocalHome rv = (CategoryLocalHome) c.lookup("java:comp/env/Category");
            return rv;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private BookLocalHome lookupBookLocal() {
        try {
            Context c = new InitialContext();
            BookLocalHome rv = (BookLocalHome) c.lookup("java:comp/env/Book");
            return rv;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public boolean register(UserDTO userdto) throws FinderException, CreateException {
        userHome = lookupUsersLocal();
        try {
            userLocal = userHome.findByLoginname(userdto.getLoginName());
            return false;
        } catch (ObjectNotFoundException e) {
            try {
                userLocal = userHome.findByEmail(userdto.getEmail());
                return false;
            } catch (ObjectNotFoundException e1) {
                userHome.create(userdto);
                return true;
            }
        }
    }

    public UserDTO login(String loginName, String password) throws FinderException {
        userHome = lookupUsersLocal();
        try {
            userLocal = userHome.findByLoginname(loginName);
            if (userLocal.getPassword().equalsIgnoreCase(password)) {
                return new UserDTO(userLocal.getId().intValue(), userLocal.getLoginname(),
                        userLocal.getEmail(), userLocal.getPassword(), userLocal.getPhone(),
                        userLocal.getFullname(), userLocal.getBirthday(), userLocal.getCreatedate());
            } else {
                return null;
            }
        } catch (ObjectNotFoundException oe) {
        }
        return null;
    }

    public UserDTO edit(String loginName, String password, String updatedEmail,
            String updatedPassword, String updatedPhone,
            String updatedFullName, Date birthday) throws FinderException {
        UserDTO userdto = login(loginName, password);
        try {
            userLocal = lookupUsersLocal().findByEmail(updatedEmail);
            return null;
        } catch (FinderException e) {
            if (userdto != null) {
                userLocal.setEmail(updatedEmail);
                userLocal.setPassword(updatedPassword);
                userLocal.setPhone(updatedPhone);
                userLocal.setFullname(updatedFullName);
                userLocal.setBirthday(birthday);
                userdto.setId(userLocal.getId().intValue());
                userdto.setEmail(updatedEmail);
                userdto.setPassword(updatedPassword);
                userdto.setPhone(updatedPhone);
                userdto.setFullName(updatedFullName);
                userdto.setBirthDate(birthday);
                return userdto;
            } else {
                return null;
            }
        }
    }

    public List browseBooks(String categoryName) {
        categoryHome = lookupCategoryLocal();
        Collection bookLocalColl;
        try {
            CategoryLocal local = categoryHome.findByName(categoryName);
            bookLocalColl = local.getBookCollection();
            List bookLocalList = new ArrayList(bookLocalColl);
            List bookDTOList = new ArrayList();
            for (int i = 0; i < bookLocalList.size(); i++) {
                BookLocal bookLocal = (BookLocal) bookLocalList.get(i);
                BookDTO bookdto = new BookDTO(bookLocal.getId().intValue(),
                        bookLocal.getTitle(),
                        bookLocal.getDescription(),
                        bookLocal.getAuthor(),
                        bookLocal.getPublisher(),
                        bookLocal.getPublishdate(),
                        bookLocal.getPrice(),
                        bookLocal.getPhoto(),
                        bookLocal.getRatingcount(),
                        bookLocal.getRatingvalue(), bookLocal.getCategory().getName());
                bookDTOList.add(bookdto);
            }
            return bookDTOList;
        } catch (FinderException e) {
            return null;
        }
    }

    public List searchBooks(String bookNameKeyword, String categoryName) {
        String defaultCategoryName = "search all";
        List bookDTOList = null;
        try {
            if (categoryName.equalsIgnoreCase(defaultCategoryName)) {
                Collection bookLocalColl = lookupBookLocal().findByTitle("%" + bookNameKeyword + "%");
                bookDTOList = getBookDTOList(bookLocalColl);
                return bookDTOList;
            }
            CategoryLocal categoryLocal = lookupCategoryLocal().findByName(categoryName);
            Collection bookLocalsList = categoryLocal.searchBooksByName(bookNameKeyword);
            bookDTOList = getBookDTOList(bookLocalsList);
            return bookDTOList;
        } catch (FinderException e) {
            return null;
        }
    }

    public List searchBooksByAuthorName(String authorKeyword, String categoryName) {
        String defaultCategoryName = "search all";
        List bookDTOList = null;
        try {
            if (categoryName.equalsIgnoreCase(defaultCategoryName)) {
                Collection bookLocalColl = lookupBookLocal().findByAuthor("%" + authorKeyword + "%");
                bookDTOList = getBookDTOList(bookLocalColl);
                return bookDTOList;
            }
            CategoryLocal categoryLocal = lookupCategoryLocal().findByName(categoryName);
            Collection bookLocalsList = categoryLocal.searchBooksByAuthorName(authorKeyword);
            bookDTOList = getBookDTOList(bookLocalsList);
            return bookDTOList;
        } catch (FinderException e) {
            return null;
        }
    }

    private List getBookDTOList(Collection c) {
        List bookDTOList = new ArrayList();
        List bookLocalList = new ArrayList(c);
        for (int i = 0; i < bookLocalList.size(); i++) {
            BookLocal bookLocal = (BookLocal) bookLocalList.get(i);
            BookDTO bookdto = new BookDTO(bookLocal.getId().intValue(),
                    bookLocal.getTitle(),
                    bookLocal.getDescription(),
                    bookLocal.getAuthor(),
                    bookLocal.getPublisher(),
                    bookLocal.getPublishdate(),
                    bookLocal.getPrice(),
                    bookLocal.getPhoto(),
                    bookLocal.getRatingcount(),
                    bookLocal.getRatingvalue(), bookLocal.getCategory().getName());
            bookDTOList.add(bookdto);
        }
        return bookDTOList;
    }

    public BookCommentsDTO viewBookDetail(int bookID) {
        BookLocal bookLocal = null;
        try {
            bookLocal = lookupBookLocal().findById(Integer.valueOf(bookID));
            BookDTO bookdto = new BookDTO(bookID, bookLocal.getTitle(),
                    bookLocal.getDescription(),
                    bookLocal.getAuthor(),
                    bookLocal.getPublisher(),
                    bookLocal.getPublishdate(),
                    bookLocal.getPrice(),
                    bookLocal.getPhoto(),
                    bookLocal.getRatingcount(),
                    bookLocal.getRatingvalue(),
                    bookLocal.getCategory().getName());
            Collection c = bookLocal.getCommentCollection();
            return new BookCommentsDTO(bookdto, commentDTOsList(c));
        } catch (FinderException e) {
            return null;
        }
    }

    private ArrayList commentDTOsList(Collection commentLocalColl) {
        ArrayList commentDTOsList = new ArrayList();
        Iterator i = commentLocalColl.iterator();
        while (i.hasNext()) {
            CommentLocal commentLocal = (CommentLocal) i.next();
            commentDTOsList.add(new CommentDTO(commentLocal.getRating(),
                    commentLocal.getContent(),
                    commentLocal.getCommentDate(),
                    commentLocal.getUsers().getLoginname()));
        }
        return commentDTOsList;
    }

    public boolean addComment(int bookid, int userid, int rating, String content) {
        try {
            BookLocal bookLocal = lookupBookLocal().findByPrimaryKey(Integer.valueOf(bookid));
            userLocal = lookupUsersLocal().findByPrimaryKey(Integer.valueOf(userid));
            Collection commentColl = bookLocal.getCommentCollection();
            ArrayList commentList = new ArrayList(commentColl);
            for (int i = 0; i < commentList.size(); i++) {
                CommentLocal commentLocal = null;
                commentLocal = (CommentLocal) commentList.get(i);
                if (commentLocal.getUsers().getId().intValue() == userid) {
                    return false;
                }
            }
            CommentLocal commentLocal = lookupCommentLocal().create(new CommentDTO(rating, content, today));
            commentLocal.setBook(bookLocal);
            commentLocal.setUsers(userLocal);
            bookLocal.setRatingcount(bookLocal.getRatingcount() + 1);
            bookLocal.setRatingvalue(bookLocal.getRatingvalue() + rating);
            return true;
        } catch (FinderException fe) {
            return false;
        } catch (CreateException ce) {
            new RuntimeException(ce);
        }
        return false;
    }

    private CommentLocalHome lookupCommentLocal() {
        try {
            Context c = new InitialContext();
            CommentLocalHome rv = (CommentLocalHome) c.lookup("java:comp/env/Comment");
            return rv;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public boolean addBookToCart(int bookid, int quantity) {
        try {
            bookHome = lookupBookLocal();
            BookLocal bookLocal = bookHome.findById(Integer.valueOf(bookid));
            // instantiate a bookdto by found bookLocal
            BookDTO bookdto = new BookDTO(bookid, bookLocal.getTitle(),
                    bookLocal.getAuthor(),
                    bookLocal.getPrice());
            BookQuantityDTO bookQuantitydto = new BookQuantityDTO(quantity, bookdto);
            // check if have any book already added equal id to this purchased book.
            if (!shoppingCart.isEmpty()) {
                for (int i = 0; i < shoppingCart.size(); i++) {
                    BookQuantityDTO bookQuandto = (BookQuantityDTO) shoppingCart.get(i);
                    if (bookQuandto.getBookdto().getId() == bookid) {
                        bookQuantitydto.setQuantity(quantity + bookQuandto.getQuantity());
                        // Remove this product at this index
                        shoppingCart.remove(i);
                        break;
                    }
                }
            }
            shoppingCart.add(bookQuantitydto);
            return true;
        } catch (FinderException e) {
            return false;
        }
    }

    public ArrayList getShoppingCart() {
        System.out.println(shoppingCart);
        return shoppingCart;
    }

    public boolean updateBookQuantityInCart(int bookID, int quantity) {
        // find book with match id and set new quantity for it
        for (int i = 0; i < shoppingCart.size(); i++) {
            BookQuantityDTO bookQuanDTO = (BookQuantityDTO) shoppingCart.get(i);
            if (bookQuanDTO.getBookdto().getId() == bookID) {
                bookQuanDTO.setQuantity(quantity);
                return true;
            }
        }
        return false; // No book is added in shopping cart.
    }

    public boolean confirmOrder(int userID, String address) throws CreateException, NamingException, SQLException {
        try {
            userLocal = lookupUsersLocal().findByPrimaryKey(Integer.valueOf(userID));
            if (!shoppingCart.isEmpty()) {
                //Instantiate an OrderDTO object and save it to database
                OrderDTO orderdto = new OrderDTO(today, shoppingCart.size(), address);
                OrdersLocal ordersLocal = lookupOrdersLocal().create(orderdto);
                ordersLocal.setUsers(userLocal);
                int orderlineNewID = getOrderLineNewID().intValue();
                for (int i = 0; i < shoppingCart.size(); i++) {
                    //int orderlineNewID = getOrderLineNewID().intValue();
                    BookQuantityDTO bookQuandto = (BookQuantityDTO) shoppingCart.get(i);
                    int bookid = bookQuandto.getBookdto().getId();
                    int quantity = bookQuandto.getQuantity();
                    double price = bookQuandto.getBookdto().getPrice();
                    OrderLineLocal orderLineLocal = lookupOrderLineLocal().create(new OrderLineDTO(orderlineNewID, price, quantity, bookid));
                    orderLineLocal.setOrders(ordersLocal);
                    orderLineLocal.setBook(lookupBookLocal().findByPrimaryKey(Integer.valueOf(bookid)));
                    orderlineNewID++;
                }
                return true;
            }
            return false;
        } catch (FinderException fe) {
            return false;
        }
    }

    private OrdersLocalHome lookupOrdersLocal() {
        try {
            Context c = new InitialContext();
            OrdersLocalHome rv = (OrdersLocalHome) c.lookup("java:comp/env/Orders");
            return rv;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private OrderLineLocalHome lookupOrderLineLocal() {
        try {
            Context c = new InitialContext();
            OrderLineLocalHome rv = (OrderLineLocalHome) c.lookup("java:comp/env/OrderLine");
            return rv;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DataSource getBookstoredb() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/bookstoredb");
    }

    private Integer getOrderLineNewID() throws NamingException, SQLException {
        Connection connection = getBookstoredb().getConnection();
        PreparedStatement psmt = connection.prepareStatement("select max(id) from order_line");
        ResultSet rs = psmt.executeQuery();
        Integer id = new Integer(1);
        if (rs.next()) {
            id = new Integer(rs.getInt(1) + 1);
        }
        return id;
    }

    public ArrayList viewPassOrders(int userid) {
        ArrayList a = new ArrayList();
        try {
            userLocal = lookupUsersLocal().findByPrimaryKey(Integer.valueOf(userid));
            ArrayList ordersList = new ArrayList(userLocal.getOrdersCollection());
            for (int i = 0; i < ordersList.size(); i++) {
                OrdersLocal orderlocal = (OrdersLocal) ordersList.get(i);
                OrderDTO orderdto = castToOrderDTO(orderlocal);
                ArrayList orderLinesList = castToOrderLinesListDTO(orderlocal.getOrderLineCollection());
                a.add(new OrderAndOrderLineDTO(orderdto, orderLinesList));
            }
            return a;
        } catch (FinderException fe) {
            return null;
        }
    }

    private OrderDTO castToOrderDTO(OrdersLocal orderlocal) {
        return new OrderDTO(orderlocal.getId().intValue(), orderlocal.getOrderDate(),
                orderlocal.getTotalAmount(), orderlocal.getShippingAddress());
    }

    private ArrayList castToOrderLinesListDTO(Collection orderLineCollection) {
        ArrayList a = new ArrayList();
        Iterator i = orderLineCollection.iterator();
        while (i.hasNext()) {
            OrderLineLocal orderline = (OrderLineLocal) i.next();
            a.add(new OrderLineDTO(orderline.getId().intValue(), orderline.getPrice(), orderline.getQuantity(), orderline.getBook().getTitle()));
        }
        return a;
    }

    public int getUserID(String loginName) throws FinderException {
        UsersLocal uLocal = lookupUsersLocal().findByLoginname(loginName);
        return uLocal.getId().intValue();
    }

    public boolean findExistLoginName(String loginName) {
        try {
            lookupUsersLocal().findByLoginname(loginName);
            System.out.println("#################Found##################");
            return true;
        } catch (FinderException e) {
            return false;
        }
    }

    public boolean findExistEmail(String email) throws FinderException {
        try {
            lookupUsersLocal().findByEmail(email);
            System.out.println("#################Found##################");
            return true;
        } catch (FinderException e) {
            return false;
        }
    }

    public ArrayList getAllCategories() throws FinderException {
        return transferToCategoryDTOColl(lookupCategoryLocal().findAllCategories());
    }

    private ArrayList transferToCategoryDTOColl(Collection categories) {
        ArrayList cagList = new ArrayList();
        Iterator it = categories.iterator();
        while (it.hasNext()) {
            CategoryLocal local = (CategoryLocal) it.next();
            CategoryDTO dto = new CategoryDTO(local.getId().intValue(), local.getName(), local.getSortorder());
            cagList.add(dto);
        }
        return cagList;
    }

    public ArrayList browseBookByCagID(int cagID) throws FinderException {
        CategoryLocal cagLocal = lookupCategoryLocal().findById(Integer.valueOf(cagID));
        Collection bookColl = cagLocal.getBookCollection();
        return transferToBookDTOList(bookColl);
    }

    private ArrayList transferToBookDTOList(Collection c) {
        ArrayList a = new ArrayList();
        Iterator it = c.iterator();
        while (it.hasNext()) {
            BookLocal local = (BookLocal) it.next();
            BookDTO dto = new BookDTO();
            dto.setId(local.getId().intValue());
            dto.setAuthorName(local.getAuthor());
            dto.setCategoryName(local.getCategory().getName());
            dto.setDescription(local.getDescription());
            dto.setPrice(local.getPrice());
            dto.setPublisherName(local.getPublisher());
            dto.setPublishingDate(local.getPublishdate());
            dto.setRatingCount(local.getRatingcount());
            dto.setRatingValue(local.getRatingvalue());
            dto.setTitle(local.getTitle());
            a.add(dto);
        }
        return a;
    }

    public ArrayList browseBooksInPage(ArrayList bookList, int pageNumber, int pageSize) {
        ArrayList a = new ArrayList();
        pageNumber--;
        pageNumber *= pageSize;
        int end = pageNumber + pageSize;
        if (bookList.size() < end) {
            end = bookList.size();
        }

        for (int i = pageNumber; i < end; i++) {
            a.add(bookList.get(i));
        }

        return a;
    }

    public TreeSet browseBook(int categoryID) throws FinderException {
        CategoryLocal local = lookupCategoryLocal().findById(Integer.valueOf(categoryID));
        Collection bookCollection = local.getBookCollection();
        return convertToBookDTOTreeSet(bookCollection);
    }

    private TreeSet convertToBookDTOTreeSet(Collection bookCollection) {
        TreeSet ts = new TreeSet();
        Iterator it = bookCollection.iterator();
        while (it.hasNext()) {
            BookLocal local = (BookLocal) it.next();
            BookDTO dto = new BookDTO();
            dto.setId(local.getId().intValue());
            dto.setAuthorName(local.getAuthor());
            dto.setCategoryName(local.getCategory().getName());
            dto.setDescription(local.getDescription());
            dto.setPrice(local.getPrice());
            dto.setPublisherName(local.getPublisher());
            dto.setPublishingDate(local.getPublishdate());
            dto.setRatingCount(local.getRatingcount());
            dto.setRatingValue(local.getRatingvalue());
            dto.setTitle(local.getTitle());
            ts.add(dto);
        }
        return ts;
    }

    public int getBookAmountInCart() {
        int bookAmount = 0;
        for (int i = 0; i < shoppingCart.size(); i++) {
            BookQuantityDTO dto = (BookQuantityDTO)shoppingCart.get(i);
            bookAmount += dto.getQuantity();
        }
        return bookAmount;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (int i = 0; i < shoppingCart.size(); i++) {
            BookQuantityDTO dto = (BookQuantityDTO)shoppingCart.get(i);
            total += dto.getQuantity() * dto.getBookdto().getPrice();
        }
        return total;
    }

    public boolean removeBookInCart(int bookID) {
        for (int i = 0; i < shoppingCart.size(); i++) {
            if(((BookQuantityDTO)shoppingCart.get(i)).getBookdto().getId() == bookID){
                shoppingCart.remove(i);
                return true;
            }
        }
        return false;
    }

    public TreeSet searchBookByTitle(String keyword, int categoryid) throws FinderException {
        // categoryid not equal 0 says that one category has been chosens
        if(categoryid != 0){
            CategoryLocal categoryL = lookupCategoryLocal().findById(Integer.valueOf(categoryid));
            Collection c = categoryL.searchBooksByName(keyword);
            return convertToBookDTOTreeSet(c);
        }
        // otherwise search book in all categories
        return convertToBookDTOTreeSet(lookupBookLocal().findByTitle("%" + keyword + "%"));
    }

    public TreeSet searchBookByAuthorName(String keyword, int categoryid) throws FinderException {
        if(categoryid != 0){
            CategoryLocal categoryL = lookupCategoryLocal().findById(Integer.valueOf(categoryid));
            Collection c = categoryL.searchBooksByAuthorName(keyword);
            return convertToBookDTOTreeSet(c);
        }
        return convertToBookDTOTreeSet(lookupBookLocal().findByAuthor("%" + keyword + "%"));
    }

    


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
}
