package sevadu.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sevadu.modelo.maestros.MaestroAliado;
import sevadu.modelo.maestros.MaestroProducto;
import sevadu.modelo.maestros.MappingProducto;
import sevadu.modelo.pk.MappingProductoPK;

public interface IMappingProductoDAO extends JpaRepository<MappingProducto, MappingProductoPK> {

	List<MappingProducto> findByIdMaestroAliadoAndEstadoMapeo(MaestroAliado aliado, int a);

	List<MappingProducto> findByIdMaestroProductoNotInAndIdMaestroAliado(
			List<MaestroProducto> productos, MaestroAliado aliado);

	List<MappingProducto> findByIdMaestroAliadoIn(
			List<MaestroAliado> eliminarLista);

	List<MappingProducto> findByIdMaestroProductoIn(
			List<MaestroProducto> eliminarLista);

	List<MappingProducto> findByIdMaestroProductoCodigoProductoDusa(String clave);

//	List<MappingProducto> findByIdMaestroAliadoAndCodigoProductoCliente(
//			MaestroAliado aliado, String idProducto);

	List<MappingProducto> findByIdMaestroAliadoAndCodigoProductoClienteAndEstadoMapeo(
			MaestroAliado aliado, String idProducto, int i);

}
