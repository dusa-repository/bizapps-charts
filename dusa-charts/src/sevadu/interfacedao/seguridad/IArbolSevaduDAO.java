package sevadu.interfacedao.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sevadu.modelo.seguridad.ArbolSevadu;
import sevadu.modelo.seguridad.GrupoSevadu;

public interface IArbolSevaduDAO extends JpaRepository<ArbolSevadu, Long> {
	
	 public List<ArbolSevadu> findByNombre(String nombre);

	 @Query("select a from ArbolSevadu a order by a.idArbol asc")
	public List<ArbolSevadu> buscarTodos();
	
	 @Query("select a from ArbolSevadu a where a.idArbol = ?1 order by a.idArbol")
	public List<ArbolSevadu> buscar(ArrayList<Long> ids);

	public List<ArbolSevadu> findByGruposArbol(GrupoSevadu grupo);

	public ArbolSevadu findByIdArbol(Long id);

	public List<ArbolSevadu> findByIdArbolInOrderByOrdenAsc(ArrayList<Long> ids);

	public List<ArbolSevadu> findByGruposArbol(List<GrupoSevadu> grupos);

		
	
}
