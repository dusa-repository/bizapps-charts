package portal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;

import sevadu.servicio.maestros.SCliente;
import sevadu.servicio.maestros.SConfiguracion;
import sevadu.servicio.maestros.SConfiguracionEnvioCorreo;
import sevadu.servicio.maestros.SExistencia;
import sevadu.servicio.maestros.SF0004;
import sevadu.servicio.maestros.SF0005;
import sevadu.servicio.maestros.SMaestroAliado;
import sevadu.servicio.maestros.SMaestroMarca;
import sevadu.servicio.maestros.SMaestroProducto;
import sevadu.servicio.maestros.SMappingProducto;
import sevadu.servicio.maestros.SMarcaActivadaVendedor;
import sevadu.servicio.maestros.SPlanVenta;
import sevadu.servicio.maestros.STipoCliente;
import sevadu.servicio.maestros.SVenta;
import sevadu.servicio.maestros.SVentaDusa;
import sevadu.servicio.seguridad.SArbolSevadu;
import sevadu.servicio.seguridad.SGrupoSevadu;
import sevadu.servicio.seguridad.SUsuario;
import sevadu.servicio.seguridad.SUsuarioAliado;
import sevadu.servicio.seguridad.SUsuarioSeguridadSevadu;
import utils.Mensaje;
import evaluacion.servicio.maestros.SCompetencia;
import evaluacion.servicio.maestros.SEmpresa;
import evaluacion.servicio.maestros.SGerencia;
import evaluacion.servicio.maestros.SPeriodo;
import evaluacion.servicio.maestros.SRevision;
import evaluacion.servicio.maestros.SUnidadOrganizativa;
import evaluacion.servicio.reporte.SReporte;
import evaluacion.servicio.seguridad.SArbol;
import evaluacion.servicio.seguridad.SGrupo;
import evaluacion.servicio.seguridad.SUsuarioSeguridad;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public abstract class CGenericoCharts extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Tabbox tabBox;
	public Include contenido;
	protected SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
	public Mensaje msj = new Mensaje();
	public List<Tab> tabs = new ArrayList<Tab>();
	public String titulo = "";
	public Tab tab;
	@WireVariable("SArbol")
	protected SArbol servicioArbol;
	@WireVariable("SGrupo")
	protected SGrupo servicioGrupo;
	@WireVariable("SUsuarioSeguridad")
	protected SUsuarioSeguridad servicioUsuarioSeguridad;
	@WireVariable("SCompetencia")
	protected SCompetencia servicioCompetencia;
	@WireVariable("SEmpresa")
	protected SEmpresa servicioEmpresa;
	@WireVariable("SGerencia")
	protected SGerencia servicioGerencia;
	@WireVariable("SUnidadOrganizativa")
	protected SUnidadOrganizativa servicioUnidadOrganizativa;
	@WireVariable("SRevision")
	protected SRevision servicioRevision;
	@WireVariable("SPeriodo")
	protected SPeriodo servicioPeriodo;
	@WireVariable("SReporte")
	protected SReporte servicioReporte;
	
	@WireVariable("SArbolSevadu")
	protected SArbolSevadu servicioArbolSevadu;
	@WireVariable("SGrupoSevadu")
	protected SGrupoSevadu servicioGrupoSevadu;
	@WireVariable("SUsuario")
	protected SUsuario servicioUsuario;
	@WireVariable("SUsuarioSeguridadSevadu")
	protected SUsuarioSeguridadSevadu servicioUsuarioSeguridadSevadu;
	@WireVariable("SUsuarioAliado")
	protected SUsuarioAliado servicioUsuarioAliado;
	@WireVariable("SF0004")
	protected SF0004 servicioF0004;
	@WireVariable("SF0005")
	protected SF0005 servicioF0005;
	@WireVariable("SConfiguracionEnvioCorreo")
	protected SConfiguracionEnvioCorreo servicioEnvio;
	@WireVariable("SExistencia")
	protected SExistencia servicioExistencia;
	@WireVariable("SMaestroAliado")
	protected SMaestroAliado servicioAliado;
	@WireVariable("SMaestroMarca")
	protected SMaestroMarca servicioMarca;
	@WireVariable("SMaestroProducto")
	protected SMaestroProducto servicioProducto;
	@WireVariable("SMappingProducto")
	protected SMappingProducto servicioMapping;
	@WireVariable("SMarcaActivadaVendedor")
	protected SMarcaActivadaVendedor servicioMarcaActivada;
	@WireVariable("SPlanVenta")
	protected SPlanVenta servicioPlan;
	@WireVariable("SVenta")
	protected SVenta servicioVenta;
	@WireVariable("SVentaDusa")
	protected SVentaDusa servicioVentaDusa;
	@WireVariable("STipoCliente")
	protected STipoCliente servicioTipoCliente;
	@WireVariable("SConfiguracion")
	protected SConfiguracion servicioConfiguracion;
	@WireVariable("SCliente")
	protected SCliente servicioCliente;
	public String informacion = "INFORMACION";
	public String alerta = "ALERTA";
	public DateFormat formatoAnno = new SimpleDateFormat("yyyy");
	public DateFormat formatoMes = new SimpleDateFormat("MM");
	public DateFormat formatoDia = new SimpleDateFormat("dd");

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		inicializar();
	}

	public abstract void inicializar() throws IOException;

	public void cerrarVentana(Div div, String id, List<Tab> tabs2) {
		div.setVisible(false);
		tabs = tabs2;
		System.out.println(tabs.size());
		for (int i = 0; i < tabs.size(); i++) {
			if (tabs.get(i).getLabel().equals(id)) {
				if (i == (tabs.size() - 1) && tabs.size() > 1) {
					tabs.get(i - 1).setSelected(true);
				}
				tabs.get(i).onClose();
				tabs.remove(i);
			}
		}
	}

	public String lastDay(Date fechaHoy) {
		DateFormat formatoNuevo = new SimpleDateFormat("MM");
		String month = formatoNuevo.format(fechaHoy);
		String last = "30-";
		switch (month) {
		case "01":
		case "03":
		case "05":
		case "07":
		case "08":
		case "10":
		case "12":
			last = "31-";
			break;
		case "02":
			last = "28-";
			break;
		default:
			break;
		}
		return last;
	}

}
