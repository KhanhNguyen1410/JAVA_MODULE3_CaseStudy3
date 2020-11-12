<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/9/2020
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<center>
<body>
<c:set var="customer" scope="session" value='${sessionScope.customer}'/>
<p>
    Tên khách:  <strong><c:out value=" ${customer.getName()}"/></strong><br>
    Địa chỉ  :  <strong><c:out value=" ${customer.getAddress()}"/></strong><br>
    Sdt      :  <strong><c:out value=" ${customer.getPhone()}"/></strong><br>
</p>
<form method="post" action="/bill?action=pay">
    <table border="1">
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Amount</th>
        </tr>
        <c:forEach items='${requestScope["orders"]}' var="order">
            <tr>
                <td>${order.getProduct().getName()}</td>
                <td>${order.getProduct().getPrice()}</td>
                <td>${order.getQuantity()}</td>
                <td>${order.getQuantity()*order.getProduct().getPrice()}</td>
            </tr>
        </c:forEach>
        <tr><td>Tong</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>

    <input type="submit" value="thanh toán" class="btn btn-success">
</form>
</body>
</center>
</html>
