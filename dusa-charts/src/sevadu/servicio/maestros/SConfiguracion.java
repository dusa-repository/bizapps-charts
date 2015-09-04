package sevadu.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sevadu.interfacedao.maestros.IConfiguracionDAO;
import sevadu.modelo.maestros.Configuracion;

@Service("SConfiguracion")
public class SConfiguracion {

	@Autowired
	private IConfiguracionDAO configuracionDAO;

	public void guardar(Configuracion confi) {
		configuracionDAO.save(confi);

	}

	public Configuracion buscar(int i) {
		return configuracionDAO.findOne(i);
	}

	public List<Configuracion> buscarTodas() {
		return configuracionDAO.findAll();
	}

}
