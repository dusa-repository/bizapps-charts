package sevadu.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sevadu.modelo.maestros.MaestroAliado;
import sevadu.modelo.maestros.MaestroMarca;
import sevadu.modelo.maestros.MaestroProducto;

public interface IMaestroProductoDAO extends
		JpaRepository<MaestroProducto, String> {

	@Query("select p from MaestroProducto p order by p.codigoProductoDusa asc")
	List<MaestroProducto> findAllOrderByCodigoProductoDusaAsc();

	List<MaestroProducto> findByCodigoProductoDusaNotInOrderByCodigoProductoDusaAsc(
			List<String> ids);

	List<MaestroProducto> findByMaestroAliadoIn(
			List<MaestroAliado> eliminarLista);

	List<MaestroProducto> findByMaestroAliado(MaestroAliado aliado);

	List<MaestroProducto> findByMaestroMarcaIn(List<MaestroMarca> eliminarLista);

	List<MaestroProducto> findByMaestroMarcaMarcaDusa(String value);

	List<MaestroProducto> findByMaestroMarcaMarcaDusaIn(List<String> ids);

	List<MaestroProducto> findByMaestroAliadoIsNull();

}
