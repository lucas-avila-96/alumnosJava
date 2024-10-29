package controlador;

import entidades.Alumno;
import entidades.Docente;
import entidades.Materia;
import entidades.Usuario;
import sesiones.DocenteFacade;
import sesiones.MateriaFacade;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sesiones.UsuarioFacade;

@WebServlet(name = "ManejadorDocente",
        loadOnStartup = 1,
        urlPatterns = {"/SolicitarDatosDocente", "/AgregarDocente", "/ListarDocente", "/ListarAlumnosRendidos"}
)
public class ManejadorDocente extends HttpServlet {

    @Inject
    private DocenteFacade docenteFacade;

    @Inject
    private MateriaFacade materiaFacade;

    @Inject
    private FacultadFacade facultadFacade;

    @Inject
    private UsuarioFacade usuarioFacade;

    @Resource
    private UserTransaction utx;

    @Override
    public void init() throws ServletException {
        // Almacenar la lista de facultades y materias en el contexto del servlet
        getServletContext().setAttribute("facultades", facultadFacade.findAll());
        getServletContext().setAttribute("materias", materiaFacade.findAll());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String pathUsuario = request.getServletPath();
            System.out.println("path = " + pathUsuario);
            String url = null;

            switch (pathUsuario) {

                case "/SolicitarDatosDocente":
                    List<Materia> materias = materiaFacade.findAll();
                    request.setAttribute("listaMaterias", materias);
                    url = "/WEB-INF/vista/SolicitarDatosDocente.jsp";
                    break;

                case "/AgregarDocente":
                    String nombre = request.getParameter("nombre");
                    String[] materiasIds = request.getParameterValues("materias");

                    Docente docente = new Docente();
                    Usuario usuario = new Usuario();

                    usuario.setNombre("NombreUsuario");
                    usuario.setClave("ClaveUsuario");
                    usuarioFacade.create(usuario);

                    docente.setUsuarioIdusuario(usuario);

                    docente.setNombre(nombre);

                    List<Materia> materiasSeleccionadas = new ArrayList<>();
                    for (String materiaId : materiasIds) {
                        Materia materia = materiaFacade.find(Integer.valueOf(materiaId));
                        materiasSeleccionadas.add(materia);
                    }
                    docente.setMateriaCollection(materiasSeleccionadas); // Añadir las materias al docente

                    utx.begin();
                    try {
                        docenteFacade.create(docente);
                        utx.commit();
                    } catch (HeuristicMixedException | HeuristicRollbackException | RollbackException | SystemException | IllegalStateException | SecurityException e) {
                        utx.rollback();
                        throw new ServletException("Error al agregar el docente", e);
                    }

                    url = "index.jsp";
                    break;

                case "/ListarDocente":
                    List<Docente> docentes = docenteFacade.findAll();
                    request.setAttribute("lista", docentes);
                    url = "/WEB-INF/vista/ListarDocente.jsp";
                    break;

                case "/ListarAlumnosRendidos":
                    Long docenteId = Long.valueOf(request.getParameter("docenteId"));

                    // Llama al método que obtiene los nombres del alumno, materia y docente
                    List<Object[]> alumnosConMaterias = docenteFacade.obtenerAlumnosPorDocente(docenteId);

                    // Envía la lista de objetos a la JSP
                    request.setAttribute("alumnosConMaterias", alumnosConMaterias);

url = request.getContextPath() + "/WEB-INF/vista/ListarAlumnosPorDocente.jsp";
                    break;
            }

            request.getRequestDispatcher(url).forward(request, response);

        } catch (ServletException | NotSupportedException | SystemException | IOException | IllegalStateException | NumberFormatException | SecurityException ex) {
            Logger.getLogger(ManejadorDocente.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (utx.getStatus() == jakarta.transaction.Status.STATUS_ACTIVE) {
                    utx.rollback();
                }
            } catch (SystemException | IllegalStateException | SecurityException e) {
                Logger.getLogger(ManejadorDocente.class.getName()).log(Level.SEVERE, null, e);
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
        return "Manejador Principal de Docentes";
    }
}
