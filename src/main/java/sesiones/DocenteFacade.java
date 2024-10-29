/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Docente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Lucas
 */
@Stateless
public class DocenteFacade extends AbstractFacade<Docente> {

    @PersistenceContext(unitName = "com.mycompany_alumno_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }
 public List<Object[]> obtenerAlumnosPorDocente(Long docenteId) {
    String jpql = "SELECT a.nombre, m.nombre, d.nombre " +
                  "FROM AlumnoMateria am " +
                  "JOIN am.alumno a " +
                  "JOIN am.materia m " +
                  "JOIN m.docentes d " +
                  "WHERE d.id = :docenteId";
    return em.createQuery(jpql, Object[].class)
                        .setParameter("docenteId", docenteId)
                        .getResultList();
}


}
