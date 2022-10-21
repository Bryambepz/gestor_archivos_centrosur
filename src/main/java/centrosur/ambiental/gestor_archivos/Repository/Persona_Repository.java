package centrosur.ambiental.gestor_archivos.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import centrosur.ambiental.gestor_archivos.models.Persona;

@Repository
public interface Persona_Repository extends CrudRepository<Persona,Long>{}
