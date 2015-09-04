package sevadu.servicio.maestros;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sevadu.interfacedao.maestros.IPlanVentaDAO;
import sevadu.modelo.maestros.MaestroAliado;
import sevadu.modelo.maestros.MaestroProducto;
import sevadu.modelo.maestros.PlanVenta;
import sevadu.modelo.pk.PlanVentaPK;

@Service("SPlanVenta")
public class SPlanVenta {

	@Autowired
	private IPlanVentaDAO planVentaDAO;

	public void guardarVarios(List<PlanVenta> planesVentas) {
		planVentaDAO.save(planesVentas);
	}

	public PlanVenta buscar(PlanVentaPK pk) {
		return planVentaDAO.findOne(pk);
	}

	public List<PlanVenta> buscarPorAliados(List<MaestroAliado> eliminarLista) {
		return planVentaDAO.findByIdMaestroAliadoIn(eliminarLista);
	}

	public List<PlanVenta> buscarPorAliado(MaestroAliado aliado) {
		return planVentaDAO.findByIdMaestroAliado(aliado);
	}

	public List<PlanVenta> buscarPorProductos(
			List<MaestroProducto> eliminarLista) {
		return planVentaDAO.findByIdMaestroProductoIn(eliminarLista);
	}

	public List<PlanVenta> buscarPorProducto(String clave) {
		return planVentaDAO.findByIdMaestroProductoCodigoProductoDusa(clave);
	}

	public Double sumarPlanAliado(MaestroAliado maestroAliado,
			MaestroProducto maestroProducto, int anno, int mes) {
		return planVentaDAO
				.sumByIdMaestroAliadoAnIdMaestroProductoAndIdMesAndIdAnno(
						maestroAliado, maestroProducto.getMaestroMarca()
								.getMarcaDusa(), mes, anno);
	}

	public Double sumarPlanAliado2(MaestroAliado maestroAliado, String marca,
			int anno, int mes) {
		return planVentaDAO
				.sumByIdMaestroAliadoAnIdMaestroProductoAndIdMesAndIdAnno(
						maestroAliado, marca, mes, anno);
	}

	public Double sumarPlanAliado2(MaestroAliado maestroAliado, int anno,
			int mes) {
		return planVentaDAO
				.sumByIdMaestroAliadoAnIdMaestroProductoAndIdMesAndIdAnno2(
						maestroAliado, mes, anno);
	}

	public List<PlanVenta> buscarPorAliadoEntreFechasRegistro(String idAliado,
			Date desde, Date hasta) {
		return planVentaDAO
				.findByIdMaestroAliadoCodigoAliadoAndFechaAuditoriaBetween(
						idAliado, desde, hasta);
	}

	public void eliminar(List<PlanVenta> planes) {
		planVentaDAO.delete(planes);
	}

	public int sumarPorProductoaliadoYFechas(String aliado,
			String codigoProductoDusa, int annoPlanDesde, int mesPlanDesde) {
		return planVentaDAO
				.sumByIdMaestroAliadoAnIdMaestroProductoProductoAndIdMesAndIdAnno(
						aliado, codigoProductoDusa, mesPlanDesde, annoPlanDesde);
	}

	public Integer sumarPorProductosaliadoYFechas(String aliado2,
			List<String> ids, int annoPlanDesde, int mesPlanDesde) {
		return planVentaDAO
				.sumByIdMaestroAliadoAnIdMaestroProductoProductoAndIdMesAndIdAnno(
						aliado2, ids, mesPlanDesde, annoPlanDesde);
	}
}
