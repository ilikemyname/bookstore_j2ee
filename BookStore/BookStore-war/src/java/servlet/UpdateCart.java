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
public class UpdateCart extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int bookid = Integer.parseInt(request.getParameter("id"));
            boolean result = CallSessionBean.lookupCustomerRemote().removeBookInCart(bookid);
            ArrayList cartList = (ArrayList) CallSessionBean.lookupCustomerRemote().getShoppingCart();
            int bookAmount = CallSessionBean.lookupCustomerRemote().getBookAmountInCart();
            double totalPrice = CallSessionBean.lookupCustomerRemote().getTotalPrice();
            HttpSession session = request.getSession();
            session.setAttribute("cartList", cartList);
            session.setAttribute("bookAmount", Integer.valueOf(bookAmount));
            session.setAttribute("totalPrice", Double.valueOf(totalPrice));
            request.getRequestDispatcher("CartDetail.jsp").forward(request, response);
        } finally {
            out.close();
        }
    }
}
