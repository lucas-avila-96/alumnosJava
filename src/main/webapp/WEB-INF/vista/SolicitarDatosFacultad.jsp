<%-- Document : Agregar Facultad --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva Facultad</title>
        <!-- Enlace a Bootstrap 5 desde CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container mt-5">
        <h1 class="text-center mb-4">Agregar Nueva Facultad</h1>
        
        <!-- Formulario con clases de Bootstrap -->
        <form action="AgregarFacultad" method="post" class="shadow p-4 rounded border">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre de la Facultad</label>
                <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Ingrese el nombre" required>
            </div>
            <button type="submit" id="crear" class="btn btn-primary">Crear Facultad</button>
        </form>
        
        <!-- Enlaces de navegación con clases de Bootstrap -->
        <div class="mt-4">
            <a href="javascript:window.history.back();" class="btn btn-secondary">&laquo; Volver atrás</a>
            <a href="./index.jsp" class="btn btn-info">Volver al inicio</a>
        </div>

        <!-- Script de Bootstrap para funcionalidades JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
