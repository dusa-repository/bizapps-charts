package sevadu.interfacedao.maestros;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sevadu.modelo.maestros.Cliente;
import sevadu.modelo.maestros.MaestroAliado;
import sevadu.modelo.maestros.MarcaActivadaVendedor;
import sevadu.modelo.pk.MarcaActivadaPK;

public interface IMarcaActivadaVendedorDAO extends JpaRepository<MarcaActivadaVendedor, MarcaActivadaPK> {

	List<MarcaActivadaVendedor> findByIdMaestroAliadoIn(
			List<MaestroAliado> eliminarLista);

	List<MarcaActivadaVendedor> findByIdMaestroAliado(MaestroAliado aliado);

	List<MarcaActivadaVendedor> findByIdClienteIn(List<Cliente> eliminarLista);

	List<MarcaActivadaVendedor> findByIdCliente(Cliente cliente);

	List<MarcaActivadaVendedor> findByIdMaestroAliadoCodigoAliadoAndFechaAuditoriaBetween(
			String idAliado, Date desde, Date hasta);

}
