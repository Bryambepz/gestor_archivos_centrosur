package centrosur.ambiental.gestor_archivos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import centrosur.ambiental.gestor_archivos.Models.Registro_Actividad;

@Repository
public interface Registro_Actividad_Repository extends JpaRepository<Registro_Actividad, Long>{}
