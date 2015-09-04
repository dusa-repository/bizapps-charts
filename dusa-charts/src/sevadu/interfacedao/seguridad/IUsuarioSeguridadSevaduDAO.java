package sevadu.interfacedao.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sevadu.modelo.seguridad.GrupoSevadu;
import sevadu.modelo.seguridad.UsuarioSeguridadSevadu;
import evaluacion.modelo.seguridad.Grupo;
import evaluacion.modelo.seguridad.UsuarioSeguridad;

public interface IUsuarioSeguridadSevaduDAO extends JpaRepository<UsuarioSeguridadSevadu, String> {

	UsuarioSeguridadSevadu findByLogin(String nombre);

	List<UsuarioSeguridadSevadu> findByGrupos(GrupoSevadu grupo);

	UsuarioSeguridadSevadu findByLoginAndEmail(String value, String value2);

}