<%-- 
    Document   : register
    Created on : Apr 29, 2011, 11:55:03 AM
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
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="Category">Categories</a></li>
                        <li><a href="register.jsp" class="selected">Register</a></li>
                    </ul>
                </div>
            </div>
            <div class="center_content">
                <div class="left_content">
                    <div id="register_form" class="feat_prod_box_details">
                        <div class="sub_contact_form">
                            <div class="form_subtitle">Create new account</div>
                            <div class="form_row">
                                <form name="myform" method="post" action="Register" onsubmit="return validateRegisterInfo()">
                                    <label class="contact">Username </label><input type="text" name="username" class="contact_input" />
                                    <label class="contact">Password </label> <input type="password" name="password" class="contact_input" />
                                    <label class="contact">Email </label> <input type="text" name="email" class="contact_input" />
                                    <label class="contact">Full Name </label> <input type="text" name="fullname" class="contact_input" />
                                    <label class="contact">Phone </label> <input type="text" name="phone" class="contact_input" />
                                    <label class="contact">Birthday</label>
                                    <select name="day" class="register_date">
                                        <%
                                                    for (int i = 1; i <= 31; i++) {
                                        %>
                                        <option value="<%=i%>"><%=i%></option>
                                        <%
                                                    }
                                        %>
                                    </select>

                                    <select id="month" name="month" class="register_date">
                                        <%
                                                    String[] monthArray = {"January", "Febuary", "March", "April", "May", "June", "July",
                                                        "August", "September", "October", "November", "December"};
                                                    for (int i = 1; i <= 12; i++) {
                                        %>
                                        <option value="<%=i%>"><%=monthArray[i - 1]%></option>
                                        <%
                                                    }
                                        %>
                                    </select>

                                    <select id="year" name="year" class="register_date">
                                        <%
                                                    for (int i = 1970; i <= 2005; i++) {
                                        %>
                                        <option value="<%=i%>"><%=i%></option>
                                        <%
                                                    }
                                        %>
                                    </select>
                                    <input type="submit" class="register" value="Register" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="right_content">
                    <div id="login_form">
                        <%
                                    HttpSession userSession = request.getSession();
                                    UserDTO user = (UserDTO) userSession.getAttribute("loggedinUser");
                                    if (user == null) {

                        %>
                        <div class="form_row">
                            <form name="loginform" method="post" action="Login">
                                <label class="contact">Username</label> <input type="text" name="loginname" class="contact_input" /><br />
                                <label class="contact">Password</label> <input type="password" class="contact_input" /><br />
                                <input id="login" type="submit" value="Login" /><br />
                            </form>
                        </div>
                        <%} else {%>
                        <div class="form_row">
                            <h3>Hi, ${loggedinUser.loginName}</h3>
                            <input type="hidden" id="userid" value="${user.id}" />
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
            function validateRegisterInfo(){
                var usernamepattern = /^[a-zA-Z0-9]+$/;
                var passwordpattern = /^[a-zA-Z0-9]{6,}$/;
                //var emailpattern = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
                var fullnamepattern = /^[a-zA-Z]+(([\'\,\.\- ][a-zA-Z ])?[a-zA-Z]*)*$/;
                var phonepattern = /^[0-9]{10}$/;

                var x = document.forms["myform"]["username"].value
                if(x == ""){
                    alert("Username must be filled out");
                    return false;
                }else if(!x.match(usernamepattern)){
                    alert("Username accept only alphanumeric character.");
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
                x = document.forms["myform"]["email"].value
                var atpos=x.indexOf("@");
                var dotpos=x.lastIndexOf(".");
                if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
                {
                    alert("Not a valid e-mail address");
                    return false;
                }
                x = document.forms["myform"]["fullname"].value
                if(x == ""){
                    alert("Fullname must be filled out");
                    return false;
                }else if(!x.match(fullnamepattern)){
                    alert("Invalid fullname");
                    return false;
                }
                x = document.forms["myform"]["phone"].value
                if(x == ""){
                    alert("Phone number must be filled out");
                    return false;
                }else if(!x.match(phonepattern)){
                    alert("Phone number must have 10 numbers");
                    return false;
                }
            }
            function validateLoginInfo(){
                var passwordpattern = /^[a-zA-Z0-9]{6,}$/;

                var x = document.forms["loginform"]["loginname"].value
                if(x == ""){
                    alert("Username must be filled out");
                    return false;
                }
                x = document.forms["loginform"]["password"].value
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