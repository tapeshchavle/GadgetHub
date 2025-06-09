<jsp:include page="header.jsp"/>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  </head>
  <body>
    <div class="container mt-3 mb-3">
        <div class="row text-center">
            <div class="col-sm-4">
                <div class="box">
                    <img src="images/camera.jpg" alt="">
                    <p>Nikon DSLR</p>
                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusantium quasi doloribus alias minus dolorem possimus illum voluptate molestias</p>
                    <p class="h6">Rs 50000</p>
                    <form action="#">
                        <button type="button" class="btn btn-warning">Add to Cart</button>
                        <button type="button" class="btn btn-primary">Buy Now</button>
                        <button type="button" class="btn btn-danger">Remove From Cart</button>
                        <button type="button" class="btn btn-success">Checkout</button>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  </body>
</html>