package sevadu.servicio.seguridad;

import sevadu.interfacedao.seguridad.IUsuarioDAO;

import java.util.List;

import sevadu.modelo.seguridad.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SUsuario")
public class SUsuario {

	@Autowired
	private IUsuarioDAO usuarioDAO;

	public List<Usuario> buscarTodos() {
		return usuarioDAO.findAll();
	}

	public void guardar(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	@Transactional
	public Usuario buscarUsuarioPorNombre(String nombre) {
		return usuarioDAO.findByLogin(nombre);
	}

	@Transactional
	public Usuario buscarPorCedula(String nombre) {
		return usuarioDAO.findByCedula(nombre);
	}

	public Usuario buscar(long id) {
		return usuarioDAO.findOne(id);
	}

	public void eliminarVarios(List<Usuario> eliminarLista) {
		usuarioDAO.delete(eliminarLista);
	}

	public void eliminarClave(long id) {
		usuarioDAO.delete(id);
	}

	public Usuario buscarPorCedulayCorreo(String value, String value2) {
		return usuarioDAO.findByCedulaAndEmail(value, value2);
	}

	public Usuario buscarPorLogin(String value) {
		return usuarioDAO.findByLogin(value);
	}

	public List<Usuario> buscarCorreosOcupados(List<String> lista) {
		return usuarioDAO.findByEmailIn(lista);
	}

	public List<Usuario> buscarCorreosLibres(List<String> lista) {
		if (lista.isEmpty())
			return usuarioDAO.findAll();
		return usuarioDAO.findByEmailNotIn(lista);
	}

	public Usuario buscarUltimo() {
		long id = usuarioDAO.findMaxId();
		if (id != 0)
			return usuarioDAO.findOne(id);
		else
			return null;
	}

}