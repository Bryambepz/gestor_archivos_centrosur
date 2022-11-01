package centrosur.ambiental.gestor_archivos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import centrosur.ambiental.gestor_archivos.Models.Informacion_Proceso;

@Repository
public interface Informacion_Proceso_Repository extends JpaRepository<Informacion_Proceso, Long>{
    
}
