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
public class Category extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ArrayList cagList = CallSessionBean.lookupCustomerRemote().getAllCategories();
            HttpSession session = request.getSession();
            session.setAttribute("cagList", cagList);
            //request.setAttribute("cagList", cagList);
            request.getRequestDispatcher("ViewCategory.jsp").forward(request, response);
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    //<myTag:BookListTag bookList="${bookList}"/>
}
