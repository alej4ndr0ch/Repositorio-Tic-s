<%-- 
    Document   : producto-masculino
    Created on : 16/07/2024, 06:04:58 PM
    Author     : aleja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ProductoMasculino</title>
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
    </body>
</html>
