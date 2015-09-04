package evaluacion.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import evaluacion.modelo.maestros.Competencia;

public interface ICompetenciaDAO extends JpaRepository<Competencia, Integer> {
	
	
	 @Query("select a from Competencia a where a.nivel = 'RECTORAS'")
		public List<Competencia> buscar();
	 
	 public Competencia findById(int id);

	public List<Competencia> findByIdStartingWithAllIgnoreCase(String valor);

	public List<Competencia> findByDescripcionStartingWithAllIgnoreCase(
			String valor);

	public List<Competencia> findByNivelStartingWithAllIgnoreCase(String valor);

	public List<Competencia> findByComentarioStartingWithAllIgnoreCase(
			String valor);

	public Competencia findByDescripcion(String descripcion);

	public List<Competencia> findByDescripcionAllIgnoreCase(String descripcion);
}
	
