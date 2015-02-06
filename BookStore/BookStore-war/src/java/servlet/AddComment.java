/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import common.CallSessionBean;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phat Huy
 */
public class AddComment extends HttpServlet {

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            HttpSession session = request.getSession();
            int bookID = Integer.parseInt(request.getParameter("bookid"));
            int userID = ((UserDTO)session.getAttribute("loggedinUser")).getId();
            int rating = Integer.parseInt(request.getParameter("rating"));
            String content = String.valueOf(request.getParameter("content"));
            boolean result = CallSessionBean.lookupCustomerRemote().addComment(bookID, userID, rating, content);
            if(result){
                request.getRequestDispatcher("Success_AddComment.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("Fail_AddComment.jsp").forward(request, response);
            }
        }finally{
            out.close();
        }
    }

}
