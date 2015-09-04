package sevadu.interfacedao.seguridad;

import java.util.List;

import sevadu.modelo.pk.UsuarioAliadoPK;
import sevadu.modelo.seguridad.Usuario;
import sevadu.modelo.seguridad.UsuarioAliado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioAliadoDAO extends
		JpaRepository<UsuarioAliado, UsuarioAliadoPK> {

	List<UsuarioAliado> findByIdUsuarioLogin(String u);

	UsuarioAliado findByEstadoTrue();

	UsuarioAliado findByIdUsuarioAndEstadoTrue(Usuario u);

	List<UsuarioAliado> findByIdUsuario(Usuario usuario);

}
