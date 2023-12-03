package edu.pucmm.eict.pwa.springbootjasperreport.repositorios;

import edu.pucmm.eict.pwa.springbootjasperreport.entidades.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepositorio extends JpaRepository<Estudiante, Long> {

}
