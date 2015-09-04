package evaluacion.servicio.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import evaluacion.interfacedao.seguridad.IArbolDAO;
import evaluacion.modelo.seguridad.Arbol;
import evaluacion.modelo.seguridad.Grupo;
import sevadu.interfacedao.seguridad.IArbolSevaduDAO;
import sevadu.modelo.seguridad.ArbolSevadu;
import sevadu.modelo.seguridad.GrupoSevadu;

@Service("SArbol")
public class SArbol {

	@Autowired
	private IArbolDAO arbolDAO;

	public void guardar(Arbol arbol) {
		arbolDAO.save(arbol);
	}

	public Arbol buscar(long id) {

		return arbolDAO.findOne(id);
	}

	public List<Arbol> listarArbol() {
		return arbolDAO.buscarTodos();
	}

	public List<Arbol> buscarOrdenados() {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("orden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return arbolDAO.findAll(o);
	}

	public Arbol buscarPorNombreArbol(String nombre) {
		List<Arbol> arbol = new ArrayList<Arbol>();
		arbol = arbolDAO.findByNombre(nombre);
		if (!arbol.isEmpty())
			return arbol.get(0);
		else
			return null;
	}

	public List<Arbol> ordenarPorID(ArrayList<Long> ids) {

		List<Arbol> arboles;
		arboles = arbolDAO.buscar(ids);
		return arboles;

	}

	public Arbol buscarPorId(Long id) {
		Arbol arbol = arbolDAO.findByIdArbol(id);
		return arbol;
	}

	public List<Arbol> buscarporGrupo(Grupo grupo) {
		List<Arbol> arboles;
		arboles = arbolDAO.findByGruposArbol(grupo);
		return arboles;
	}

	public void eliminarUno(long clave) {
		arbolDAO.delete(clave);

	}

	public void eliminarVarios(List<Arbol> eliminarLista) {
		arbolDAO.delete(eliminarLista);

	}

	public List<Arbol> ordenarPorOrden(ArrayList<Long> ids) {
		return arbolDAO.findByIdArbolInOrderByOrdenAsc(ids);
	}

}
