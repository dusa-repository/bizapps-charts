package evaluacion.interfacedao.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sevadu.modelo.seguridad.UsuarioSeguridadSevadu;
import evaluacion.modelo.seguridad.Grupo;
import evaluacion.modelo.seguridad.UsuarioSeguridad;

public interface IUsuarioSeguridadDAO extends JpaRepository<UsuarioSeguridad, String> {

	UsuarioSeguridad findByLogin(String nombre);

	List<UsuarioSeguridad> findByGrupos(Grupo grupo);

	UsuarioSeguridad findByLoginAndEmail(String value, String value2);

}