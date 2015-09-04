package sevadu.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sevadu.interfacedao.maestros.IConfiguracionEnvioCorreo;
import sevadu.modelo.maestros.ConfiguracionEnvioCorreo;

@Service("SConfiguracionEnvioCorreo")
public class SConfiguracionEnvioCorreo {

	@Autowired
	private IConfiguracionEnvioCorreo envioDAO;

	public List<ConfiguracionEnvioCorreo> buscarTodosOrdenados() {
		return envioDAO.findAllOrderByDestinatariosAsc();
	}

	public void guardar(ConfiguracionEnvioCorreo configuracion) {
		envioDAO.save(configuracion);
	}

	public void eliminarVarios(List<ConfiguracionEnvioCorreo> eliminarLista) {
		envioDAO.delete(eliminarLista);
	}

	public void eliminarUno(long clave) {
		envioDAO.delete(clave);
	}

	public ConfiguracionEnvioCorreo buscar(long clave) {
		return envioDAO.findOne(clave);
	}
}
