/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import common.CallSessionBean;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phat Huy
 */
public class Checkout extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, RemoteException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //int bookid = Integer.parseInt(request.getParameter("bookid"));
            //int bookQuantity = Integer.parseInt(request.getParameter("quantity"));
            String address = request.getParameter("address");
            HttpSession session = request.getSession();
            UserDTO dto = (UserDTO) session.getAttribute("loggedinUser");
            String checkout = request.getParameter("checkout");
            String update = request.getParameter("update");
            try {
                boolean result = false;
                ArrayList cartList = (ArrayList) CallSessionBean.lookupCustomerRemote().getShoppingCart();
                int bookID = 0;
                int quantity = 0;
                for (int i = 0; i < cartList.size(); i++) {
                    bookID = Integer.parseInt(request.getParameter("bookNo_" + i));
                    quantity = Integer.parseInt(request.getParameter("quantity_" + i));
                    if (checkout != null) {
                        result = CallSessionBean.lookupCustomerRemote().updateBookQuantityInCart(bookID, quantity);
                        //result = CallSessionBean.lookupCustomerRemote().confirmOrder(dto.getId(), address);
                    } else if (update != null) {
                        result = CallSessionBean.lookupCustomerRemote().updateBookQuantityInCart(bookID, quantity);
                    }
                }
                if (checkout != null) {
                    result = CallSessionBean.lookupCustomerRemote().confirmOrder(dto.getId(), address);
                }
                if (result) {
                    int bookAmount = CallSessionBean.lookupCustomerRemote().getBookAmountInCart();
                    double totalPrice = CallSessionBean.lookupCustomerRemote().getTotalPrice();
                    session.setAttribute("cartList", cartList);
                    session.setAttribute("bookAmount", Integer.valueOf(bookAmount));
                    session.setAttribute("totalPrice", Double.valueOf(totalPrice));
                    if (checkout != null) {
                        request.getRequestDispatcher("CheckoutSuccess.jsp").forward(request, response);
                    }
                }
                if (update != null) {
                    request.getRequestDispatcher("CartDetail.jsp").forward(request, response);
                }
            } catch (NamingException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            out.close();
        }
    }
}
