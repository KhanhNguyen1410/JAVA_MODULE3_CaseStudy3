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
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <i class="fad fa-rocket-launch"></i>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="#"><h1 style="font-family: monospace"><img src="image/logoXu.jpg" alt="Xuka's Shop"></h1></a>
        </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="/home" method="post">
        <input type="submit" value="Đăng xuất" class="btn btn-outline-secondary">
    </form>

    <!-- Modal -->
    <form class="form-inline my-2 my-lg-0" action="/home?action=searchByCustomer" method="post">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="search">
        <button type="submit" class="btn btn-outline-secondary">TÌM KIẾM</button>
    </form>
</nav>

<div>
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="image/banner1.jpg" alt="First slide" style="width: 50% ; height: 200px">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="image/banner2.jpg" alt="Second slide"
                     style="width: 50% ; height: 200px">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="image/banner3.jpg" alt="Third slide" style="width: 50%; height: 200px">
            </div>
        </div>
    </div>
    <div class="container">
        <p style="margin-top: 50px">SẢN PHẨM <i style="color: red">Xuka's shop</i></p>
        <c:set var="customer" scope="session" value="${sessionScope.customer}"/>
        <c:out value="Have a good day ${customer.getName()}"/>
        <br>
        <a href="/customer?action=profile" class="btn btn-success">Tài khoản</a>
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
                            <p>Nhãn hiệu: <c:out value="${product.brand.brand}"/></p>
                            <p>Xuất xứ: <c:out value="${product.origin.country}"/></p>
                            <p>Mô tả: <c:out value="${product.desc}"/></p>
                            <div class="row">
                                <a href="/customer?action=details&id=${product.id}" class="btn btn-outline-secondary">Details</a>
                                <button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#exampleModalUpdate${product.id}">Thêm vào giỏ</button>
                                <div class="modal fade" id="exampleModalUpdate${product.id}" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <div class="container">
                                                    <h2>Nhập số lượng và Thêm vào giỏ</h2>
                                                    <form method="post" action="/bill?action=addToCart">
<%--                                                        <input type="hidden" name="bill_id" value="${bill.getId()}">--%>
                                                        <input type="hidden" name="product_id" value="${product.getId()}">
<%--                                                        <input type="text" class="form-control" name="order_id"  value="${product.id}">--%>
                                                        <label>Product</label>
                                                        <input type="text" class="form-control" disabled  value="${product.name}">
                                                        <label>Price</label>
                                                        <input type="text" class="form-control"  disabled value="${product.price}">
                                                        <label>Ảnh</label>
                                                        <input type="text" class="form-control"  disabled value="${product.image}">
                                                        <img src="image/${product.image}.jpg" alt="image product">
                                                        <br>
                                                        <label>Số lượng</label>
                                                        <input type="number" class="form-control" name="quantity" value="1">
                                                        <div class="modal-footer">
                                                            <input type="submit" value="Thêm vào giỏ">
                                                        </div>
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

            </c:forEach>
        </div>
    </div>
</div>
<footer style="background-color: burlywood">
    <hr>
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="#">khanh Nobi</a>
        HOTLINE:<a href="$">0345541750</a>
        <a href="https://www.facebook.com/kodoi.love/"><img src="image/face.jpg" style="height: 50px ; width: 50px" alt=""></a>
        <a href="https://www.youtube.com/"><img src="image/youtube.jpg" style="height: 50px ; width: 50px" alt=""></a>
        <a href="https://codegym.vn/"><img src="image/codegym.jpg" style="height: 50px ; width: 50px" alt=""></a>
    </div>
</footer>
</body>
</html>
