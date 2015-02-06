<%-- 
    Document   : register_fail
    Created on : Apr 29, 2011, 12:30:59 PM
    Author     : Phat Huy
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
        <title>Book Store - Index</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>
    <body>
        <div id="wrap">
            <div class="header">
                <div class="logo"><a href="index.jsp"><img src="images/logo.gif" border="0" /></a></div>
                <div id="menu">
                    <ul>
                        <li class="selected"><a href="index.jsp">Home</a></li>
                        <li><a href="Category">Categories</a></li>
                        <li><a href="register.jsp">Register</a></li>
                    </ul>
                </div>
            </div>
            <div class="center_content">
                <div class="left_content">
                    <h3>Sorry! Username you filled in has been used by other person.</h3>
                </div>
                <div class="right_content">
                    <div id="login_form">
                        <%
                                    HttpSession userSession = request.getSession();
                                    UserDTO userdto = (UserDTO) userSession.getAttribute("loggedinUser");
                                    if (userdto == null) {
                        %>
                        <div class="form_row">
                            <table>
                                <form method="post" action="Login">
                                    <tr><td><label class="contact">Username</label></td><td><input type="text" name="loginname" class="contact_input" /></td></tr>
                                    <tr><td><label class="contact">Password</label></td><td><input type="password" name="password" class="contact_input" /></td></tr>
                                    <tr><td><input id="login" type="submit" value="Login" /></td></tr>
                                </form>
                            </table>
                        </div>
                        <%} else {%>
                        <div class="form_row">
                            <h3>Hi, ${loggedinUser.loginName}</h3>
<!--                            <input type="hidden" id="userid" value="${loggedinUser.id}" />-->
                            <a href="Logout"><input type="button" id="logout" value="Log out" class='normal_button' /></a>
                            <a href="editUser.jsp"><input type="button" id="changeProfile" value="Edit Profile" class='normal_button' /></a>
                            <a href="MyOrder"><input type="button" id="pastOrder" value="My Order" class='normal_button' /></a>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
            <!--            <div class="footer">
                            <div class="left_footer">
                                <img src="images/footer_logo.gif" />
                            </div>
                            <div class="right_footer">
                                Copyright © 2011 Bookstore Online, All rights reserved.
                            </div>
                        </div>-->
        </div>
    </body>
</html>
