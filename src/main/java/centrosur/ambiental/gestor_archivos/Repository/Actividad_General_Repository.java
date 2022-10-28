package centrosur.ambiental.gestor_archivos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import centrosur.ambiental.gestor_archivos.Models.Actividad_General;

@Repository
public interface Actividad_General_Repository extends JpaRepository<Actividad_General, Long>{}
