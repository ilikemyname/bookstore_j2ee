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
import javax.ejb.FinderException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phat Huy
 */
public class Login extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

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
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, RemoteException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String loginName = request.getParameter("loginname");
            String password = request.getParameter("password");
            try {
                UserDTO userdto = CallSessionBean.lookupCustomerRemote().login(loginName, password);
                if (userdto != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedinUser", userdto);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("login_fail.jsp").forward(request, response);
                }
            } catch (FinderException ex) {
            }
        } finally {
            out.close();
        }
    }
}
