<%-- 
    Document   : Fail_AddComment
    Created on : May 7, 2011, 11:30:12 AM
    Author     : Phat Huy
--%>

<%--
    Document   : Success_AddComment
    Created on : May 7, 2011, 11:29:59 AM
    Author     : Phat Huy
--%>

<%@page import="dto.CommentDTO"%>
<%@page import="dto.BookDTO"%>
<%@page import="dto.BookCommentsDTO"%>
<%@page import="dto.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="myTag" uri="/WEB-INF/tlds/CustomTags" %>
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
                    Find <input id="keywords" type="text" />
                    By
                    <select id="search_by">
                        <option selected="selected" value="title">Title</option>
                        <option value="author">Author</option>
                    </select>
                    In
                    <select id="category">
                        <option selected="selected" value="0">All Categories</option>
                        <c:forEach var="eachCategory" items="${cagList}">
                            <option value="${eachCategory.id}">${eachCategory.name}</option>
                        </c:forEach>
                    </select>
                    <input onclick="" id="search" type="button" class="normal_button" value="Search" />
                    <br />
                    <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Categories</div>
                    <div class="new_products">
                        <h1 style="color: #FF9900">Sorry, you cannot add comment twice to this book</h1>
                        <c:forEach var="eachCategory" items="${cagList}">
                            <div class="new_prod_box">
                                ${eachCategory.name}
                                <div class="new_prod_bg">
                                    <a href="RetrieveBooks?id=${eachCategory.id}" rel="${eachCategory.id}"><img src="images/${eachCategory.name}.png" class="thumb" border="0" /></a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
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
        <script type="text/javascript">
            function validateCommentContent(){
                var ratingpattern = /^[1-5]{1}$/;
                var x = document.forms["myCommentForm"]["rating"].value
                if(x == ""){
                    alert("Username must be filled out");
                    return false;
                }else if(!x.match(ratingpattern)){
                    alert("Please rating number from 1 to 5");
                    return false;
                }
            }
        </script>
    </body>
</html>