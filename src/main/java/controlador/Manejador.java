package controlador;

import entidades.Alumno;
import entidades.Carrera;
import sesiones.AlumnoFacade;
import sesiones.CarreraFacade;
import sesiones.FacultadFacade;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Manejador", 
            loadOnStartup = 1, 
            urlPatterns = {"/SolicitarDatosFacultad", "/SolicitarDatosAlumno", "/AgregarAlumno", "/ListarAlumno", "/ListarCarreras", "/Facultad"}
)
public class Manejador extends HttpServlet {

    @Inject
    private AlumnoFacade alumnoFacade;

    @Inject
    private CarreraFacade carreraFacade;

    @Inject
    private FacultadFacade facultadFacade;

    @Resource
    private UserTransaction utx;    

    @Override
    public void init() throws ServletException {
        // Almacenar la lista de facultades en el contexto del servlet
        getServletContext().setAttribute("facultades", facultadFacade.findAll());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String pathUsuario = request.getServletPath();
            System.out.println("path = " + pathUsuario);
            String url = null;

            switch (pathUsuario) {
                case "/SolicitarDatosAlumno":
                    List<Carrera> carreras = carreraFacade.findAll();
                    request.setAttribute("listaCarreras", carreras);
                    url = "/WEB-INF/vista/SolicitarDatosAlumno.jsp";
                    break;

                case "/AgregarAlumno":
                    String registro = request.getParameter("registro");
                    String nombre = request.getParameter("nombre");
                    String carreraId = request.getParameter("carrera");

                    Alumno alumno = new Alumno();
                    alumno.setRegistro(Integer.valueOf(registro));
                    alumno.setNombre(nombre);

                    Carrera carrera = carreraFacade.find(Integer.valueOf(carreraId));
                    alumno.setCarreraIdcarrera(carrera);

                    utx.begin();
                    try {
                        alumnoFacade.create(alumno);
                        utx.commit();
                    } catch (HeuristicMixedException | HeuristicRollbackException | RollbackException | SystemException | IllegalStateException | SecurityException e) {
                        utx.rollback();
                        throw new ServletException("Error al agregar el alumno", e);
                    }

                    url = "index.jsp";
                    break;

                case "/ListarAlumno":
                    List<Alumno> alumnos = alumnoFacade.findAll();
                    request.setAttribute("lista", alumnos);
                    url = "/WEB-INF/vista/ListarAlumno.jsp";
                    break;
            }

            request.getRequestDispatcher(url).forward(request, response);

        } catch (ServletException | NotSupportedException | SystemException | IOException | IllegalStateException | NumberFormatException | SecurityException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (utx.getStatus() == jakarta.transaction.Status.STATUS_ACTIVE) {
                    utx.rollback();
                }
            } catch (SystemException | IllegalStateException | SecurityException e) {
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
        return "Manejador Principal de Alumnos y Facultades";
    }
}
