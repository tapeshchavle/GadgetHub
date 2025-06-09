<%@page import="in.gadgethub.dao.impl.OrderDaoImpl"%>
<%@page import="in.gadgethub.pojo.ProductPojo"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Bootstrap demo</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
  </head>
  <body>
      <%
          List<ProductPojo> products=(List<ProductPojo>)request.getAttribute("products");
      %>
      <jsp:include page="header.jsp"/>
    <div class="text-primary text-center h3 mt-3 mb-3">Product Stock</div>
    <div class="container-fluid">
      <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
              <th>Product Image</th>
              <th>Product ID</th>
              <th>Product Name</th>
              <th>Product Type</th>
              <th>Price</th>
              <th>Sold Qty</th>
              <th>Stock Qty</th>
              <th colspan="2" class="text-center">Action</th>
            </tr>
            </thead>
          <%
              OrderDaoImpl orderDao=new OrderDaoImpl();
              for(ProductPojo product:products){
              %>
              
           <tbody>
            <tr>
              <td>
                <img src="./ShowImageServlet?pid=<%=product.getProdId() %>" alt="product" width="100px" height="100px" />
              </td>
              <td><a href="./updateProduct.jsp?prodid=<%=product.getProdId() %>"><%=product.getProdId() %></a></td>
              <%
                  String name=product.getProdName();
                  name=name.substring(0,Math.min(name.length(),25));
              %>
              <td><%=name %></td>
              <td><%=product.getProdType().toUpperCase() %></td>
              <td><%=product.getProdPrice() %></td>
              <td><%=orderDao.getSoldQuantity(product.getProdId()) %></td>
              <td><%=product.getProdQuantity() %></td>
              
              <td>
                  <!--form ke andar se query stirng nhi pass karte-->
                 <!--<form action="./UpdateProductByIdServlet?prodid="+<%=product.getProdQuantity() %> method="get">
                  <button type="submit" class="btn btn-primary">Update</button>
                </form>
                 -->
                 <a href="./UpdateProductByIdServlet?prodid=<%=product.getProdId() %>"> 
                     <button type="submit" class="btn btn-primary">Update</button>
                 </a>
               
              </td>
              <td>
                <a href="./RemoveProductServlet?prodid=<%=product.getProdId()%>"> 
                     <button type="submit" class="btn btn-danger">Remove</button>
                 </a>
              </td>
            </tr>
            
          </tbody>
              <%
              }
              if(products.isEmpty()){
          %>
          <tr>
              <td colspan="8" class="text-center">No items available</td>
            </tr>
            <%
                }
            %>
         
        </table>
      </div>
    </div>
    <jsp:include page="footer.jsp"/>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
