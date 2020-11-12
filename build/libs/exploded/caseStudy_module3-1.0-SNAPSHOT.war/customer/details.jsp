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
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
    <p>1</p>
</div>
</body>
</html>
