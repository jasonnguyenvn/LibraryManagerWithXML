<%-- 
    Document   : search
    Created on : Jun 24, 2017, 4:02:50 PM
    Author     : Hau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USERNAME}
        </font>
        <h1>Search Page</h1>
        <form action="search">
            <input type="text" name="txtSearchValue" 
                   value="${param.txtSearchValue}">
            <input type="submit" value="Search" name="btnAction">
        </form>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            
            
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Last name</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                           
                                    <form action="updateRecord"> 
                
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}">
                                </td>
                                <td>
                                    <input type="password" name="txtPassword"
                                           value="${dto.password}" >
                                </td>
                                <td>
                                    ${dto.lastname}
                                </td>
                                <td>
                                    <input type="checkbox" 
                                            name="chkAdmin" value="ADMIN"
                                           <c:if test="${dto.admin}">
                                               checked="checked"
                                           </c:if>   
                                    >
                                </td>
                                <td>
                                    <c:url var="urlRewritting" value="deleteRecord">
                                        <c:param name="pk" value="${dto.username}" />
                                        <c:param name="lastSearchValue" value="${searchValue}" />
                                    </c:url>
                                    <a href="${urlRewritting}">Delete</a>
                                    |
                                    <input type="hidden" name="lastSearchValue"
                                           value="${searchValue}">
                                    <input type="submit" name="btnAction"
                                           value="Update">
                                </td>
                            </tr>
                                    </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
        </c:if>
    </body>
</html>
