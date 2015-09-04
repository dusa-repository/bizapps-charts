package sevadu.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sevadu.modelo.maestros.F0004;
import sevadu.modelo.pk.F0004PK;

public interface IF0004DAO extends JpaRepository<F0004, F0004PK> {

	@Query("Select f from F0004 f order by f.id.dtsy asc, f.id.dtrt asc")
	List<F0004> findAllOrderByIdDtsy();

	List<F0004> findByIdDtsy(String value);

}
