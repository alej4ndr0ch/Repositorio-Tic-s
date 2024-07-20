<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tienda De Ropa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="index-style.css" rel="stylesheet" type="text/css">
</head>
<body>
  <nav class="navbar bg-body-tertiary fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <img src="/src/assets/image/Logo.png" alt="Kinal-Shop Logo" style="height: 40px; margin-right: 25px;">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Menu</h5>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
          <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
            <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="/src/productosMasculino/producto-masculino.jsp">Hombres</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="/src/productosFemeninos/productos-femenino.jsp">Mujeres</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="/src/categoria-productos/categoria-productos.html">Categoria Productos</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="/src/cliente/cliente.jsp">Clientes</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="/src/distribuidor/distribuidor.jsp">Distribuidor</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="/src/direccion/direccion.jsp">Direccion</a>
                </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
      <div id="carouselExampleCaptions" class="carousel slide">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div style="text-align: center;">
              <img src="/src/assets/image/hombre-ropa.jpg"  alt="Kinal-Shop-ropa-hombre" width="630">
            </div>
            <div class="carousel-caption d-none d-md-block">
              <h5>Hombre</h5>
              <p>Colección de moda de Kinal-Shop Guatemala para hombre.</p>
            </div>
          </div>
          <div class="carousel-item">
            <div style="text-align: center;">
              <img src="/src/assets/image/mujer-ropa.jpg" alt="Kinal-Shop-ropa-mujer" width="630">
            </div>
            <div class="carousel-caption d-none d-md-block">
              <h5>Mujer</h5>
              <p>Colección de moda de Kinal-Shop Guatemala para mujer.</p>
            </div>
          </div>
          <div class="carousel-item">
            <div style="text-align: center;">
              <img src="/src/assets/image/accesorio-ropa.jpg" alt="Kinal-Shop-ropa-accesorio" width="630">
            </div>
            <div class="carousel-caption d-none d-md-block">
              <h5>Accesorio</h5>
              <p>Colección de moda de Kinal-Shop Guatemala accesorio.</p>
            </div>
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
      <footer class="footer">
        <p>Kinal-Shop 2024&copy;</p>
      </footer>  
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
