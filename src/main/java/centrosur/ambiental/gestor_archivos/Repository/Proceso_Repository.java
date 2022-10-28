package centrosur.ambiental.gestor_archivos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import centrosur.ambiental.gestor_archivos.Models.Proceso;

@Repository
public interface Proceso_Repository extends JpaRepository<Proceso, Long >{}
