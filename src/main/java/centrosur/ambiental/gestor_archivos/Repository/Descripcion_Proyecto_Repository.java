package centrosur.ambiental.gestor_archivos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import centrosur.ambiental.gestor_archivos.Models.Descripcion_Proyecto;

@Repository
public interface Descripcion_Proyecto_Repository extends JpaRepository<Descripcion_Proyecto, Long>{}
