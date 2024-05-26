package bdabackend.bda.Repository;

import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.VoluntarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface VoluntarioRepository extends JpaRepository<VoluntarioEntity, Long> {
        // Crear
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO voluntario (nombre, correo, numero_documento, zona_vivienda, contrasena, equipamiento) "
                        +
                        "VALUES (:nombre, :correo, :numeroDocumento, :zonaVivienda, :contrasena, :equipamiento)", nativeQuery = true)
        public void insertarVoluntario(@Param("nombre") String nombre, @Param("correo") String correo,
                        @Param("numeroDocumento") String numeroDocumento, @Param("zonaVivienda") Point zonaVivienda,
                        @Param("contrasena") String contrasena, @Param("equipamiento") String equipamiento);

        // Leer
        @Query("SELECT v FROM VoluntarioEntity v WHERE v.id = ?1")
        public VoluntarioEntity buscarVoluntarioPorId(Long id);

        @Query("SELECT palabra FROM VoluntarioEntity palabra WHERE"
                + " CONCAT(palabra.nombre, palabra.correo, " +
                "palabra.numeroDocumento, palabra.equipamiento)"
                + " LIKE %?1%")
        public List<VoluntarioEntity> findAll(@Param("palabra") String palabraClave);

        @Query("SELECT v FROM VoluntarioEntity v WHERE v.correo = ?1")
        public VoluntarioEntity buscarPorCorreo(String correo);

        @Query("SELECT v FROM VoluntarioEntity v")
        public List<?> listaVoluntario();

        // Delete
        @Transactional
        @Modifying
        @Query("DELETE FROM VoluntarioEntity v WHERE v.id = :id")
        public void eliminarVoluntarioPorId(@Param("id") Long id);

        @Query("SELECT v.nombre FROM VoluntarioEntity v WHERE v.id = :id")
        public String nombre (@Param("id") Long id);

        @Query("SELECT v.numeroDocumento FROM VoluntarioEntity v WHERE v.id = :id")
        public String numeroDocumento (@Param("id") Long id);

        @Query("SELECT v.equipamiento FROM VoluntarioEntity v WHERE v.id = :id")
        public String equipamiento (@Param("id") Long id);

        @Query("SELECT v FROM VoluntarioEntity v WHERE v.id = ?1")
        public List<?> listaVoluntarioId(@Param("id") Long id);
}
