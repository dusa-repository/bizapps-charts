package evaluacion.servicio.reporte;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.chart.model.CategoryModel;

import evaluacion.interfacedao.reporte.IReporteDAO;

@Service
public class SReporte {

	@Autowired
	private IReporteDAO servicioReporteDAO;

	
/* ----------------- PERIODO ---------------------- */
	
	@SuppressWarnings("unchecked")
	public CategoryModel getDataResumenMacroP(Map parametros) {

		CategoryModel datos = servicioReporteDAO
				.getDataResumenMacroPeriodo(parametros);
		return datos;
	}
	
	
	@SuppressWarnings("unchecked")
	public CategoryModel getDataCumplimientoObjetivoP(Map parametros) {

		CategoryModel datos = servicioReporteDAO
				.getDataCumplimientoObjetivoPeriodo(parametros);
		return datos;
	}

	@SuppressWarnings("unchecked")
	public CategoryModel getDataResumenGeneralBrechaP(Map parametros) {

		CategoryModel datos = servicioReporteDAO
				.getDataResumenGeneralBrechaPeriodo(parametros);
		
		return datos;

	}
	
	/* ----------------- PERIODO ---------------------- */
	
	/* ----------------- GERENCIA ---------------------- */
	
	@SuppressWarnings("unchecked")
	public CategoryModel getDataResumenMacroG(Map parametros) {

		CategoryModel datos = servicioReporteDAO
				.getDataResumenMacroGerencia(parametros);
		return datos;
	}
	
	@SuppressWarnings("unchecked")
	public CategoryModel getDataCumplimientoObjetivoG(Map parametros) {

		CategoryModel datos = servicioReporteDAO
				.getDataCumplimientoObjetivoGerencia(parametros);
		return datos;
	}

	@SuppressWarnings("unchecked")
	public CategoryModel getDataResumenGeneralBrechaG(Map parametros) {

		CategoryModel datos = servicioReporteDAO
				.getDataResumenGeneralBrechaGerencia(parametros);
		
		return datos;

	}
	
	/* ----------------- GERENCIA ---------------------- */
	
	@SuppressWarnings("unchecked")
	public CategoryModel getDataEvaluadosBrecha(Map parametros) {

		CategoryModel datos = servicioReporteDAO
				.getDataEvaluadosBrecha(parametros);
		
		return datos;

	}

	
}
