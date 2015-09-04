package evaluacion.interfacedao.maestros;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import evaluacion.modelo.maestros.Empresa;

public interface IEmpresaDAO extends JpaRepository<Empresa, Integer> {

	public List<Empresa> findByIdStartingWithAllIgnoreCase(String valor);

	public List<Empresa> findByNombreStartingWithAllIgnoreCase(String valor);

	public List<Empresa> findByDireccionStartingWithAllIgnoreCase(String valor);

	public List<Empresa> findByTelefono1StartingWithAllIgnoreCase(String valor);

	public List<Empresa> findByTelefono2StartingWithAllIgnoreCase(String valor);

	public List<Empresa> findByIdEmpresaAuxiliarStartingWithAllIgnoreCase(String valor);

	public Empresa findByNombre(String nombre);

	public List<Empresa> findByNombreAllIgnoreCase(String nombre);


	
}