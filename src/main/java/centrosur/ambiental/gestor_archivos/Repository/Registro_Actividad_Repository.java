package centrosur.ambiental.gestor_archivos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import centrosur.ambiental.gestor_archivos.Models.Registro_Actividad;

public interface Registro_Actividad_Repository extends JpaRepository<Registro_Actividad, Long>{}
