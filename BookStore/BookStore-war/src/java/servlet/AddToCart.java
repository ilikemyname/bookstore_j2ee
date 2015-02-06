/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import common.CallSessionBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phat Huy
 */
public class AddToCart extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            int bookid = Integer.parseInt(request.getParameter("id"));
            boolean result = CallSessionBean.lookupCustomerRemote().addBookToCart(bookid, 1);
            if(result){
                ArrayList cartList = (ArrayList)CallSessionBean.lookupCustomerRemote().getShoppingCart();
                int bookAmount = CallSessionBean.lookupCustomerRemote().getBookAmountInCart();
                double totalPrice = CallSessionBean.lookupCustomerRemote().getTotalPrice();
                HttpSession session = request.getSession();
                ArrayList cagList = (ArrayList)session.getAttribute("cagList");
                session.setAttribute("cartList", cartList);
                session.setAttribute("bookAmount", Integer.valueOf(bookAmount));
                session.setAttribute("totalPrice", Double.valueOf(totalPrice));
                request.getRequestDispatcher("CartDetail.jsp").forward(request, response);
            }
        }finally{
            out.close();
        }
    } 

}
