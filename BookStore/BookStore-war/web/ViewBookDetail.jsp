<%-- 
    Document   : ViewBookDetail
    Created on : May 7, 2011, 8:18:14 AM
    Author     : Phat Huy
--%>

<%@page import="dto.CommentDTO"%>
<%@page import="dto.BookDTO"%>
<%@page import="dto.BookCommentsDTO"%>
<%@page import="dto.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <form name="searchform" method="post" action="SearchBook" onsubmit="return validateSearchForm()">
                        Find <input name="keywords" type="text" />
                        By
                        <select name="attribute">
                            <option selected="selected" value="title">Title</option>
                            <option value="author">Author</option>
                        </select>
                        In
                        <select name="category">
                            <option selected="selected" value="0">All Categories</option>
                            <c:forEach var="eachCategory" items="${cagList}">
                                <option value="${eachCategory.id}">${eachCategory.name}</option>
                            </c:forEach>
                        </select>
                        <input name="search" type="submit" class="normal_button" value="Search" />
                    </form>
                    <br />
                    <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Categories</div>
                    <div class="new_products">
                        <c:forEach var="eachCategory" items="${cagList}">
                            <div class="new_prod_box">
                                ${eachCategory.name}
                                <div class="new_prod_bg">
                                    <a href="RetrieveBooks?id=${eachCategory.id}" rel="${eachCategory.id}"><img src="images/${eachCategory.name}.png" class="thumb" border="0" /></a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="title"><span class="title_icon"><img src="images/bullet2.gif" /></span>Books List</div>
                    <div class="new_products">
                        <myTag:Pagination myBookSet="${bookList}" pageNumber="${currentPage}" pageSize="3" />
                    </div>
                    <%
                                BookCommentsDTO bookDetail = (BookCommentsDTO) request.getAttribute("bookDetail");
                                BookDTO bookdto = bookDetail.getBookdto();
                                int bookid = bookdto.getId();
                                HttpSession userSession = request.getSession();
                                UserDTO loggedinUser = (UserDTO) userSession.getAttribute("loggedinUser");
                    %>
                    <div class="title"><span class="title_icon"><img src="images/bullet3.gif"></span>Book Detail</div>
                    <div class="new_products">
                        <table>
                            <tr><td align="right">Title </td><td><%= bookdto.getTitle()%></td></tr>
                            <tr><td align="right">Author </td><td><%= bookdto.getAuthorName()%></td></tr>
                            <tr><td align="right">Description </td><td><%= bookdto.getDescription()%></td></tr>
                            <tr><td align="right">Publisher name</td><td><%= bookdto.getPublisherName()%></td></tr>
                            <tr><td align="right">Unit Price</td><td><%= bookdto.getPrice()%></td></tr>
                            <tr><td align="right">Total Rating</td><td><%= bookdto.getRatingValue()%></td></tr>
                            <tr><td align="right">Rating Amount</td><td><%= bookdto.getRatingCount()%></td></tr>
                            <tr><td align="right">Category</td><td><%= bookdto.getCategoryName()%></td></tr>
                        </table>
                        <div class="new_products">
                            <h3>Post Comment</h3>
                            <%
                                        int commentAmount = bookDetail.getCommentsList().size();
                                        if (commentAmount != 0) {
                                            int i = commentAmount - 1, count = 1;
                                            for (; i >= 0; i--, count++) {
                                                CommentDTO comment = (CommentDTO) bookDetail.getCommentsList().get(i);
                            %>
                            <table>
                                <tr><td colspan="2"><label><strong><h3>Comment <%=count%></h3></strong></label></td></tr>
                                <tr><td align="right" width="100">Username </td><td><%= comment.getLoginName()%></td></tr>
                                <tr><td align="right">Rating </td><td><%= comment.getRating()%></td></tr>
                                <tr><td align="right">Comment date </td><td><%= comment.getCommentDate()%></td></tr>
                                <tr><td align="right">Content </td><td><%= comment.getContent()%></td></tr>
                            </table>
                            <%
                                                                        }
                                                                    } else {
                            %>
                            <h3>There's no comment for this book</h3><br/>
                            <%}%>
                            <%if (loggedinUser != null) {%>
                            <form name="myCommentForm" method="post" action="AddComment" onsubmit="return validateCommentContent()">
                                <table>
                                    <tr><td colspan="2"><label><strong><h3>+ Add your comment here:</h3></strong></label></td></tr>
                                    <tr><input type="hidden" name="bookid" value="<%= bookid%>"/></tr>
                                    <tr><td align="right">Rating:</td><td><input name="rating" type="text" class="contact_input" /></td></tr>
                                    <tr><td align="right">Content:</td><td><textarea name="content" class="contact_textarea"></textarea></td></tr>
                                    <tr><td><input type="submit" value="Send"></td><td><input type="reset" value="Clear"></td></tr>
                                </table>
                            </form>
                            <%}%>
                        </div>
                    </div>
                </div>
                <div class="right_content">
                    <div id="login_form">
                        <%
                                    if (loggedinUser == null) {
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
            function validateSearchForm(){
                var x = document.forms["searchform"]["keywords"].value
                if(x == ""){
                    alert("Keyword must be filled out");
                    return false;
                }
            }
        </script>
    </body>
</html>
