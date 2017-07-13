<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<c:import url="WEB-INF/BookInfo.xsl"  var="xsldoc" charEncoding="UTF-8" />
<!DOCTYPE html>
<x:transform xml="${requestScope.BOOKINFO}" xslt="${xsldoc}" >
    <x:param name="userXml" value="" />
</x:transform>
