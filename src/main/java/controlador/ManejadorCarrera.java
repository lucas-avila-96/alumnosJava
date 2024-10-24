package controlador;

import entidades.Carrera;
import entidades.Facultad; // Asegúrate de tener esta entidad
import sesiones.CarreraFacade;
import sesiones.FacultadFacade; // Asegúrate de tener esta fachada
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ManejadorCarrera",
        loadOnStartup = 1,
            urlPatterns = {"/SolicitarDatosCarrera", "/AgregarCarrera", "/ListarCarrera"}
)
public class ManejadorCarrera extends HttpServlet {

    @Inject
    private CarreraFacade carreraFacade;

    @Inject
    private FacultadFacade facultadFacade; // Inyección de FacultadFacade

    @Resource
    private UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = null;

        try {
            String pathUsuario = request.getServletPath();

            switch (pathUsuario) {

                case "/ListarCarrera":
                    List<Carrera> carreras = carreraFacade.findAll(); // Obtener la lista de carreras desde la base de datos
                    request.setAttribute("listaCarrera", carreras); // Enviar la lista al JSP
                    url = "/WEB-INF/vista/ListarCarrera.jsp"; // Cargar la vista para listar carreras
                    break;

                case "/SolicitarDatosCarrera":
                    // Obtener la lista de facultades desde la base de datos
                    List<Facultad> facultades = facultadFacade.findAll();
                    request.setAttribute("listaFacultades", facultades); // Enviar la lista al JSP
                    url = "/WEB-INF/vista/SolicitarDatosCarrera.jsp"; // Cargar la vista para agregar carrera
                    break;

                case "/AgregarCarrera":
                    String nombreCarrera = request.getParameter("nombre");
                    String facultadId = request.getParameter("facultad");

                    Carrera nuevaCarrera = new Carrera();
                    nuevaCarrera.setNombre(nombreCarrera);

                    Facultad facultad = facultadFacade.find(Integer.valueOf(facultadId)); // Buscar facultad por ID
                    nuevaCarrera.setFacultadIdfacultad(facultad); // Asociar la facultad a la nueva carrera

                    utx.begin();
                    try {
                        carreraFacade.create(nuevaCarrera); // Persistir la nueva carrera
                        utx.commit();
                    } catch (Exception e) {
                        utx.rollback();
                        throw new ServletException("Error al agregar la carrera", e);
                    }

                    url = "index.jsp"; // O redirige a donde desees
                    break;
            }

            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (utx.getStatus() == jakarta.transaction.Status.STATUS_ACTIVE) {
                    utx.rollback();
                }
            } catch (Exception e) {
                Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, e);
            }
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error en el servidor.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Manejador de Alumnos y Carreras con Transacciones en Jakarta EE";
    }
}
