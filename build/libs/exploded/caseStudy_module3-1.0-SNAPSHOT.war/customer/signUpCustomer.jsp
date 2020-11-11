<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/8/2020
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<center>
<body>
<p>
    <c:if test='${requestScope["message"] != null}'>

        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
    <fieldset>
        <legend>Sign Up Account</legend>
        <table>
            <tr>
                <td>Id</td>
                <td><input type="number" name="id"></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Pass:</td>
                <td><input type="text" name="pass"></td>
            </tr>
            <tr>
                <td>Pass:</td>
                <td><input type="text" name="pass2"></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address"></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="phone"></td>
            </tr>
            <tr>
                <td><a href="/home" class="btn btn-success">back to home</a></td>
                <td><input type="submit" value="create"></td>
            </tr>
        </table>
    </fieldset>
</form>

</body>
</center>
</html>
