/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import common.CallSessionBean;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phat Huy
 */
public class EditUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            int day = Integer.parseInt(request.getParameter("day"));
            int month = Integer.parseInt(request.getParameter("month"));
            month -= 1;
            int year = Integer.parseInt(request.getParameter("year"));
            Calendar birthday = GregorianCalendar.getInstance();
            birthday.set(year, month, day);
            HttpSession session = request.getSession();
            UserDTO userdto = (UserDTO) session.getAttribute("loggedinUser");
            String loginName = userdto.getLoginName();
            String oldPass = userdto.getPassword();
            userdto = CallSessionBean.lookupCustomerRemote().edit(loginName, oldPass, email, password, phone, fullname, birthday.getTime());
            if (userdto == null) {
                out.println("<p>Error when editting account. This email has been used by other person</p>");
                out.println("<a href='editUser.jsp'>Back to Edit Form</a>");
            } else {
                session.removeAttribute("loggedinUser");
                userdto = (UserDTO) session.getAttribute("loggedinUser");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } finally {
            out.close();
        }
    }
}
