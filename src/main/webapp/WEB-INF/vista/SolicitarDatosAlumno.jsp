<%-- 
    Document   : Agregar Alumno con Selección de Carrera
    Created on : 25-oct-2010, 14:09:01
    Author     : Monti
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Agregar Alumno</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="card">
                <div class="card-header">
                    <h1 class="card-title">Nuevo Alumno</h1>
                </div>
                <div class="card-body">
                    <form action="AgregarAlumno" method="post">
                        <!-- Campo para Nombre -->
                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre del alumno" required>
                        </div>

                        <!-- Campo para Registro -->
                        <div class="mb-3">
                            <label for="registro" class="form-label">Registro</label>
                            <input type="text" class="form-control" id="registro" name="registro" placeholder="Ingrese el registro del alumno" required>
                        </div>

                        <!-- Dropdown para Selección de Carrera -->
                        <div class="mb-3">
                            <label for="carrera" class="form-label">Carrera</label>
                            <select class="form-select" id="carrera" name="carrera" required>
                                <option value="" disabled selected>Seleccione una carrera</option>
                                <c:forEach var="carrera" items="${listaCarreras}">
                                    <option value="${carrera.idcarrera}">
                                        ${carrera.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- Botones -->
                        <button type="submit" class="btn btn-primary">Crear</button>
                        <a href="javascript:window.history.back();" class="btn btn-secondary">Volver atrás</a>
                        <a href="./index.jsp" class="btn btn-secondary">Volver</a>
                    </form>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
