<%-- 
    Document   : ViewOrder
    Created on : May 2, 2011, 8:10:58 AM
    Author     : Phat Huy
--%>

<%@page import="dto.OrderLineDTO"%>
<%@page import="dto.OrderAndOrderLineDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="myOrderList" class="ArrayList" scope="session" />
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
                <div class="left_content"></div>
                <div class="right_content">
                    <div class="form_row">
                        <h3>Hi, ${loggedinUser.loginName}</h3>
<!--                            <input type="hidden" id="userid" value="${loggedinUser.id}" />-->
                        <a href="Logout"><input type="button" id="logout" value="Log out" class='normal_button' /></a>
                        <a href="editUser.jsp"><input type="button" id="changeProfile" value="Edit Profile" class='normal_button' /></a>
                    </div>
                    <div id="login_form">
                        <div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>Your Orders</div>
                                <%
                                            for (int i = 0; i < myOrderList.size(); i++) {
                                                OrderAndOrderLineDTO orders = (OrderAndOrderLineDTO) myOrderList.get(i);
                                %>
                        <div class="form_row">
                            <label><strong>Order <%= i + 1%></strong></label>
                            <table border="1">
                                <tr>
                                    <th>Order date</th><th>Total Amount</th>
                                </tr>
                                <tr>
                                    <td><%= orders.getOrderDTO().getOrderDate().toString()%></td>
                                    <td><%= orders.getOrderDTO().getTotalAmount()%></td>
                                </tr>
                            </table>
                            <label><strong>Ordered books:</strong></label>
                        </div>
                        <table width="350" border="1">
                            <tr><th width="170">Book Title</th><th width="70">Quantity</th><th width="60">Price</th></tr>
                            <%
                                                                                                        for (int j = 0; j < orders.getOrderLinesList().size(); j++) {
                                                                                                            OrderLineDTO orderLines = (OrderLineDTO) orders.getOrderLinesList().get(j);
                            %>
                            <tr>
                                <td><%= orderLines.getBookTitle()%></td>
                                <td align="center"><%= orderLines.getQuantity()%></td>
                                <td align="center"><%= orderLines.getPrice()%></td>
                            </tr>
                            <%
                                                                                                        }
                            %>
                        </table>
                        <%
                                    }
                        %>
                    </div>
                    <div class="separator"></div>
                    <%@include file="Cart.jsp" %>
                </div>
            </div>
        </div>
    </body>
</html>