/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import common.CallSessionBean;
import dto.UserDTO;
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
public class MyOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            UserDTO userdto = (UserDTO)session.getAttribute("loggedinUser");
            int userID = userdto.getId();
            ArrayList orderList = CallSessionBean.lookupCustomerRemote().viewPassOrders(userID);
            session.setAttribute("myOrderList", orderList);
            request.getRequestDispatcher("ViewOrder.jsp").forward(request, response);
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
