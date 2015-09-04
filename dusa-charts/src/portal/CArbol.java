package portal;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.A;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.West;
import org.zkoss.zul.Window;

import sevadu.modelo.seguridad.ArbolSevadu;
import utils.MArbol;
import utils.Nodos;
import evaluacion.modelo.seguridad.Arbol;

public class CArbol extends CGenericoCharts {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Tree arbolMenu;
	@Wire
	private Include contenido;
	@Wire
	private Label etiqueta;
	@Wire
	private Image imagenes;
	TreeModel<?> _model;
	URL url = getClass().getResource("/security/controlador/usuario.png");
	@Wire
	private Tab tab;
	@Wire
	private Tabbox tabBox;
	@Wire
	private West west;
	@Wire
	private Listbox ltbRoles;
	@Wire
	private Window wdwIndex;
	@Wire
	private Label lblUsuario;
	private Tabbox tabBox2;
	private Include contenido2;
	private Tab tab2;
	HashMap<String, Object> mapGeneral = new HashMap<String, Object>();
	String valor = null;
	String usuarioSes = null;

	@Override
	public void inicializar() {
		valor = Executions.getCurrent().getParameter("type");
		arbolMenu.setModel(getModel());
		usuarioSes = Executions.getCurrent().getParameter("id");
		lblUsuario.setValue("Usuario: " + usuarioSes);
	}

	public TreeModel<?> getModel() {
		if (_model == null) {
			_model = new MArbol(getFooRoot());
		}
		return _model;
	}

	private Nodos getFooRoot() {
		Nodos root = new Nodos(null, 0, "");
		ArrayList<String> strings = new ArrayList<String>();
		if (valor.equals("1")) {
			strings.add("Resumen Macro");
			strings.add("Cumplimiento Objetivos");
			strings.add("Resumen General Brecha");
			strings.add("Cantidad Evaluados con Brecha");
			strings.add("Resumen Macro Gerencia");
			strings.add("Cumplimiento Objetivos Gerencia");
			strings.add("Resumen General Brecha Gerencia");
			strings.add("Resultados Empleado");
		} else {
			strings.add("Graficos");

		}
		String[] stockArr = new String[strings.size()];
		stockArr = strings.toArray(stockArr);
		return crearArbol(root, stockArr);
	}

	private Nodos crearArbol(Nodos roote, String... arreglo) {
		Nodos oneLevelNode = new Nodos(roote, 0, "Graficas");
		roote.appendChild(oneLevelNode);
		Nodos two = null;
		for (int i = 0; i < arreglo.length; i++) {
			two = new Nodos(oneLevelNode, i + 1, arreglo[i]);
			oneLevelNode.appendChild(two);
		}
		return roote;
	}

	@Listen("onClick = #arbolMenu")
	public void selectedNode() {
		if (arbolMenu.getSelectedItem() != null) {
			Treecell celda = (Treecell) arbolMenu.getSelectedItem()
					.getChildren().get(0).getChildren().get(0);
			int item = Integer.valueOf(celda.getId());
			boolean abrir = true;
			Tab taba = new Tab();
			final Arbol arbolItem = servicioArbol
					.buscarPorNombreArbol(arbolMenu.getSelectedItem()
							.getLabel());
			ArbolSevadu arbolSec = new ArbolSevadu();
			String nombre = null;
			String url = "null";
			Boolean menu = false;
			Long idArbol = (long) 0;
			if (arbolItem != null) {
				nombre = arbolItem.getNombre();
				url = arbolItem.getUrl();
				menu = arbolItem.getMenu();
				idArbol = arbolItem.getIdArbol();
			} else {
				arbolSec = servicioArbolSevadu.buscarPorNombreArbol(arbolMenu
						.getSelectedItem().getLabel());
				nombre = arbolSec.getNombre();
				url = arbolSec.getUrl();
				menu = arbolSec.getMenu();
				idArbol = arbolSec.getIdArbol();
			}
			mapGeneral.put("titulo", nombre);
			if (nombre != null) {
				if (item != 0) {
					if (menu != null)
						if (menu)
							west.setOpen(false);
					for (int i = 0; i < tabs.size(); i++) {
						if (tabs.get(i).getLabel().equals(nombre)) {
							abrir = false;
							taba = tabs.get(i);
						}
					}
					if (abrir) {
						final String nombreReal = nombre;
						String ruta = "/vistas/" + url + ".zul";
						contenido = new Include();
						contenido.setSrc(null);
						contenido.setSrc(ruta);
						Tab newTab = new Tab(nombre);
						newTab.setClosable(true);
						newTab.addEventListener(Events.ON_CLOSE,
								new EventListener<Event>() {
									@Override
									public void onEvent(Event arg0)
											throws Exception {
										for (int i = 0; i < tabs.size(); i++) {
											if (tabs.get(i).getLabel()
													.equals(nombreReal)) {
												if (i == (tabs.size() - 1)
														&& tabs.size() > 1) {
													tabs.get(i - 1)
															.setSelected(true);
												}
												tabs.get(i).close();
												tabs.remove(i);
												if (tabs.isEmpty())
													if (!west.isOpen())
														west.setOpen(true);
											}
										}
									}
								});
						newTab.setSelected(true);
						Tabpanel newTabpanel = new Tabpanel();
						newTabpanel.appendChild(contenido);
						tabBox.getTabs().insertBefore(newTab, tab);
						newTabpanel.setParent(tabBox.getTabpanels());
						tabs.add(newTab);
						mapGeneral.put("tabsGenerales", tabs);
						mapGeneral.put("idArbol", idArbol);
						mapGeneral.put("west", west);
						mapGeneral.put("usuarioSes", usuarioSes);
						Sessions.getCurrent().setAttribute("mapaGeneral",
								mapGeneral);
					} else
						taba.setSelected(true);
				} else {
					if (!arbolMenu.getSelectedItem().isOpen())
						arbolMenu.getSelectedItem().setOpen(true);
					else
						arbolMenu.getSelectedItem().setOpen(false);
				}
			} else {
				if (!arbolMenu.getSelectedItem().isOpen())
					arbolMenu.getSelectedItem().setOpen(true);
				else
					arbolMenu.getSelectedItem().setOpen(false);
			}

		}
		tabBox2 = tabBox;
		contenido2 = contenido;
		tab2 = tab;
	}

	@Listen("onClick = #mnuItem")
	public void cerrarTodas() {
		for (int i = 0; i < tabs.size(); i++) {
			tabs.get(i).close();
			tabs.remove(i);
			i--;
		}
	}
}
