package sevadu.controlador.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import portal.CGenericoCharts;
import sevadu.modelo.maestros.Configuracion;
import sevadu.modelo.maestros.MaestroAliado;
import sevadu.modelo.maestros.MaestroMarca;
import sevadu.modelo.maestros.Venta;
import sevadu.modelo.seguridad.ArbolSevadu;
import sevadu.modelo.seguridad.GrupoSevadu;
import sevadu.modelo.seguridad.Usuario;
import sevadu.modelo.seguridad.UsuarioAliado;
import utils.Botonera;
import utils.Catalogo;
import utils.Mensaje;
import utils.Validador;

public class CReporte extends CGenericoCharts {

	private static final long serialVersionUID = 1470992598691077405L;
	@Wire
	private Textbox txtAliado;
	@Wire
	private Div botoneraReporte;
	@Wire
	private Div divVReporte;
	@Wire
	private Div divCatalogoAliado;
	@Wire
	private Combobox cmbReporte;
	@Wire
	private Combobox cmbZona;
	@Wire
	private Combobox cmbCliente;
	@Wire
	private Combobox cmbVendedor;
	@Wire
	private Combobox cmbTipo;
	@Wire
	private Comboitem itm16;
	@Wire
	private Comboitem itm17;
	@Wire
	private Comboitem itm20;
	@Wire
	private Comboitem itm21;
	@Wire
	private Comboitem itm23;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Row rowAliado;
	@Wire
	private Row rowZona;
	@Wire
	private Row rowVendedor;
	@Wire
	private Hbox box;
	Catalogo<MaestroAliado> catalogoAliado;
	protected Connection conexion;
	String idAliado = null;
	@Wire
	private Listbox ltbMarcas;
	@Wire
	private Listbox ltbMarcasAgregadas;
	@Wire
	private Label lblAliado;
	List<MaestroMarca> marcas = new ArrayList<MaestroMarca>();
	List<MaestroMarca> marcasAgregadas = new ArrayList<MaestroMarca>();
	String usuarioSes = null;

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (map != null) {
			if (map.get("tabsGenerales") != null) {
				tabs = (List<Tab>) map.get("tabsGenerales");
				titulo = (String) map.get("titulo");
				usuarioSes = (String) map.get("usuarioSes");
				map.clear();
				map = null;
			}
		}
		List<GrupoSevadu> grupos = servicioGrupoSevadu
				.buscarGruposDelUsuario(servicioUsuarioSeguridadSevadu
						.buscarPorLogin(usuarioSes));
		List<ArbolSevadu> arboles = servicioArbolSevadu
				.buscarporListGrupo(grupos);
		Usuario user = servicioUsuario.buscarPorLogin(usuarioSes);
		UsuarioAliado objeto = servicioUsuarioAliado.buscarActivo(user);
		if (objeto != null)
			idAliado = objeto.getId().getMaestroAliado().getCodigoAliado();
		for (int i = 0; i < arboles.size(); i++) {
			String nombre = arboles.get(i).getNombre().toString();
			if (nombre.equals("Ver Aliados Reporte/Grafica")) {
				rowAliado.setVisible(true);
				i = arboles.size();
			}
		}
		llenarLista();
		Botonera botonera = new Botonera() {

			@Override
			public void seleccionar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void salir() {
				cerrarVentana(divVReporte, titulo, tabs);
			}

			@Override
			public void reporte() {
				// TODO Auto-generated method stub

			}

			@Override
			public void limpiar() {
				cmbReporte.setValue("");
				cmbCliente.setValue("TODOS");
				cmbVendedor.setValue("TODOS");
				cmbZona.setValue("TODAS");
				dtbDesde.setValue(new Date());
				dtbHasta.setValue(new Date());
				txtAliado.setValue("");
				rowVendedor.setVisible(true);
				rowZona.setVisible(true);
				box.setVisible(false);
				lblAliado.setValue("");
				llenarLista();
			}

			@Override
			public void guardar() {
				if (validar()) {

					Date desde = dtbDesde.getValue();
					Date hasta = dtbHasta.getValue();
					DateFormat fechaF = new SimpleDateFormat("yyyy-MM-dd");
					String fecha1 = fechaF.format(desde);
					String fecha2 = fechaF.format(hasta);
					String aliado = idAliado;
					String vendedor = cmbVendedor.getValue();
					String zona = cmbZona.getValue();
					String cliente = cmbCliente.getValue();
					String nombre = cmbReporte.getValue()
							.replaceAll("\\s+", "") + "-" + aliado;
					if (vendedor.equals("TODOS"))
						vendedor = "";
					if (zona.equals("TODAS"))
						zona = "";
					if (cliente.equals("TODOS"))
						cliente = "";
					int tipo = 0;
					Window ventana = new Window();
					HashMap<String, Object> mapaGrafica = new HashMap<String, Object>();
					switch (cmbReporte.getValue()) {
					case "Grafico Venta de Marcas":
						tipo = 25;
						mapaGrafica = new HashMap<String, Object>();
						mapaGrafica.put("idAliado", aliado);
						mapaGrafica.put("desde", desde);
						mapaGrafica.put("hasta", hasta);
						mapaGrafica.put("tipo", "line");
						mapaGrafica.put("lista", marcasAgregadas);
						Sessions.getCurrent().setAttribute("grafica",
								mapaGrafica);
						ventana = (Window) Executions.createComponents(
								"/vistas/reportes/VGrafica.zul", null,
								mapaGrafica);
						ventana.doModal();
						break;
					case "Grafico Vendido VS Planificado Marcas":
						tipo = 25;
						mapaGrafica = new HashMap<String, Object>();
						mapaGrafica.put("idAliado", aliado);
						mapaGrafica.put("desde", desde);
						mapaGrafica.put("hasta", hasta);
						mapaGrafica.put("tipo", "bar");
						mapaGrafica.put("lista", marcasAgregadas);
						Sessions.getCurrent().setAttribute("grafica",
								mapaGrafica);
						ventana = (Window) Executions.createComponents(
								"/vistas/reportes/VGrafica.zul", null,
								mapaGrafica);
						ventana.doModal();
						break;
					case "Grafico Volumen de Ventas por Marca y Producto":
						tipo = 25;
						mapaGrafica = new HashMap<String, Object>();
						mapaGrafica.put("idAliado", aliado);
						mapaGrafica.put("desde", desde);
						mapaGrafica.put("hasta", hasta);
						mapaGrafica.put("tipo", "pie");
						mapaGrafica.put("dona", true);
						mapaGrafica.put("lista", marcasAgregadas);
						Sessions.getCurrent().setAttribute("grafica",
								mapaGrafica);
						ventana = (Window) Executions.createComponents(
								"/vistas/reportes/VGrafica.zul", null,
								mapaGrafica);
						ventana.doModal();
						break;
					case "Grafico Porcentaje de Participacion en Ventas(Marcas)":
						tipo = 25;
						mapaGrafica = new HashMap<String, Object>();
						mapaGrafica.put("idAliado", aliado);
						mapaGrafica.put("desde", desde);
						mapaGrafica.put("hasta", hasta);
						mapaGrafica.put("tipo", "pie");
						mapaGrafica.put("lista", marcasAgregadas);
						Sessions.getCurrent().setAttribute("grafica",
								mapaGrafica);
						ventana = (Window) Executions.createComponents(
								"/vistas/reportes/VGrafica.zul", null,
								mapaGrafica);
						ventana.doModal();
						break;
					case "Grafico de Comparacion entre Ventas/Compras/Inventario/Plan Ventas":
						tipo = 25;
						mapaGrafica = new HashMap<String, Object>();
						mapaGrafica.put("idAliado", aliado);
						mapaGrafica.put("desde", desde);
						mapaGrafica.put("hasta", hasta);
						mapaGrafica.put("tipo", "mixto");
						mapaGrafica.put("lista", marcasAgregadas);
						Sessions.getCurrent().setAttribute("grafica",
								mapaGrafica);
						ventana = (Window) Executions.createComponents(
								"/vistas/reportes/VGrafica.zul", null,
								mapaGrafica);
						ventana.doModal();
						break;
					case "Grafico Vendido VS Planificado Marcas (Angular)":
						tipo = 25;
						mapaGrafica = new HashMap<String, Object>();
						mapaGrafica.put("idAliado", aliado);
						mapaGrafica.put("desde", desde);
						mapaGrafica.put("hasta", hasta);
						mapaGrafica.put("tipo", "gauge");
						mapaGrafica.put("lista", marcasAgregadas);
						Sessions.getCurrent().setAttribute("grafica",
								mapaGrafica);
						ventana = (Window) Executions.createComponents(
								"/vistas/reportes/VGrafica.zul", null,
								mapaGrafica);
						ventana.doModal();
						break;
					case "Grafico Vendido VS Activado Marcas (Angular)":
						tipo = 25;
						mapaGrafica = new HashMap<String, Object>();
						mapaGrafica.put("idAliado", aliado);
						mapaGrafica.put("desde", desde);
						mapaGrafica.put("hasta", hasta);
						mapaGrafica.put("tipo", "gauge");
						mapaGrafica.put("tipo2", "activado");
						mapaGrafica.put("lista", marcasAgregadas);
						Sessions.getCurrent().setAttribute("grafica",
								mapaGrafica);
						ventana = (Window) Executions.createComponents(
								"/vistas/reportes/VGrafica.zul", null,
								mapaGrafica);
						ventana.doModal();
						break;
					}
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void buscar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void ayuda() {
				// TODO Auto-generated method stub

			}

			@Override
			public void annadir() {
				// TODO Auto-generated method stub

			}
		};
		Button guardar = (Button) botonera.getChildren().get(3);
		guardar.setLabel("Generar");
		guardar.setImage("/public/imagenes/botones/reporte.png");
		botonera.getChildren().get(0).setVisible(false);
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(false);
		botonera.getChildren().get(4).setVisible(false);
		botonera.getChildren().get(6).setVisible(false);
		botonera.getChildren().get(8).setVisible(false);
		botoneraReporte.appendChild(botonera);
	}

	private void llenarLista() {
		marcasAgregadas.clear();
		ltbMarcasAgregadas.getItems().clear();
		marcas = servicioMarca.buscarTodosOrdenados();
		ltbMarcas.setModel(new ListModelList<MaestroMarca>(marcas));
		listasMultiples();
	}

	private void listasMultiples() {
		ltbMarcas.setMultiple(false);
		ltbMarcas.setCheckmark(false);
		ltbMarcas.setMultiple(true);
		ltbMarcas.setCheckmark(true);

		ltbMarcasAgregadas.setMultiple(false);
		ltbMarcasAgregadas.setCheckmark(false);
		ltbMarcasAgregadas.setMultiple(true);
		ltbMarcasAgregadas.setCheckmark(true);
	}

	protected boolean validar() {
		if (!rowAliado.isVisible() && idAliado == null) {
			Mensaje.mensajeAlerta("Su usuario no esta asociado a ningun Aliado, "
					+ "pongase en contacto con el Administrador del sistema");
			return false;
		} else {
			if (rowAliado.isVisible() && idAliado == null) {
				Mensaje.mensajeAlerta("Debe seleccionar un Aliado");
				return false;
			} else {
				if (cmbReporte.getText().compareTo("") == 0) {
					Mensaje.mensajeAlerta("Debe seleccionar un Reporte");
					return false;
				} else {
					if (box.isVisible() && marcasAgregadas.isEmpty()) {
						Mensaje.mensajeAlerta("Debe seleccionar al menos una Marca para generar el grafico");
						return false;
					} else {
						if (cmbReporte
								.getValue()
								.equals("Grafico Vendido VS Planificado Marcas (Angular)")
								&& marcasAgregadas.size() != 1) {
							Mensaje.mensajeAlerta("Para este Grafico solo debe agregar una Marca");
							return false;
						} else
							return true;
					}
				}
			}
		}
	}

	@Listen("onClick = #btnBuscarAliado")
	public void mostrarCatalogoAliado() {
		List<MaestroAliado> listaLlenadora = new ArrayList<MaestroAliado>();
		MaestroAliado objeto = new MaestroAliado();
		objeto.setCodigoAliado("TODOS");
		objeto.setNombre("TODOS");
		objeto.setZona("TODAS");
		objeto.setDescripcionVendedor("TODOS");
		listaLlenadora.add(objeto);
		listaLlenadora.addAll(servicioAliado.buscarTodosOrdenados());
		final List<MaestroAliado> listaObjetos = listaLlenadora;
		catalogoAliado = new Catalogo<MaestroAliado>(divCatalogoAliado,
				"Catalogo de Aliados", listaObjetos, true, false, false,
				"Codigo", "Nombre", "Zona", "Vendedor") {

			@Override
			protected List<MaestroAliado> buscar(List<String> valores) {

				List<MaestroAliado> lista = new ArrayList<MaestroAliado>();

				for (MaestroAliado objeto : listaObjetos) {
					if (objeto.getCodigoAliado().toLowerCase()
							.contains(valores.get(0).toLowerCase())
							&& objeto.getNombre().toLowerCase()
									.contains(valores.get(1).toLowerCase())
							&& objeto.getDescripcionZona().toLowerCase()
									.contains(valores.get(2).toLowerCase())
							&& objeto.getDescripcionVendedor().toLowerCase()
									.contains(valores.get(3).toLowerCase())) {
						lista.add(objeto);
					}
				}
				return lista;
			}

			@Override
			protected String[] crearRegistros(MaestroAliado objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getCodigoAliado();
				registros[1] = objeto.getNombre();
				registros[2] = objeto.getDescripcionZona();
				registros[3] = objeto.getDescripcionVendedor();
				return registros;
			}
		};
		catalogoAliado.setClosable(true);
		catalogoAliado.setWidth("80%");
		catalogoAliado.setParent(divCatalogoAliado);
		catalogoAliado.doModal();
	}

	@Listen("onSeleccion = #divCatalogoAliado")
	public void seleccionAliado() {
		MaestroAliado aliado = catalogoAliado.objetoSeleccionadoDelCatalogo();
		txtAliado.setValue(aliado.getCodigoAliado());
		lblAliado.setValue(aliado.getNombre());
		idAliado = aliado.getCodigoAliado();
		catalogoAliado.setParent(null);
	}

	@Listen("onOK = #txtAliado; onChange = #txtAliado")
	public void buscarNombreAliado() {
		MaestroAliado aliado = servicioAliado.buscar(txtAliado.getValue());
		if (aliado != null) {
			txtAliado.setValue(aliado.getCodigoAliado());
			lblAliado.setValue(aliado.getNombre());
			idAliado = aliado.getCodigoAliado();
		} else {
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
			txtAliado.setValue("");
			txtAliado.setFocus(true);
			lblAliado.setValue("");
		}
		cmbCliente.setValue("TODOS");
		cmbVendedor.setValue("TODOS");
		cmbZona.setValue("TODAS");
	}

	@Listen("onOpen = #cmbCliente")
	public void buscarClientes() {
		List<String> lista = new ArrayList<String>();
		lista.add("TODOS");
		lista.addAll(servicioVenta.buscarDistinctCliente(idAliado));
		cmbCliente.setModel(new ListModelList<String>(lista));
	}

	@Listen("onOpen = #cmbVendedor")
	public void buscarVendedores() {
		List<String> lista = new ArrayList<String>();
		lista.add("TODOS");
		lista.addAll(servicioVenta.buscarDistinctVendedor(idAliado));
		cmbVendedor.setModel(new ListModelList<String>(lista));
	}

	@Listen("onOpen = #cmbZona")
	public void buscarZoonas() {
		List<String> lista = new ArrayList<String>();
		lista.add("TODAS");
		lista.addAll(servicioVenta.buscarDistinctZona(idAliado));
		cmbZona.setModel(new ListModelList<String>(lista));
	}

	@Listen("onClick = #pasar1")
	public void derecha() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbMarcas.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					MaestroMarca marca = listItem.get(i).getValue();
					marcas.remove(marca);
					marcasAgregadas.add(marca);
					ltbMarcasAgregadas
							.setModel(new ListModelList<MaestroMarca>(
									marcasAgregadas));
					ltbMarcasAgregadas.renderAll();
					listitemEliminar.add(listItem.get(i));
					listItem.get(i).setSelected(false);
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbMarcas.removeItemAt(listitemEliminar.get(i).getIndex());
			ltbMarcas.renderAll();
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2")
	public void izquierda() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbMarcasAgregadas.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					MaestroMarca marca = listItem2.get(i).getValue();
					marcasAgregadas.remove(marca);
					marcas.add(0, marca);
					ltbMarcas.setModel(new ListModelList<MaestroMarca>(marcas));
					ltbMarcas.renderAll();
					listitemEliminar.add(listItem2.get(i));
					listItem2.get(i).setSelected(false);
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbMarcasAgregadas.removeItemAt(listitemEliminar.get(i).getIndex());
			ltbMarcasAgregadas.renderAll();
		}
		listasMultiples();
	}

	@Listen("onSelect = #cmbReporte")
	public void ocultar() {
		if (cmbReporte.getValue().startsWith("Grafico")) {
			if (!cmbReporte.getValue().equals(
					"Grafico Vendido VS Activado Marcas (Angular)")) {
				llenarLista();
				rowVendedor.setVisible(false);
				rowZona.setVisible(false);
				box.setVisible(true);
			}
		} else {
			rowVendedor.setVisible(true);
			rowZona.setVisible(true);
			box.setVisible(false);
		}
	}

}
