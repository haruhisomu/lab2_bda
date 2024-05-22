package bdabackend.bda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.TareaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<TareaEntity, Long> {
        // Crear
        // Se crea una tarea con el id de la emergencia
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO tarea (nombre, descripcion, tipo, zona, id_emergencia) VALUES (:nombre, :descripcion, :tipo, :zona, :idEmergencia)", nativeQuery = true)
        public void insertarTarea(@Param("nombre") String nombre, @Param("descripcion") String descripcion,
                        @Param("tipo") String tipo, @Param("zona") String zona,
                        @Param("idEmergencia") Long idEmergencia);

        // Crear
        // Se crea una tarea sin el id de la emergencia
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO tarea (nombre, descripcion, tipo, zona) VALUES (:nombre, :descripcion, :tipo, :zona)", nativeQuery = true)
        public void insertarTareaSinEmergencia(@Param("nombre") String nombre, @Param("descripcion") String descripcion,
                        @Param("tipo") String tipo, @Param("zona") String zona);

        // Leer
        @Query("SELECT v FROM TareaEntity v WHERE v.id = ?1")
        public TareaEntity buscarTareaPorId(Long id);

        @Query("SELECT v FROM TareaEntity v")
        public List<TareaEntity> listaTarea();

        // Delete
        @Transactional
        @Modifying
        @Query("DELETE FROM TareaEntity v WHERE v.id = :id")
        public void eliminarTareaPorId(@Param("id") Long id);
}