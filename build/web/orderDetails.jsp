<%@page import="in.gadgethub.pojo.OrderDetailsPojo"%>
<%@page import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
    <title>Order Details</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="mycss.css">
</head>
<body style="background-color: #E6F9E6;">

    <%
        String paymentStatus=(String)request.getAttribute("paymentStatus");
        List<OrderDetailsPojo> orders=(List<OrderDetailsPojo>)request.getAttribute("orders");
       
    %>

    <jsp:include page="header.jsp" />

    <div class="text-center text-primary h3 m-3">Order Details</div>

    <!-- Display Payment Status -->
    <%
          if(paymentStatus!=null && paymentStatus.equals("Order Placement Fails")) {
    %>
        <div id="message" class="alert alert-danger text-center">
            <%=paymentStatus%>
        </div>
       <%
        }else if(paymentStatus!=null){
        %>
        <div id="message" class="alert alert-info text-center">
            <%=paymentStatus%>
        </div>
        <%
    }
    %>

    <!-- Start of Product Items List -->
    <div class="container">
        <div class="table-responsive ">
            <table class="table table-hover table-sm">
                <thead style="background-color: black; color: white; font-size: 14px; font-weight: bold;">
                    <tr>
                        <th>Picture</th>
                        <th>Product Name</th>
                        <th>Order ID</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Time</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                     if(orders!=null && !orders.isEmpty()) {
                            for(OrderDetailsPojo order:orders){
                            
                    %>
                            <tr>
                                <td><img src="./ShowImageServlet?pid=<%=order.getProdId() %>" style="width: 50px; height: 50px;"></td>
                                <td><%= order.getProdName()  %></td>
                                <td><a href="./OneProduct"><%= order.getOrderId() %></a></td>
                                <td><%=order.getQty() %></td>
                                <td><%=order.getAmount() %></td>
                                <td><%= order.getTime() %></td>
                                <td class="text-success"><%=(order.getShipped()==0)?"ORDER_PLACED":"ORDER_SUCESS"%></td>
                            </tr>
                    <%
                        }
                     }else{
                    %>
                        <tr>
                            <td colspan="7" class="text-center">No orders found.</td>
                        </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>
        </div>
    </div>
    <!-- End of Product Items List -->

    <%@ include file="footer.jsp" %>
</body>
</html>
