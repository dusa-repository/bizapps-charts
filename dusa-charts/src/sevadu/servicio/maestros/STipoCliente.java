package sevadu.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sevadu.interfacedao.maestros.ITipoClienteDAO;
import sevadu.modelo.maestros.TipoCliente;

@Service("STipoCliente")
public class STipoCliente {
	
	@Autowired
	private ITipoClienteDAO tipoClienteDAO;

	public List<TipoCliente> buscarTodos() {
		return tipoClienteDAO.findAll();
	}

	public void guardar(TipoCliente tipo) {
		tipoClienteDAO.save(tipo);
		
	}

	public void eliminarVarios(List<TipoCliente> eliminarLista) {
		tipoClienteDAO.delete(eliminarLista);
		
	}

	public void eliminarUno(String id) {
		tipoClienteDAO.delete(id);
		
	}

	public TipoCliente buscarPorCodigo(String value) {
		return tipoClienteDAO.findOne(value);
	}

}
