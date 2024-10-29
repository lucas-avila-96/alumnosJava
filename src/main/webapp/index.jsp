<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Alumnos</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <style>
            .carousel-inner > .item > img {
                margin: auto;
            }
        </style>
    </head>	
    <body>
        <div class="container">
            <!-- Header Section -->
            <div class="row">
                <div class="col-xs-12 text-center">
                    <img src="imagen/humo.png" alt="Logo" class="img-responsive" style="display:inline-block; width:90px; height:85px;">
                    <h1>
                        <a href="#" style="text-decoration: none;">
                            <span style="letter-spacing:0.2em;">SISTEMA</span><br>
                            <span style="letter-spacing:0.38em; color:#28b2a4;">ALUMNOS</span>
                        </a>
                    </h1>
                </div>
            </div>
            <!-- Social Media Icons -->
            <div class="row text-center">
                <div class="col-xs-12">
                    <a href="#"><img src="imagen/facenegro.png" alt="Facebook" style="width: 20px; height: 20px; margin-right: 10px;"></a>
                    <a href="#"><img src="imagen/twitnegro.png" alt="Twitter" style="width: 20px; height: 20px;"></a>
                </div>
            </div>

            <!-- Navigation Menu -->
            <nav class="navbar navbar-default" style="margin-top: 30px;">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">Inicio</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Alumnos <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="ListarAlumno">Listar Alumnos</a></li>
                                <li><a href="SolicitarDatosAlumno">Solicitar Datos</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Facultades <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <c:forEach var="facultad" items="${facultades}">
                                    <li><a href="Facultad?codigo=${facultad.idfacultad}">${facultad.nombre}</a></li>
                                    </c:forEach>
                                    <li><a href="SolicitarDatosFacultad">Agregar Facultad</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Carreras <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="SolicitarDatosCarrera">Solicitar Datos</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Materias <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="ListarMateria">Listar Materias</a></li>
                                <li><a href="SolicitarDatosMateria">Solicitar Datos</a></li>
                                <li><a href="ListarAlumnosConMaterias">Listar Alumnos con Materias</a></li>
                            </ul>
                        </li>
                        
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Docentes <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="ListarDocente">Listar Docentes</a></li>
                                <li><a href="SolicitarDatosDocente">Solicitar Datos</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

            <!-- Carousel Section -->
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="imagen/imagen1.jpg" alt="Imagen 1" class="img-responsive">
                    </div>
                    <div class="item">
                        <img src="imagen/imagen2.jpg" alt="Imagen 2" class="img-responsive">
                    </div>
                    <div class="item">
                        <img src="imagen/imagen3.jpg" alt="Imagen 3" class="img-responsive">
                    </div>
                    <div class="item">
                        <img src="imagen/imagen5.jpg" alt="Imagen 4" class="img-responsive">
                    </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Anterior</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Siguiente</span>
                </a>
            </div>

            <!-- Footer Section -->
            <footer class="row" style="margin-top: 50px;">
                <div class="col-xs-12 text-right">
                    <p>© 2020 - Catedra Tecnologias Web - 
                        <a href="http://www.unsj.edu.ar" target="_blank">unsj</a>
                    </p>
                </div>
            </footer>
        </div>
    </body>
</html>
