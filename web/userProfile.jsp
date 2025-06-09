<%@page import="in.gadgethub.pojo.UserPojo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Profile Details</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/27e2ddb8ff.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="mycss.css">
    </head>
    <body style="background-color: #E6F9E6;">

        <%
            UserPojo userPojo = (UserPojo) request.getAttribute("user");
            String resp = (String) request.getAttribute("res");
            ContactDaoImpl contact = new ContactDaoImpl();
            boolean r = contact.checkAlreadyExistOrNot(userPojo.getUseremail());
            String s = "";
            s = (r == false) ? "d-none" : "";

        %>

        <jsp:include page="header.jsp" />

        <div class="container mt-5 user-profile">
            <div class="row">
                <div class="col mt-3">
                    <nav aria-label="breadcrumb" class="bg-warning rounded-3 p-3 mb-4">
                        <ol class="breadcrumb mb-0">
                            <li class="breadcrumb-item"><a href="./LandingServlet">Home</a></li>
                            <li class="breadcrumb-item"><a href="./UserProfileServlet">User
                                    Profile</a></li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-4">
                    <div class="card mb-4">
                        <div class="card-body text-center">
                            <img src="images/profile.jpg" class="rounded-circle img-fluid w-25"
                                 >
                            <h5 class="my-3">
                                Hello
                                <%=userPojo.getUsername()%>
                                here!!
                            </h5>

                        </div>
                    </div>
                    <div class="card mb-4 mb-4">
                        <div class="card-body p-0">
                            <ul class="list-group list-group-flush rounded-3">

                                <li
                                    class="text-center list-group-item d-flex justify-content-between align-items-center p-3">
                                    <h3>My Profile</h3>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Full Name</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0"><%=userPojo.getUsername()%></p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Email</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0"><%=userPojo.getUseremail()%>
                                    </p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Phone</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0"><%=userPojo.getMobile()%>
                                    </p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Address</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0"><%=userPojo.getAddress()%>
                                    </p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">PinCode</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0"><%=userPojo.getPinCode()%>
                                    </p>
                                </div>
                            </div>
                            <hr>



                            <!-- comment -->
                            <form action="./UpdateContactServlet">
                                <div class="row <%=s%>">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Your Response</p>
                                    </div>
                                    <input type="text" class="form-control" name="username" value="<%=userPojo.getUsername()%>" hidden>                         



                                    <input type="email" class="form-control" name="useremail" value="<%=userPojo.getUseremail()%>" hidden>                       

                                    <div class="col-sm-7">
                                        <input type="text" value="<%=resp%>" name="message" class="text-muted form-control"/>                                        
                                    </div>

                                    <div class="col-sm-2 d-flex">

                                        <div class="col-sm-8"> <input type="submit" value="Edit"> </div>
                                        </form>

                                        <div class="col-sm-4"><a href="UpdateContactServlet?useremail=<%= userPojo.getUseremail()%>&username=<%= userPojo.getUsername()%>&del=1"><i class="fa-solid fa-trash"></i></a></div>

 
                                    </div>
                                </div>
                            
                            <!--<!-- comment -->


                    </div>
                </div>
            </div>
        </div>
    </div>

    <br>
    <br>
    <br>

    <%@ include file="footer.jsp"%>

</body>
</html>
