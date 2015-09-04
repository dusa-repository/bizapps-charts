package evaluacion.interfacedao.reporte;

import java.util.Map;

import org.zkoss.chart.model.CategoryModel;

public interface IReporteDAO {

	public abstract CategoryModel getDataResumenMacroPeriodo(Map<String, String> parametros);
	public abstract CategoryModel getDataCumplimientoObjetivoPeriodo(Map<String, String> parametros);
	public abstract CategoryModel getDataResumenGeneralBrechaPeriodo(Map<String, String> parametros);
	
	public abstract CategoryModel getDataResumenMacroGerencia(Map<String, String> parametros);
	public abstract CategoryModel getDataCumplimientoObjetivoGerencia(Map<String, String> parametros);
	public abstract CategoryModel getDataResumenGeneralBrechaGerencia(Map<String, String> parametros);
	
	
	public abstract CategoryModel getDataEvaluadosBrecha(Map<String, String> parametros);
	
	

}
