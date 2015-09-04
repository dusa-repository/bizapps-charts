package sevadu.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sevadu.modelo.maestros.ConfiguracionEnvioCorreo;

public interface IConfiguracionEnvioCorreo extends
		JpaRepository<ConfiguracionEnvioCorreo, Long> {

	@Query("select c from ConfiguracionEnvioCorreo c order by c.destinatarios asc")
	List<ConfiguracionEnvioCorreo> findAllOrderByDestinatariosAsc();

}
