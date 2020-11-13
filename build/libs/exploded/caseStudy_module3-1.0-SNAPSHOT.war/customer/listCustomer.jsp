<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/8/2020
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Customer</title>
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
<h1>Customer</h1>
<p>
    <a href="/customer?action=create" class="btn btn-success">Create new customer</a>
</p>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Pass</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items='${requestScope["customers"]}' var="customer">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.pass}</td>
            <td>${customer.address}</td>
            <td>${customer.phone}</td>
            <td>${customer.status}</td>
            <td><button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#exampleModalUpdate${customer.id}">Disable</button>
                <div class="modal fade" id="exampleModalUpdate${customer.id}" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="container">
                                    <h2>ARE YOU SURE?</h2>
                                    <form method="post" action="/customer?action=delete&id=${customer.id}">
                                        <label>ID:</label>
                                        <input type="hidden" class="form-control" name="id" value="${customer.id}">
                                        <label>Name</label>
                                        <input type="text" class="form-control" name="name" value="${customer.name}">
                                        <label>Pass</label>
                                        <input type="password" class="form-control" name="price" value="${customer.pass}">
                                        <label>Address</label>
                                        <input type="text" class="form-control" name="image" value="${customer.address}">
                                        <label>Phone</label>
                                        <input type="text" class="form-control" name="type" value="${customer.phone}">
                                        <div class="modal-footer">
                                            <input type="submit" value="Disable">
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
                <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#exampleModalUpdate${customer.name}">Able</button>
                <div class="modal fade" id="exampleModalUpdate${customer.name}" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="container">
                                    <h2>ARE YOU SURE?</h2>
                                    <form method="post" action="/customer?action=able&id=${customer.id}">
                                        <label>ID:</label>
                                        <input type="hidden" class="form-control" name="id" value="${customer.id}">
                                        <label>Name</label>
                                        <input type="text" class="form-control" name="name" value="${customer.name}">
                                        <label>Pass</label>
                                        <input type="password" class="form-control" name="price" value="${customer.pass}">
                                        <label>Address</label>
                                        <input type="text" class="form-control" name="image" value="${customer.address}">
                                        <label>Phone</label>
                                        <input type="text" class="form-control" name="type" value="${customer.phone}">
                                        <div class="modal-footer">
                                            <input type="submit" value="Able">
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
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</center>
</html>
