package sevadu.servicio.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import evaluacion.interfacedao.seguridad.IUsuarioSeguridadDAO;
import evaluacion.modelo.seguridad.Grupo;
import evaluacion.modelo.seguridad.UsuarioSeguridad;
import sevadu.interfacedao.seguridad.IUsuarioSeguridadSevaduDAO;
import sevadu.modelo.seguridad.GrupoSevadu;
import sevadu.modelo.seguridad.UsuarioSeguridadSevadu;

@Service("SUsuarioSeguridadSevadu")
public class SUsuarioSeguridadSevadu {

	@Autowired
	private IUsuarioSeguridadSevaduDAO usuarioDAO;

	public void guardar(UsuarioSeguridadSevadu usuario) {
		usuarioDAO.save(usuario);
	}

	public List<UsuarioSeguridadSevadu> buscarTodos() {
		return usuarioDAO.findAll();
	}

	public void eliminar(UsuarioSeguridadSevadu usuario) {
		usuarioDAO.delete(usuario);
	}

	public List<UsuarioSeguridadSevadu> buscarPorGrupo(GrupoSevadu grupo) {
		return usuarioDAO.findByGrupos(grupo);
	}

	@Transactional
	public UsuarioSeguridadSevadu buscarPorLogin(String value) {
		return usuarioDAO.findOne(value);
	}

	public UsuarioSeguridadSevadu buscarPorLoginyCorreo(String value, String value2) {
		return usuarioDAO.findByLoginAndEmail(value, value2);
	}

	public void eliminarVarios(List<UsuarioSeguridadSevadu> eliminarLista) {
		usuarioDAO.delete(eliminarLista);
	}

	public void guardarVarios(List<UsuarioSeguridadSevadu> eliminarLista) {
		usuarioDAO.save(eliminarLista);
	}

}