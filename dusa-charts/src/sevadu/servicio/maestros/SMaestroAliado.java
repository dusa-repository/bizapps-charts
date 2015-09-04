package sevadu.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sevadu.interfacedao.maestros.IMaestroAliadoDAO;
import sevadu.modelo.maestros.MaestroAliado;

@Service("SMaestroAliado")
public class SMaestroAliado {

	@Autowired
	private IMaestroAliadoDAO aliadoDAO;

	public boolean existe(String value) {
		return aliadoDAO.exists(value);
	}

	public List<MaestroAliado> buscarTodosOrdenados() {
		return aliadoDAO.findAllOrderByNombreAsc();
	}

	public void guardar(MaestroAliado aliado) {
		aliadoDAO.save(aliado);
	}

	public void eliminarVarios(List<MaestroAliado> eliminarLista) {
		aliadoDAO.delete(eliminarLista);
	}

	public void eliminarUno(String clave) {
		aliadoDAO.delete(clave);
	}

	public MaestroAliado buscar(String value) {
		return aliadoDAO.findOne(value);
	}

	public List<MaestroAliado> buscarRestantes(List<String> listaAliados) {
		if (!listaAliados.isEmpty())
			return aliadoDAO.findByCodigoAliadoNotIn(listaAliados);
		return aliadoDAO.findAllOrderByNombreAsc();
	}
}
