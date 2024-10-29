<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Solicitar Datos del Docente</title>
</head>
<body>
    <h2>Agregar Nuevo Docente</h2>

    <form action="AgregarDocente" method="post">
        <label for="nombre">Nombre del Docente:</label>
        <input type="text" id="nombre" name="nombre" required>
        <br><br>

        <label>Seleccione las Materias:</label>
        <br>
        <c:forEach var="materia" items="${listaMaterias}">
            <input type="checkbox" name="materias" value="${materia.idmateria}">
            ${materia.nombre}<br>
        </c:forEach>

        <br>
        <input type="submit" value="Agregar Docente">
    </form>
</body>
</html>
