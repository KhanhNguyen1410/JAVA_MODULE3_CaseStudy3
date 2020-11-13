<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/12/2020
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<center>
<head>
    <title>Lay mat khau</title>
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
<%--<form action="/customer?action=forgotPass" method="post">--%>
<%--    <label>Nhập tên tài khoản</label> <br>--%>
<%--    <input type="text" name="name">--%>
<%--    <br>--%>
<%--    <label>Nhập số điện thoại</label> <br>--%>
<%--    <input type="text" name="phone">--%>
<%--    <br>--%>
<%--    <input type="submit" value="Lấy mật khẩu">--%>
<%--</form>--%>
<div class="container" style="width: 500px;">
<form action="/customer?action=forgotPass" method="post">
    <div class="form-group">
        <label for="exampleInputEmail1">Tên tài khoản</label>
        <input type="text" name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="nhập tên tài khoản">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Số điện thoại</label>
        <input type="text" name="phone" class="form-control" id="exampleInputPassword1" placeholder="số điện thoại">
    </div>
    <button type="submit" class="btn btn-primary">Lấy mật khẩu</button>
</form>
<p>
    <c:if test='${requestScope["message"] != null}'>

        <span class="message">Mật khẩu của bạn là: ${requestScope["message"]}</span>
    </c:if>
</p>
<a href="/home" class="btn btn-danger">Trang chủ</a>
</div>
</body>
</center>
</html>
