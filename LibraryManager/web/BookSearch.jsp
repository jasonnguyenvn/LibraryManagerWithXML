<%-- 
    Document   : BookSearch
    Created on : Jul 12, 2017, 1:10:37 PM
    Author     : Hau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kết quả tìm kiếm</title>
        <link rel="stylesheet" href="main.css" />
    </head>
    <body>
        <div id="nav-bar">
            <div class="nav-bar-wrapper">
            <div id="logo">
                <a href="./">
                Trang chủ
                </a>
            </div>
            </div>
        </div>
        <div id="searchBox">
            <form action="searchBook"  accept-charset="UTF-8" method="get">
                <div id="searchControlCont">
                    <input type="text" name="txtSearchValue" value="${param.txtSearchValue}">
                    <input type="submit" name="btnAction" value="Tìm">
                </div>
                <div id="searchFilterCont">
                Tìm kiếm bằng:
                    <select name="cbxSearchBy" required>
                        <option value="booktitle"
                            <c:if test="${param.cbxSearchBy == 'booktitle'}">selected="selected"</c:if>
                        >Tựa sách</option>
                        <option value="author"
                            <c:if test="${param.cbxSearchBy == 'author'}">selected="selected"</c:if>
                        >Tác giả</option>
                        <option value="publisher"
                            <c:if test="${param.cbxSearchBy == 'publisher'}">selected="selected"</c:if>
                        >Nhà xuất bản</option>
                        <option value="year"
                            <c:if test="${param.cbxSearchBy == 'year'}">selected="selected"</c:if>
                        >Năm xuất bản</option>
                     </select>
                </div>
            </form>
        </div>
        <div id="main">
            <h1>Kết quả tìm kiếm</h1>
            <c:import url="WEB-INF/BookSearch.xsl"  var="xsldoc" charEncoding="UTF-8" />
            Từ khóa tìm kiếm <b>"${param.txtSearchValue}"</b>
            <x:transform xml="${requestScope.SEARCHRESULT}" xslt="${xsldoc}" />
        </div> 

    </body>
</html>
