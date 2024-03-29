package centrosur.ambiental.gestor_archivos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import centrosur.ambiental.gestor_archivos.Models.Proyecto;

@Repository
public interface Proyecto_Repository extends JpaRepository<Proyecto, Long>{
    List<Proyecto> findByNombre(String nombre);
}
