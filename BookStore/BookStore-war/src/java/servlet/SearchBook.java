/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import common.CallSessionBean;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SearchBook extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keywords");
            String attribute = request.getParameter("attribute");
            int categoryid = Integer.parseInt(request.getParameter("category"));
            TreeSet bookList;
            if (attribute.equalsIgnoreCase("title")) {
                bookList = CallSessionBean.lookupCustomerRemote().searchBookByTitle(keyword, categoryid);
            } else {
                bookList = CallSessionBean.lookupCustomerRemote().searchBookByAuthorName(keyword, categoryid);
            }
            HttpSession session = request.getSession();
            session.setAttribute("bookList", bookList);
            int index = 0;
            String incomingPage = request.getParameter("pageNumber");
            if (incomingPage == null) {
                index = 1;
            } else {
                index = Integer.parseInt(incomingPage);
            }
            session.setAttribute("currentPage", Integer.valueOf(index));
            if (bookList.size() > 0) {
                request.getRequestDispatcher("ViewBooks.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("SearchNotFound.jsp").forward(request, response);
            }
        } finally {
            out.close();
        }
    }
}
