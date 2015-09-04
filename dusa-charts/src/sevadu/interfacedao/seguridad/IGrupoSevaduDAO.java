package sevadu.interfacedao.seguridad;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sevadu.modelo.seguridad.ArbolSevadu;
import sevadu.modelo.seguridad.GrupoSevadu;
import sevadu.modelo.seguridad.UsuarioSeguridadSevadu;

public interface IGrupoSevaduDAO extends JpaRepository<GrupoSevadu, Long> {
	
	public List<GrupoSevadu> findByUsuarios(UsuarioSeguridadSevadu usuario);

	public List<GrupoSevadu> findByEstadoTrue();

	public List<GrupoSevadu> findByIdGrupoNotInAndEstadoTrue(List<Long> ids);

	public List<GrupoSevadu> findByNombre(String nombreGrupo);

	public List<GrupoSevadu> findByNombreStartingWithAllIgnoreCase(String valor);

	@Query("Select g from GrupoSevadu g order by g.idGrupo asc")
	public List<GrupoSevadu> findAllOrderById();

	public List<GrupoSevadu> findByUsuariosOrderByNombreAsc(UsuarioSeguridadSevadu u);

	public List<GrupoSevadu> findByArbols(Set<ArbolSevadu> arbols);
}
