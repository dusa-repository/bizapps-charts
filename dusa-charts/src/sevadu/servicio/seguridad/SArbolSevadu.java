package sevadu.servicio.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import sevadu.interfacedao.seguridad.IArbolSevaduDAO;
import sevadu.modelo.seguridad.ArbolSevadu;
import sevadu.modelo.seguridad.GrupoSevadu;

@Service("SArbolSevadu")
public class SArbolSevadu {

	@Autowired
	private IArbolSevaduDAO arbolDAO;

	public void guardar(ArbolSevadu arbol) {
		arbolDAO.save(arbol);
	}

	public ArbolSevadu buscar(long id) {

		return arbolDAO.findOne(id);
	}

	public List<ArbolSevadu> listarArbol() {
		return arbolDAO.buscarTodos();
	}

	public List<ArbolSevadu> buscarOrdenados() {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("orden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return arbolDAO.findAll(o);
	}

	public ArbolSevadu buscarPorNombreArbol(String nombre) {
		List<ArbolSevadu> arbol = new ArrayList<ArbolSevadu>();
		arbol = arbolDAO.findByNombre(nombre);
		if (!arbol.isEmpty())
			return arbol.get(0);
		else
			return null;
	}

	public List<ArbolSevadu> ordenarPorID(ArrayList<Long> ids) {

		List<ArbolSevadu> arboles;
		arboles = arbolDAO.buscar(ids);
		return arboles;

	}

	public ArbolSevadu buscarPorId(Long id) {
		ArbolSevadu arbol = arbolDAO.findByIdArbol(id);
		return arbol;
	}

	public List<ArbolSevadu> buscarporGrupo(GrupoSevadu grupo) {
		List<ArbolSevadu> arboles;
		arboles = arbolDAO.findByGruposArbol(grupo);
		return arboles;
	}

	public void eliminarUno(long clave) {
		arbolDAO.delete(clave);

	}

	public void eliminarVarios(List<ArbolSevadu> eliminarLista) {
		arbolDAO.delete(eliminarLista);

	}

	public List<ArbolSevadu> ordenarPorOrden(ArrayList<Long> ids) {
		return arbolDAO.findByIdArbolInOrderByOrdenAsc(ids);
	}

	public List<ArbolSevadu> buscarporListGrupo(List<GrupoSevadu> grupos) {
		List<ArbolSevadu> arboles;
		arboles = arbolDAO.findByGruposArbol(grupos);
		return arboles;
	}

}
