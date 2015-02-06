/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import common.CallSessionBean;
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
public class RetrieveBooks extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
//            ArrayList cagList = CallSessionBean.lookupCustomerRemote().getAllCategories();
//            request.setAttribute("cagList", cagList);
            String cagIDstr = request.getParameter("id");
            int cagID = Integer.parseInt(cagIDstr);
//            Integer cagID = Integer.valueOf(1);
//            if (cagIDstr == null){
//                cagID = (Integer)request.getSession().getAttribute("cagID");
//            } else {
//                cagID = Integer.valueOf(cagIDstr);
//            }
//
//
//            request.getSession().setAttribute("cagID", cagID);
            HttpSession session = request.getSession();
//            ArrayList bookList = null;
//            if (session.getAttribute("bookList") != null) {
//                bookList = (ArrayList) session.getAttribute("bookList");
//            } else {
            TreeSet bookList = CallSessionBean.lookupCustomerRemote().browseBook(cagID);
            session.setAttribute("bookList", bookList);
//            }
            int index = 0;
            String incomingPage = request.getParameter("pageNumber");
            if (incomingPage == null) {
                index = 1;
            } else {
                index = Integer.parseInt(incomingPage);
            }
//            ArrayList booksInPage = CallSessionBean.lookupCustomerRemote().browseBooksInPage(bookList, index, 3);
//            session.setAttribute("booksInPage", booksInPage);
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