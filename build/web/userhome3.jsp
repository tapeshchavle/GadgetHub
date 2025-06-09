

<%@page import="in.gadgethub.pojo.ProductPojo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<ProductPojo> list=(List<ProductPojo>)session.getAttribute("prod");
        %>
        <h1><%=list.toString()%></h1>
    </body>
</html>
