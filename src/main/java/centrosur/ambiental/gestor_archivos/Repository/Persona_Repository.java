package centrosur.ambiental.gestor_archivos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import centrosur.ambiental.gestor_archivos.Models.Persona;

@Repository
public interface Persona_Repository extends JpaRepository<Persona,Long>{
    List<Persona> findByCedula(String cedula);   
}
