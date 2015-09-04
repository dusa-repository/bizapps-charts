package sevadu.interfacedao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sevadu.modelo.maestros.Configuracion;

	public interface IConfiguracionDAO extends JpaRepository<Configuracion, Integer> {

}
