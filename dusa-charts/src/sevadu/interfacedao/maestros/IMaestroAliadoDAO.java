package sevadu.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sevadu.modelo.maestros.MaestroAliado;

public interface IMaestroAliadoDAO extends JpaRepository<MaestroAliado, String> {

	@Query("select m from MaestroAliado m order by m.nombre asc")
	List<MaestroAliado> findAllOrderByNombreAsc();

	List<MaestroAliado> findByCodigoAliadoNotIn(List<String> listaAliados);

}
