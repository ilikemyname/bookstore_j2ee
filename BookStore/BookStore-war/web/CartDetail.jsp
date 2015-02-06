<%-- 
    Document   : CartDetail
    Created on : May 8, 2011, 9:00:16 AM
    Author     : Phat Huy
--%>

<%@page import="dto.BookDTO"%>
<%@page import="dto.BookQuantityDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <%
                                //HttpSession shoppingCartSession = request.getSession();
                                UserDTO loggedinUser = (UserDTO) userSession.getAttribute("loggedinUser");
                                ArrayList cartList = (ArrayList) userSession.getAttribute("cartList");
                                //if condition 1
                                if (cartList != null && !cartList.isEmpty()) {
                    %>
                    <div style="float:left">
                        <form name="CheckOutForm" method="post" action="Checkout" onsubmit="return validateCheckout()">
                            <table width="350" border="1">
                                <tr><th width="180">Book Title</th><th width="100">Quantity</th><th width="50">Unit Price</th><td></td></tr>
                                <%
                                                                    for (int i = 0; i < cartList.size(); i++) {
                                                                        BookQuantityDTO dto = (BookQuantityDTO) cartList.get(i);
                                                                        BookDTO bookdto = dto.getBookdto();
                                %>
                                <tr>
                                <input type="hidden" value="<%= bookdto.getId()%>" name="bookNo_<%= i%>" />
                                <td><%= bookdto.getTitle()%></td>
                                <td align="center">
                                    <select name="quantity_<%= i%>">
                                        <option selected value="<%= dto.getQuantity() %>"><%= dto.getQuantity() %></option>
                                        <%for (int j = 1; j <= 10; j++) {%>
                                        <option value="<%= j%>"><%= j%></option>
                                        <%}%>
                                    </select>
<!--                                    <input name="quantity_<%= i%>" type="text" value="<%= dto.getQuantity()%>" size="1" style="text-align:center" />-->
                                </td>
                                <td align="center"><%= bookdto.getPrice()%></td>
<!--                                <td align="center"><%= bookdto.getPrice() * dto.getQuantity()%></td>-->
                                <td><a href="UpdateCart?id=<%= bookdto.getId()%>" ><input type="button" class="normal_button" value="Remove" /></a></td>
                                </tr>
                                <%
                                                                    }//end of for loop over cartList
                                                                    if (loggedinUser != null) {
                                %>
                                <tr>
                                    <td colspan="4"><textarea cols="40" name="address">your address</textarea></td>
                                </tr>
                                <%      }//end of condition check an user session existed
                                %>
                            </table>
                            <input name="update" type="submit" class="normal_button" value="Update" /> <input name="checkout" type="submit" class="normal_button" value="Check Out" />
                        </form>
                        <%
                                                            HttpSession yourCartSession = request.getSession();
                                                            Integer bookAmount = (Integer) yourCartSession.getAttribute("bookAmount");
                                                            if (bookAmount != null) {
                        %>
                        <h2>Total: ${bookAmount} book(s) | <span class="green">Total price: $ ${totalPrice}</span></h2>
                        <%                                                            } else {
                        %>
                        <h2>Total: 0 book | <span class="green">Total price: $0.0</span></h2>
                        <%    }
                        %>
                    </div>
                    <%
                                }//end of if condition 1
                    %>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function validateCheckout(){
                var x = document.forms["CheckOutForm"]["address"].value
                if(x == ""){
                    alert("Address must be filled out");
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