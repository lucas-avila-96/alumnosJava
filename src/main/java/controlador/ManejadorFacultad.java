package controlador;

import entidades.Facultad;
import jakarta.ejb.EJB;
import jakarta.persistence.RollbackException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sesiones.FacultadFacade;

@WebServlet(name = "ManejadorFacultad",
        loadOnStartup = 1,
        urlPatterns = {"/SolicitarDatosFacultad", "/AgregarFacultad", "/Facultad"})
public class ManejadorFacultad extends HttpServlet {

    @EJB
    private FacultadFacade facu;  // Inyección de EJB

    @Override
    public void init() throws ServletException {
        // Almacena la lista de facultades en el contexto del Servlet
        getServletContext().setAttribute("facultades", facu.findAll());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String pathUsuario = request.getServletPath();
            System.out.println("path = " + pathUsuario);
            String url = null;

            switch (pathUsuario) {
                case "/SolicitarDatosFacultad":
                    url = "/WEB-INF/vista/" + pathUsuario + ".jsp";
                    break;
                
                case "/AgregarFacultad":
                    String nombreF = (String) request.getParameter("nombre");
                    Facultad f = new Facultad();
                    f.setNombre(nombreF);
                    facu.create(f);
                    url = "index.jsp";
                    break;
                
                case "/Facultad":
                    Integer nroFacultad = Integer.valueOf(request.getParameter("codigo"));
                    entidades.Facultad miFacu = facu.find(nroFacultad);
                    getServletContext().setAttribute("facultad", miFacu);
                    url = "/WEB-INF/vista/" + "ListarCarrera.jsp";
                    break;

            }

            request.getRequestDispatcher(url).forward(request, response);

        } catch (RollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(ManejadorFacultad.class.getName()).log(Level.SEVERE, null, ex);
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
        return "Manejador de Facultad";
    }
}