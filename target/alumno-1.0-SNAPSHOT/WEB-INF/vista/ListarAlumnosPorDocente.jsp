<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Listado de Alumnos que rindieron</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Alumnos que han rendido la materia</h2>
        
        <table class="table table-bordered table-hover shadow-sm bg-white">
            <thead class="thead-dark">
                <tr>
                    <th>Nombre del Alumno</th>
                    <th>Materia Rendida</th>
                    <th>Docente</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${alumnosConMaterias}">
                    <tr>
                        <td>${record[0]}</td> <!-- Nombre del Alumno -->
                        <td>${record[1]}</td> <!-- Nombre de la Materia -->
                        <td>${record[2]}</td> <!-- Nombre del Docente -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
