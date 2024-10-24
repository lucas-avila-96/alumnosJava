<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Carreras</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h1 class="mb-4">Carreras de la Facultad: <span class="text-primary">${facultad.nombre}</span></h1>

    <!-- Verificamos si la facultad tiene carreras asociadas -->
    <c:choose>
        <c:when test="${not empty facultad.carreraCollection}">
            <div class="table-responsive">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nombre de la Carrera</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="carrera" items="${facultad.carreraCollection}">
                            <tr>
                                <td>${carrera.idcarrera}</td>
                                <td>${carrera.nombre}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning" role="alert">
                No hay carreras asociadas a esta facultad.
            </div>
        </c:otherwise>
    </c:choose>

    <a href="index.jsp" class="btn btn-secondary mt-3">Volver al inicio</a>
</div>

<!-- Bootstrap JS y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
