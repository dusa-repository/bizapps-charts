package sevadu.interfacedao.maestros;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sevadu.modelo.maestros.MaestroAliado;
import sevadu.modelo.maestros.MaestroProducto;
import sevadu.modelo.maestros.PlanVenta;
import sevadu.modelo.pk.PlanVentaPK;

public interface IPlanVentaDAO extends JpaRepository<PlanVenta, PlanVentaPK> {

	List<PlanVenta> findByIdMaestroProductoMaestroMarcaFiltroTermometroAndIdMaestroAliadoAndIdAnnoAndIdMes(
			boolean b, MaestroAliado aliado, int anno, int tiempo, Sort o);

	List<PlanVenta> findByIdMaestroProductoMaestroMarcaFiltroTermometroAndIdMaestroAliadoAndIdAnnoAndIdMesBetween(
			boolean b, MaestroAliado aliado, int anno, int periodo, int tiempo,
			Sort o);

	@Query("select p from PlanVenta p where p.id.maestroProducto.maestroMarca.filtroTermometro = ?1 and p.id.maestroAliado = ?2 "
			+ "and ((id.anno = ?3 and id.mes between ?4 and ?5) or (id.anno = ?6 and id.mes between ?7 and ?8))")
	List<PlanVenta> findByIdMaestroProductoMaestroMarcaFiltroTermometroAndIdMaestroAliadoAndIdAnnoAndIdMesBetweenAndIdAnnoAndIdMesBetween(
			boolean b, MaestroAliado aliado, int anno2, int tiempo,
			int limiteInf, int anno, int limiteSup, int periodo, Sort o);

	List<PlanVenta> findByIdMaestroAliadoIn(List<MaestroAliado> eliminarLista);

	List<PlanVenta> findByIdMaestroAliado(MaestroAliado aliado);

	List<PlanVenta> findByIdMaestroProductoIn(
			List<MaestroProducto> eliminarLista);

	List<PlanVenta> findByIdMaestroProductoCodigoProductoDusa(String clave);

	PlanVenta findByIdMaestroAliadoAndIdMaestroProductoAndIdAnnoAndIdMes(
			MaestroAliado maestroAliado, MaestroProducto maestroProducto,
			int anno, int mes);

	@Query("select coalesce(sum(v.cajasPlanificadas), '0') from PlanVenta v where v.id.maestroAliado = ?1 " +
			"and v.id.maestroProducto.maestroMarca.marcaDusa = ?2 and v.id.anno = ?4 and v.id.mes=?3")
	Double sumByIdMaestroAliadoAnIdMaestroProductoAndIdMesAndIdAnno(
			MaestroAliado maestroAliado, String maestroProducto,
			int mes, int anno);
	
	@Query("select coalesce(sum(v.cajasPlanificadas), '0') from PlanVenta v where v.id.maestroAliado = ?1 " +
			" and v.id.anno = ?3 and v.id.mes=?2")
	Double sumByIdMaestroAliadoAnIdMaestroProductoAndIdMesAndIdAnno2(
			MaestroAliado maestroAliado, int mes, int anno);

	List<PlanVenta> findByIdMaestroAliadoCodigoAliadoAndFechaAuditoriaBetween(
			String idAliado, Date desde, Date hasta);
	
	@Query("select coalesce(sum(v.cajasPlanificadas), '0') from PlanVenta v where v.id.maestroAliado.codigoAliado like ?1 " +
			"and v.id.maestroProducto.codigoProductoDusa = ?2 and v.id.anno = ?4 and v.id.mes=?3")
	Integer sumByIdMaestroAliadoAnIdMaestroProductoProductoAndIdMesAndIdAnno(
			String maestroAliado, String maestroProducto,
			int mes, int anno);

	List<PlanVenta> findByIdMaestroProductoMaestroMarcaFiltroTermometroAndIdAnnoAndIdMes(
			boolean b, int anno, int tiempo, Sort o);

	List<PlanVenta> findByIdMaestroProductoMaestroMarcaFiltroTermometroAndIdAnnoAndIdMesBetween(
			boolean b, int anno, int tiempo, int periodo, Sort o);

	@Query("select p from PlanVenta p where p.id.maestroProducto.maestroMarca.filtroTermometro = ?1 "
			+ "and ((id.anno = ?2 and id.mes between ?3 and ?4) or (id.anno = ?5 and id.mes between ?6 and ?7))")
	List<PlanVenta> findByIdMaestroProductoMaestroMarcaFiltroTermometroAndIdAnnoAndIdMesBetweenAndIdAnnoAndIdMesBetween(
			boolean b, int anno2, int tiempo, int limiteInf, int anno,
			int limiteSup, int periodo, Sort o);

	@Query("select coalesce(sum(v.cajasPlanificadas), '0') from PlanVenta v where v.id.maestroAliado.codigoAliado like ?1 " +
			"and v.id.maestroProducto.maestroMarca.marcaDusa in ?2 and v.id.anno = ?4 and v.id.mes=?3")
	Integer sumByIdMaestroAliadoAnIdMaestroProductoProductoAndIdMesAndIdAnno(
			String aliado2, List<String> ids, int mesPlanDesde,
			int annoPlanDesde);

}
