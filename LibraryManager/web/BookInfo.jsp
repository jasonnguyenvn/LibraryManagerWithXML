<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<c:import url="WEB-INF/BookInfo.xsl"  var="xsldoc" charEncoding="UTF-8" />
<!DOCTYPE html>

<c:if test="${not empty sessionScope.USERINFO }">
    <x:parse var="USERINFO" xml="${sessionScope.USERINFO}" />
    <x:transform xml="${requestScope.BOOKINFO}" xslt="${xsldoc}" >
           <x:param name="userFullname">
               <x:out select="$USERINFO/user/fullname" />
           </x:param>
    </x:transform>
</c:if>
<c:if test="${empty sessionScope.USERINFO }">
    <x:transform xml="${requestScope.BOOKINFO}" xslt="${xsldoc}" />
    
</c:if>