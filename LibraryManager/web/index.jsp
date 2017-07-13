<%-- 
    Document   : index
    Created on : Jun 24, 2017, 3:08:52 PM
    Author     : Hau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Chào mừng đến với Library Manager</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="main.css" />
    </head>
    <body>
        <div id="banner">
            <c:if test="${not empty sessionScope.USERINFO }">
                <x:parse var="USERINFO" xml="${sessionScope.USERINFO}" />
                Xin chào, 
                <b><x:out select="$USERINFO//fullname"/></b>
            </c:if>
            <c:if test="${empty sessionScope.USERINFO }">
                <a href="login.jsp">Đăng nhập</a>
            </c:if>
        </div>
        <div id="home-main-container">
            <div id="searchBox">
                <h1>Library Manager v0.1</h1>
                <h3>Hôm nay bạn muốn đọc gì?</h3>
                <form action="searchBook"  accept-charset="UTF-8" method="get">
                    <div id="searchControlCont">
                        <input class="search-value" type="text" name="txtSearchValue" >
                        <input type="submit" name="btnAction" value="Tìm sách">
                    </div>
                    <div id="searchFilterCont">
                    Tìm kiếm bằng:
                        <select name="cbxSearchBy" required>
                            <option value="booktitle">Tựa sách</option>
                            <option value="author">Tác giả</option>
                            <option value="publisher">Nhà xuất bản</option>
                            <option value="year">Năm xuất bản</option>
                         </select>
                    </div>
                </form>
            </div>
        </div>
        
    </body>
</html>

