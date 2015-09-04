package sevadu.interfacedao.maestros;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sevadu.modelo.maestros.Existencia;
import sevadu.modelo.maestros.MaestroAliado;
import sevadu.modelo.maestros.MaestroProducto;
import sevadu.modelo.pk.ExistenciaPK;

public interface IExistenciaDAO extends JpaRepository<Existencia, ExistenciaPK> {

	List<Existencia> findByIdMaestroAliadoIn(List<MaestroAliado> eliminarLista);

	List<Existencia> findByIdMaestroAliado(MaestroAliado aliado);

	List<Existencia> findByIdMaestroProdIn(List<MaestroProducto> eliminarLista);

	List<Existencia> findByIdMaestroProdCodigoProductoDusa(String clave);

	List<Existencia> findByIdMaestroAliadoCodigoAliadoAndFechaAuditoriaBetween(
			String idAliado, Date desde, Date hasta);

	@Query("select coalesce(sum(cantidad), '0') from Existencia v where v.id.maestroAliado.codigoAliado like ?1 and v.id.maestroProd.maestroMarca.marcaDusa in ?2 "
			+ " and v.id.fechaExistencia between ?3 and ?4")
	Integer sumByMaestroAliadoCodigoAliadoAndMaestroProductoMaestroMarcaMarcaDusaInAndFechaFacturaBetween(
			String aliado2, List<String> ids, Date fechaDesde2, Date fechaHasta2);

}
