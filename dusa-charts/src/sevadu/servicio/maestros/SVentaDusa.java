package sevadu.servicio.maestros;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sevadu.interfacedao.maestros.IVentaDusaDAO;

@Service("SVentaDusa")
public class SVentaDusa {

	@Autowired
	private IVentaDusaDAO vendaDusaDAO;

	public Double sumarPorAliadoEntreFechasYMarcasOrdenadoPorProducto(
			String aliado2, Date fechaInicio, Date fechaFin, List<String> ids) {
		return vendaDusaDAO
				.sumByMaestroAliadoCodigoAliadoAndMaestroProductoMaestroMarcaMarcaDusaInAndFechaFacturaBetween(
						aliado2, ids, fechaInicio, fechaFin);
	}
}
