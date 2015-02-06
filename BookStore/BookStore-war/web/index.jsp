<%-- 
    Document   : index
    Created on : Apr 28, 2011, 11:56:20 AM
    Author     : Phat Huy
--%>
<%@page import="dto.UserDTO, javax.servlet.http.HttpSession"%>
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
                    <h2>Welcome to my web site, enjoy your shopping time</h2>
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
                                <form name="myform" method="post" action="Login">
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
                    <div class="separator"></div>
                    <%@include file="Cart.jsp" %>
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
        <script type="text/javascript">
            function validateLoginInfo(){
                var passwordpattern = /^[a-zA-Z0-9]{6,}$/;

                var x = document.forms["myform"]["loginname"].value
                if(x == ""){
                    alert("Username must be filled out");
                    return false;
                }
                x = document.forms["myform"]["password"].value
                if(x == ""){
                    alert("Password must be filled out");
                    return false;
                }else if(!x.match(passwordpattern)){
                    alert("Password length must be larger than 6 character");
                    return false;
                }
            }
        </script>
    </body>
</html>
