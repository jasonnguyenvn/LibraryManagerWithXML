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
    </head>
    <body>
        <div id="searchBox">
            <form action="searchBook"  accept-charset="UTF-8" method="post">
                <div id="searchControlCont">
                    <input type="text" name="txtSearchValue" id="txtSearchValue">
                    <input type="submit" name="btnAction" value="Tìm">
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
        <div id="main">
            <h1>Kết quả tìm kiếm</h1>
            <c:import url="WEB-INF/BookSearch.xsl"  var="xsldoc" charEncoding="UTF-8" />
            <x:transform xml="${requestScope.SEARCHRESULT}" xslt="${xsldoc}" />
        </div>
    </body>
</html>
