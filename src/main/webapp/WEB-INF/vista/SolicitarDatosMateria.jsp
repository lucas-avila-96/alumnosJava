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
                    <h1 class="card-title">Nueva Materia</h1>
                </div>
                <div class="card-body">
                    <form action="AgregarMateria" method="post">
                        <!-- Campo para Nombre -->
                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre del alumno" required>
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
