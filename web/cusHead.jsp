<%-- 
    Document   : cusHead
    Created on : 21-Dec-2024, 6:17:57 pm
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <!--fontawesome link-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  
    </head>
    <body>
         <!--Customer navbar starts here-->
      <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container-fluid">
            <a href="userHome.jsp" class="navbar-brand">GadgetHub</a>
            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#mynav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="mynav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a href="userHome.jsp" class="nav-link">Products</a>
                    </li>
                    <li class="nav-item" style="display: flex;">
                        <div><a href="cartDetails.jsp" class="nav-link">Cart</a></div>
                        <div style="margin:-12px 0 0 -15px;">
                            <a href="userHome.jsp" class="nav-link">
                            <i class="fa fa-cart-shopping " style="font-size: 10px; background-color: black;margin: 0;padding:0px;"></i></a>
                            <div>
                    </li>
                    
                    <li class="nav-item">
                        <a href="#" class="nav-link">Orders</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">Logout</a>
                    </li>

                </ul>

            </div>
        </div>

      </nav>
      <!--customer navbar ends here-->
    </body>
</html>
