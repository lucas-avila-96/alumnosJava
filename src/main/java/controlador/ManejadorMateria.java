package controlador;

import entidades.Materia;
import entidades.MateriaHasAlumno;
import jakarta.inject.Inject;
import jakarta.persistence.RollbackException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.SystemException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sesiones.MateriaFacade;
import sesiones.MateriaHasAlumnoFacade;

@WebServlet(name = "ManejadorMateria",
        loadOnStartup = 1,
        urlPatterns = {"/SolicitarDatosMateria",
            "/AgregarMateria",
            "/ListarMateria",
            "/ListarAlumnosConMaterias"
        }
)

public class ManejadorMateria extends HttpServlet {

    @Inject
    private MateriaFacade materiaFacade;

    @Inject
    private MateriaHasAlumnoFacade materiaAlumnoF;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NotSupportedException, SystemException, jakarta.transaction.RollbackException {
        try {
            String pathUsuario = request.getServletPath();
            System.out.println("path = " + pathUsuario);
            String url = null;

            switch (pathUsuario) {
                case "/SolicitarDatosMateria":
                    url = "/WEB-INF/vista/SolicitarDatosMateria.jsp";
                    break;
                case "/AgregarMateria":
                    String nombreM = (String) request.getParameter("nombre");
                    Materia m = new Materia();
                    m.setNombre(nombreM);
                    materiaFacade.create(m);
                    url = "index.jsp";
                    break;

                case "/ListarMateria":
                    List<Materia> materias = materiaFacade.findAll();
                    request.setAttribute("materias", materias);
                    url = "/WEB-INF/vista/ListarMateria.jsp";
                    break;

                case "/ListarAlumnosConMaterias":

                    List<MateriaHasAlumno> alumnosConMaterias = materiaAlumnoF.findAlumnosConMaterias();
                    request.setAttribute("alumnosMaterias", alumnosConMaterias);
                    url = "/WEB-INF/vista/ListarAlumnosConMaterias.jsp";
                    break;
            }
            request.getRequestDispatcher(url).forward(request, response);

        } catch (RollbackException | SecurityException | IllegalStateException  ex) {
            Logger.getLogger(ManejadorMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NotSupportedException | SystemException | jakarta.transaction.RollbackException ex) {
            Logger.getLogger(ManejadorMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NotSupportedException | SystemException | jakarta.transaction.RollbackException ex) {
            Logger.getLogger(ManejadorMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Manejador de Materia";
    }
}
