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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ejb.FinderException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Duong
 */
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, RemoteException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, RemoteException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
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
        Date creationDate = Calendar.getInstance().getTime();
        UserDTO aUser = new UserDTO(username, email, password, phone, fullname, birthday.getTime(), creationDate);

        boolean result = false;
        result = CallSessionBean.lookupCustomerRemote().findExistLoginName(username);
        if (result) {
            out.println("<p>Sorry! This username was registered. Please try other name</p>");
            out.println("<a href='register.jsp'>Back to Register</a>");
            request.getRequestDispatcher("register_fail_username.jsp").forward(request, response);
        } else {
            result = CallSessionBean.lookupCustomerRemote().findExistEmail(email);
            if (result) {
                out.println("<p>Sorry! This email was registered. Please try other mail</p>");
                out.println("<a href='register.jsp'>Back to Register</a>");
                request.getRequestDispatcher("register_fail_email.jsp").forward(request, response);
            }
        }
        try {
            result = CallSessionBean.lookupCustomerRemote().register(aUser);
            if (result) {
                out.println("<p>You registered a new user.</p>");
                request.getRequestDispatcher("register_success.jsp").forward(request, response);
            }
        } catch (FinderException ex) {
            ex.printStackTrace();
        }


    }
}
