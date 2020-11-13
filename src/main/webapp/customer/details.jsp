<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/12/2020
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Details</title>
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
<body>
<style type="text/css">
    #test {
        border: 1px #243024;
        width: 1000px;
        height: 150px;
        overflow-x: hidden;
        overflow-y: auto;
    }
</style>
<c:set var="customer" scope="session" value='${sessionScope.customer}'/>
<div class="thumb">
    <div><img src="image/${product.image}.jpg" style="height: 100px"></div>
</div>
<div class="service_infor">
    <p>Tên sản phẩm: <c:out value="${product.name}"/></p>
    <p>Giá tiền: <c:out value="${product.price}"/></p>
    <p>Nhãn hiệu: <c:out value="${product.brand.brand}"/></p>
    <p>Xuất xứ: <c:out value="${product.origin.country}"/></p>
    <p>Mô tả: <c:out value="${product.desc}"/></p>
</div>
<div id="test">
    <h2>Bình Luận</h2>
    <c:forEach items='${requestScope["comments"]}' var="comment">
        <label>${comment.getCustomer().getName()}</label> <br>
        <c:out value="${comment.getContent()}"/>
<%--        <input type="text" value="${comment.getContent()}">--%>
    </c:forEach>
</div>
<form method="post" action="/customer?action=comment">
    <input type="hidden" name="id" value="1">
    <input type="text" name="comment">
    <input type="hidden" name="customer_id" value="${customer.getId()}">
    <input type="hidden" name="product_id" value="${product.getId()}">
    <input type="submit" value="Bình Luận" class="">
</form>
</body>
</html>
