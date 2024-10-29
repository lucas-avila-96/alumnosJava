<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Docentes</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Listado de Docentes</h2>

        <table class="table table-bordered table-hover shadow-sm bg-white">
            <thead class="thead-dark">
                <tr>
                    <th>Nombre del Docente</th>
                    <th>Materias que Dicta</th>
                    <th>Ver Alumnos que Rindieron</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="docente" items="${lista}">
                    <tr>
                        <td>${docente.nombre}</td>
                        <td>
                            <c:forEach var="materia" items="${docente.materiaCollection}">
                                ${materia.nombre}<br>
                            </c:forEach>
                        </td>
                        <td>
<a href="<c:url value='/ManejadorDocente' />?action=ListarAlumnosRendidos&docenteId=${docente.iddocente}">
    Ver Alumnos
</a>

                        </td>
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
