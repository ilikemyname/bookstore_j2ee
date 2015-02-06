<%-- 
    Document   : Cart
    Created on : May 8, 2011, 7:52:13 AM
    Author     : Phat Huy
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<div class="cart">
    <div class="title"><span class="title_icon"><img src="images/cart.gif" /></span>Your Current Cart</div>
    <div class="about">
        <div id="cart_sum" class="home_cart_content">
            <%
                        HttpSession yourCartSession = request.getSession();
                        Integer bookAmount = (Integer) yourCartSession.getAttribute("bookAmount");
                        if (bookAmount != null) {
            %>
            Total: ${bookAmount} book(s) | <span class="green">Total price: $ ${totalPrice}</span>
            <%
                                                        } else {
            %>
            Total: 0 book | <span class="green">Total price: $0.0</span>
            <%                        }
            %>
        </div>
    </div>
</div>
