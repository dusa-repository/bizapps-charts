package sevadu.interfacedao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sevadu.modelo.maestros.TipoCliente;


public interface ITipoClienteDAO extends JpaRepository<TipoCliente, String> {

}
