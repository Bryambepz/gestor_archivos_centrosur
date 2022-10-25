package centrosur.ambiental.gestor_archivos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import centrosur.ambiental.gestor_archivos.Models.Persona;

@Repository
public interface Persona_Repository extends JpaRepository<Persona,Long>{
    
}
