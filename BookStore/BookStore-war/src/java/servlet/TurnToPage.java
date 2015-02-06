/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phat Huy
 */
public class TurnToPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
//            TreeSet bookList = (TreeSet) session.getAttribute("bookList");
//            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            int index = 0;
            String incomingPage = request.getParameter("pageNumber");
            if (incomingPage == null) {
                index = 1;
            } else {
                index = Integer.parseInt(incomingPage);
            }
            session.setAttribute("currentPage", Integer.valueOf(index));
            request.getRequestDispatcher("ViewBooks.jsp").forward(request, response);
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
