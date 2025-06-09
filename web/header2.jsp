<!doctype html>
<html lang="en">
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

    <!--navbar 1 starts here-->
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container-fluid">
            <a href="index.jsp" class="navbar-brand">GadgetHub

            </a>
            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#mynav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="mynav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
                        >Category</a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#" class="dropdown-item">Mobile</a>
                            </li>
                            <li>
                                <a href="#" class="dropdown-item">TV</a>
                            </li>
                            <li>
                                <a href="#" class="dropdown-item">Camera</a>
                            </li>
                            <li>
                                <a href="#" class="dropdown-item">Laptop</a>
                            </li>
                            <li>
                                <a href="#" class="dropdown-item">Fridge</a>
                            </li>
                            <li>
                                <a href="#" class="dropdown-item">Cooler</a>
                            </li>
                        </ul>

                    </li>
                    <li class="nav-item ">
                        <a href="index.jsp" class="nav-link">Products</a>
                    </li>
                    <li class="nav-item">
                        <a href="login.jsp" class="nav-link">Login</a>
                    </li>
                    <li class="nav-item">
                        <a href="register.jsp" class="nav-link">Register</a>
                    </li>

                </ul>
            </div>

        </div>
        


    </nav>
    <br><br>

    <!--navbar 1 ends here-->
    <!--admin navbar starts here--> 
 
    <nav class="navbar navbar-expand-lg navbar-custom"> 
        <div class="container-fluid"> 
          <a href="userHome.jsp" class="navbar-brand"> 
            GadgetHub 
          </a> 
   
          <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#mynav"> 
            <span class="navbar-toggler-icon"></span> 
          </button> 
          <div class="collapse navbar-collapse" id="mynav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
                    >Category</a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#" class="dropdown-item">Mobile</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-item">TV</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-item">Camera</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-item">Laptop</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-item">Fridge</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-item">Cooler</a>
                        </li>
                    </ul>

                </li>
                <li class="nav-item">
                    <a href="adminStock.jsp" class="nav-link">Stocks</a>
                </li>
                <li class="nav-item">
                    <a href="adminShipped.jsp" class="nav-link">Shipped</a>
                </li>
                <li class="nav-item">
                    <a href="unshippedorder.jsp" class="nav-link">Orders</a>
                </li>
                
                <li class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
                    >UpdateItem</a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#" class="dropdown-item">Mobile</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-item">TV</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-item">Camera</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-item">Laptop</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-item">Fridge</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-item">Cooler</a>
                        </li>
                    </ul>

                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">Logout</a>
                </li>

            </ul>
        </div>
        </div> 
      </nav> 
      <br><br>
   
      <!--admin navbar ends here--> 

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

      <!--Searchbar Starts here-->
      <div class="container-fluid bg-warning text-center p-3">
        <h2>GadgetHub</h2>          
        <p>We specialize in Electronics</p>
        <form action="LandingServlet">
            <div class="input-group">
                <input type="text" class="form-control" name="search" placeholder="Search items...">
                <input type="submit" class="btn btn-primary" value="Search">
            </div>
        </form>
      </div>
      <!--Searchbar Starts here-->
      
    



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  </body>
</html>
