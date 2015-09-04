package evaluacion.interfacedao.maestros;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import evaluacion.modelo.maestros.UnidadOrganizativa;

public interface IUnidadOrganizativaDAO extends JpaRepository<UnidadOrganizativa, Integer> {

	public List<UnidadOrganizativa> findByIdStartingWithAllIgnoreCase(String valor);

	public List<UnidadOrganizativa> findByDescripcionStartingWithAllIgnoreCase(
			String valor);

	public List<UnidadOrganizativa> findByNivelStartingWithAllIgnoreCase(String valor);

	public List<UnidadOrganizativa> findBySubNivelStartingWithAllIgnoreCase(
			String valor);

	public List<UnidadOrganizativa> findByIdEmpresaAuxiliarStartingWithAllIgnoreCase(
			String valor);

	public List<UnidadOrganizativa> findByIdUnidadOrganizativaAuxiliarStartingWithAllIgnoreCase(
			String valor);

	public UnidadOrganizativa findByDescripcion(String descripcion);

	public List<UnidadOrganizativa> findByDescripcionAllIgnoreCase(
			String descripcion);

	public List<UnidadOrganizativa> findByGerenciaDescripcionStartingWithAllIgnoreCase(
			String valor);

	

	
}
