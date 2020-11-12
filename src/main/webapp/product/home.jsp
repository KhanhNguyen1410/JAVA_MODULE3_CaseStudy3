<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/4/2020
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Trang chủ</title>
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
<nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="height: 80px">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <i class="fad fa-rocket-launch"></i>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="#"><h1 style="font-family: monospace"><img src="image/logoXu.jpg" alt="Xuka's Shop"></h1></a>
        </li>
    </ul>
    <p>
        <c:if test='${requestScope["message"] != null}'>

            <span class="message">${requestScope["message"]}</span>
        </c:if>
    </p>
    <button type="button" class="btn btn-outline-secondary" style="margin-right: 30px" data-toggle="modal"
            data-target="#exampleModal">
        Login
    </button>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel" style="color: #ff0000;">Đăng nhập</h5>

                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/login">
                        <label>User name</label>
                        <input type="text" class="form-control" name="userName" placeholder="UserName">
                        <label>PassWord</label>
                        <input type="password" class="form-control" name="password" placeholder="passWords">
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Sign In</button>
                            <a href="/customer?action=signUp" class="btn btn-success">Sign Up</a>
<%--                            <button type="submit" name="action" value="signUp" class="btn btn-secondary" data-dismiss="modal">Sign Up</button>--%>
                        </div>

                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <form class="form-inline my-2 my-lg-0" action="/home?action=searchByClient" method="post">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="search">
        <button type="submit" class="btn btn-outline-secondary">TÌM KIẾM</button>
    </form>
</nav>

<div>
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="image/banner2.jpg" alt="First slide" style="width: 50% ; height: 300px">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="image/banner1.jpg" alt="Second slide" style="width: 50% ; height: 300px">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="image/banner3.jpg" alt="Third slide" style="width: 50%; height: 300px">
            </div>
        </div>
    </div>
    <div class="container">
        <p style="margin-top: 50px">SẢN PHẨM <i style="color: red">Xuka's shop</i></p>
        <hr>
        <a href="/home?action=type&type_id=1" class="btn btn-outline-secondary">Nails</a>
        <a href="/home?action=type&type_id=2" class="btn btn-outline-secondary">Lipsticks</a>
        <a href="/home?action=type&type_id=3" class="btn btn-outline-secondary">Perfume</a>
        <a href="/home?action=type&type_id=4" class="btn btn-outline-secondary">Other</a>
        <hr>
        <div class="row">
            <c:forEach var="product" items='${requestScope["product"]}'>
                <div class="col-md-6 col-lg-4" style="margin-bottom: 0px">
                    <div class="single_service">
                        <div class="thumb">

                            <div><img src="image/${product.image}.jpg" style="height: 100px"></div>
                        </div>
                        <div class="service_infor">
                            <p>Tên sản phẩm: <c:out value="${product.name}"/></p>
                            <p>Giá tiền: <c:out value="${product.price}"/></p>
                            <p>Mô tả: <c:out value="${product.desc}"/></p>
                            <div class="row">
                                <div class="col">
                                    <button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#exampleModalUpdate${product.id}">Xem chi tiết</button>
                                    <div class="modal fade" id="exampleModalUpdate${product.id}" tabindex="-1" role="dialog"
                                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <div class="container">
                                                        <h2>Đăng nhập để xem chi tiết và mua hàng</h2>
                                                        <form method="post" action="/login">
                                                            <label>User name</label>
                                                            <input type="text" class="form-control" name="userName" placeholder="account">
                                                            <label>PassWord</label>
                                                            <input type="password" class="form-control" name="password" placeholder="passwords">
                                                            <div class="modal-footer">
                                                                <button type="submit" class="btn btn-primary">Sign In</button>
                                                                <a href="/customer?action=signUp" class="btn btn-success">Sign Up</a>
<%--                                                                <button type="submit" name="action" value="signUp" class="btn btn-secondary" data-dismiss="modal">Sign Up</button>--%>
                                                            </div>
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                                        </form>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </div>
    </div>
</div>
<footer>
    <hr>
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="#">khanh Nobi</a>
        HOTLINE:<a href="$">0345541750</a>
    </div>
</footer>
</body>
</html>
