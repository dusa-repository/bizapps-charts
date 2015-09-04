package sevadu.servicio.seguridad;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sevadu.interfacedao.seguridad.IGrupoSevaduDAO;
import sevadu.modelo.seguridad.ArbolSevadu;
import sevadu.modelo.seguridad.GrupoSevadu;
import sevadu.modelo.seguridad.UsuarioSeguridadSevadu;
import evaluacion.interfacedao.seguridad.IGrupoDAO;
import evaluacion.modelo.seguridad.Arbol;
import evaluacion.modelo.seguridad.Grupo;
import evaluacion.modelo.seguridad.UsuarioSeguridad;

@Service("SGrupoSevadu")
public class SGrupoSevadu {

	@Autowired
	private IGrupoSevaduDAO grupoDAO;

	public void guardarGrupo(GrupoSevadu grupo) {
		grupoDAO.save(grupo);
	}

	public List<GrupoSevadu> buscarTodos() {
		return grupoDAO.findByEstadoTrue();
	}

	public GrupoSevadu buscarGrupo(long id) {
		return grupoDAO.findOne(id);
	}

	public List<GrupoSevadu> buscarGruposDelUsuario(UsuarioSeguridadSevadu usuario) {
		return grupoDAO.findByUsuarios(usuario);
	}

	public List<GrupoSevadu> buscarGruposDisponibles(List<Long> ids) {
		return grupoDAO.findByIdGrupoNotInAndEstadoTrue(ids);
	}

	public List<GrupoSevadu> buscarPorNombre(String nombreGrupo) {
		return grupoDAO.findByNombre(nombreGrupo);
	}

	public List<GrupoSevadu> filtroNombre(String valor) {
		return grupoDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public void eliminarUno(long id) {
		grupoDAO.delete(id);
	}

	public List<GrupoSevadu> buscarTodosOrdenados() {
		return grupoDAO.findAllOrderById();
	}

	public void eliminarVarios(List<GrupoSevadu> eliminarLista) {
		grupoDAO.delete(eliminarLista);

	}

	public List<GrupoSevadu> buscarGruposUsuario(UsuarioSeguridadSevadu u) {
		return grupoDAO.findByUsuariosOrderByNombreAsc(u);
	}

	public List<GrupoSevadu> buscarArboles(List<ArbolSevadu> eliminarLista) {
		Set<ArbolSevadu> arbols = new HashSet<ArbolSevadu>(eliminarLista);
		return grupoDAO.findByArbols(arbols);
	}

	public void guardarVarios(List<GrupoSevadu> eliminarLista) {
		grupoDAO.save(eliminarLista);
	}

}