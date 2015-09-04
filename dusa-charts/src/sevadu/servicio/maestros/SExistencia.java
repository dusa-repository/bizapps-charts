package sevadu.servicio.maestros;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sevadu.interfacedao.maestros.IExistenciaDAO;
import sevadu.modelo.maestros.Existencia;
import sevadu.modelo.maestros.MaestroAliado;
import sevadu.modelo.maestros.MaestroProducto;

@Service("SExistencia")
public class SExistencia {

	@Autowired
	private IExistenciaDAO existenciaDAO;

	public void guardarVarios(List<Existencia> existencias) {
		existenciaDAO.save(existencias);
	}

	public List<Existencia> buscarPorAliados(List<MaestroAliado> eliminarLista) {
		return existenciaDAO.findByIdMaestroAliadoIn(eliminarLista);
	}

	public List<Existencia> buscarPorAliado(MaestroAliado aliado) {
		return existenciaDAO.findByIdMaestroAliado(aliado);
	}

	public List<Existencia> buscarPorProductos(
			List<MaestroProducto> eliminarLista) {
		return existenciaDAO.findByIdMaestroProdIn(eliminarLista);
	}

	public List<Existencia> buscarPorProducto(String clave) {
		return existenciaDAO.findByIdMaestroProdCodigoProductoDusa(clave);
	}

	public List<Existencia> buscarPorAliadoEntreFechasRegistro(String idAliado,
			Date desde, Date hasta) {
		return existenciaDAO
				.findByIdMaestroAliadoCodigoAliadoAndFechaAuditoriaBetween(
						idAliado, desde, hasta);
	}

	public void eliminar(List<Existencia> existencias) {
		existenciaDAO.delete(existencias);
	}

	public Integer sumarPorAliadoEntreFechasYMarcasOrdenadoPorProducto(
			String aliado2, Date fechaDesde2, Date fechaHasta2, List<String> ids) {
		return existenciaDAO
				.sumByMaestroAliadoCodigoAliadoAndMaestroProductoMaestroMarcaMarcaDusaInAndFechaFacturaBetween(
						aliado2, ids, fechaDesde2, fechaHasta2);
	}
}
