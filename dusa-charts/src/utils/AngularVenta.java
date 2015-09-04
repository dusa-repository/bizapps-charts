package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.chart.Charts;
import org.zkoss.chart.LinearGradient;
import org.zkoss.chart.PaneBackground;
import org.zkoss.chart.YAxis;
import org.zkoss.chart.model.DefaultDialModel;
import org.zkoss.chart.model.DialModel;
import org.zkoss.chart.model.DialModelScale;

import sevadu.modelo.maestros.Configuracion;
import sevadu.modelo.maestros.MaestroAliado;
import sevadu.modelo.maestros.Venta;
import sevadu.servicio.maestros.SConfiguracion;
import sevadu.servicio.maestros.SPlanVenta;
import sevadu.servicio.maestros.SVenta;

public class AngularVenta extends Charts {

	private static final long serialVersionUID = -392192041291540186L;
	DateFormat formatoCorrecto = new SimpleDateFormat("dd-MM-yyyy");
	DateFormat formatoAnno = new SimpleDateFormat("yyyy");
	DateFormat formatoMes = new SimpleDateFormat("MM");

	public AngularVenta(MaestroAliado aliado, SVenta servicioVenta,
			SPlanVenta servicioPlan, Date fechaDesde2, Date fechaHasta2,
			SConfiguracion servicioConfiguracion, int habilesHoy,
			int habilesTotal) {
		super();
		this.setType("gauge");
		List<Venta> ventas = servicioVenta
				.buscarPorAliadoEntreFechasYMarcasOrdenadoPorFecha2(
						aliado.getCodigoAliado(), fechaDesde2, fechaHasta2);
		// if (!ventas.isEmpty()) {
		double vendido = 0;

		int annoPlanDesde = Integer.parseInt(formatoAnno.format(fechaDesde2));
		int mesPlanDesde = Integer.parseInt(formatoMes.format(fechaDesde2));
		int annoPlanHasta = Integer.parseInt(formatoAnno.format(fechaHasta2));
		int mesPlanHasta = Integer.parseInt(formatoMes.format(fechaHasta2));
		Double suma = (double) 0;
		do {
			if (mesPlanDesde == 13) {
				mesPlanDesde = 1;
				annoPlanDesde = annoPlanDesde + 1;
			}
			Double plan2 = (double) 0;
			if (!ventas.isEmpty()) {
				plan2 = servicioPlan.sumarPlanAliado2(ventas.get(0)
						.getMaestroAliado(), annoPlanDesde, mesPlanDesde);
			}
			suma = suma + plan2;
			mesPlanDesde = mesPlanDesde + 1;
		} while (annoPlanDesde != annoPlanHasta
				|| mesPlanDesde != mesPlanHasta + 1);

		for (int i = 0; i < ventas.size(); i++) {
			vendido = vendido + ventas.get(i).getCantidad();
		}
		vendido = Math.rint(vendido * 100) / 100;
		DialModel dialmodel = new DefaultDialModel();
		dialmodel.setFrameBgColor(null);
		dialmodel.setFrameBgColor1(null);
		dialmodel.setFrameBgColor2(null);
		dialmodel.setFrameFgColor(null);

		int minimo = 10;
		Configuracion actual = servicioConfiguracion.buscar(1);
		Double valorPorcentual = (double) minimo;
		if (actual != null) {
			if (actual.getPorcentaje() != null) {
				valorPorcentual = actual.getPorcentaje().doubleValue();
				minimo = valorPorcentual.intValue();
			}
		}
		// buscar dias habiles para regla de tres
		double limiteSuperior = habilesHoy * suma / habilesTotal;
		double cantidad = suma - vendido;
		Double tope = (double) 0;
		if (cantidad < 0)
			tope = vendido;
		else
			tope = suma;

		Double primero = tope * 5 / 100;
		Double segundo = tope * 0.01 / 100;
		DialModelScale scale = dialmodel.newScale(0, tope.intValue(), -150,
				-300, primero.intValue(), segundo.intValue());
		scale.setText("Cajas");
		scale.setTickColor("#666666");
		scale.setValue(vendido);
		scale.newRange(0, limiteSuperior - (limiteSuperior * minimo / 100),
				"#DF5353", 0.9, 1); 
		scale.newRange(limiteSuperior - (limiteSuperior * minimo / 100),
				limiteSuperior, "#DDDF0D", 0.9, 1);
		scale.newRange(limiteSuperior, tope.intValue(), "#55BF3B", 0.9, 1);
		this.setModel(dialmodel);
		List<PaneBackground> backgrounds = new LinkedList<PaneBackground>();
		PaneBackground background1 = new PaneBackground();
		LinearGradient linearGradient1 = new LinearGradient(0, 0, 0, 1);
		linearGradient1.setStops("#FFF", "#333");
		background1.setBackgroundColor(linearGradient1);
		background1.setBorderWidth(0);
		background1.setOuterRadius("109%");
		backgrounds.add(background1);
		PaneBackground background2 = new PaneBackground();
		LinearGradient linearGradient2 = new LinearGradient(0, 0, 0, 1);
		linearGradient2.setStops("#333", "#FFF");
		background2.setBackgroundColor(linearGradient2);
		background2.setBorderWidth(1);
		background2.setOuterRadius("107%");
		backgrounds.add(background2);
		backgrounds.add(new PaneBackground());
		PaneBackground background3 = new PaneBackground();
		background3.setBackgroundColor("#DDD");
		background3.setBorderWidth(0);
		background3.setOuterRadius("105%");
		background3.setInnerRadius("103%");
		backgrounds.add(background3);
		this.getPane().setBackground(backgrounds);
		YAxis yAxis = this.getYAxis();
		yAxis.setMinorTickWidth(1);
		yAxis.setMinorTickPosition("inside");
		yAxis.setMinorTickColor("#666");
		yAxis.setTickPixelInterval(30);
		yAxis.setTickWidth(2);
		yAxis.setTickPosition("inside");
		yAxis.setTickLength(10);
		yAxis.getLabels().setStep(2);
		yAxis.getLabels().setRotation("auto");
		Double porcentaje = (double) 0;
		if(tope > 0)
			porcentaje = vendido*100/tope;
		
		this.getPlotOptions().getGauge().getTooltip().setValueSuffix(" cajas <b>(" + porcentaje + "%)</b>");
		this.getSeries().setName("Cajas Vendidas");
		this.getYAxis().getTitle().setText("Numero de Cajas");
		this.setTitle("Vendido VS Planificado(Obj):" + " desde "
				+ formatoCorrecto.format(fechaDesde2) + " hasta  "
				+ formatoCorrecto.format(fechaHasta2));
		this.setSubtitle("Aliado: " + aliado.getNombre() + " ("
				+ aliado.getCodigoAliado() + ") <b>("+ porcentaje+"%)</b> de Ventas");
		// }

		if (ventas.isEmpty())
			this.setTitle("Vendido VS Planificado(Obj):" + " desde "
					+ formatoCorrecto.format(fechaDesde2) + " hasta  "
					+ formatoCorrecto.format(fechaHasta2) + "\n"
					+ "NO EXISTEN DATOS EN ESTE INTERVALO DE TIEMPO");
	}

}
