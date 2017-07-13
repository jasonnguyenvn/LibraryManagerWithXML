<%-- 
    Document   : login
    Created on : Jul 14, 2017, 5:19:13 AM
    Author     : Hau
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng nhập</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    <body>
        <h1>Trang đăng nhập</h1>
        <c:if test="${not empty sessionScope.USERINFO }">
            <x:parse var="USERINFO" xml="${sessionScope.USERINFO}" />
            Xin chào, 
            <b><x:out select="$USERINFO//fullname"/></b>.<br/>
            Bạn đã đăng nhập. <br/>
            <a href="./">Click vào đây để về trang chủ!</a>
        </c:if>

        <c:if test="${empty sessionScope.USERINFO }">
            <form action="login" method="POST">
                Tên tài khoản <input type="text" name="txtUsername" value=""><br>
                Mật khẩu <input type="password" name="txtPassword" value="">
                <input type="submit" value="Login" name="btnAction">
                <input type="reset" value="Reset">
            </form><br>
        </c:if>
    </body>
</html>
