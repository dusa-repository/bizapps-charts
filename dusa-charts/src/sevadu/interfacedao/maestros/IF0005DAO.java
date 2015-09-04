package sevadu.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sevadu.modelo.maestros.F0005;
import sevadu.modelo.pk.F0005PK;

public interface  IF0005DAO extends JpaRepository<F0005, F0005PK> {

	@Query("Select f from F0005 f order by f.id.drsy asc, f.id.drrt asc, f.id.drky asc")
	List<F0005> findAllOrderBySYAndRTAndKY();

	List<F0005> findByIdDrsyAndIdDrrtOrderByIdDrsyAscIdDrrtAsc(String string, String string2);

}
