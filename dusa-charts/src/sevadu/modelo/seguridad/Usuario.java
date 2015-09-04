package sevadu.modelo.seguridad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import sevadu.modelo.bitacora.BitacoraEliminacion;
import sevadu.modelo.bitacora.BitacoraLogin;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario", schema="sevaduco_dusa_estadistica_ventas")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -7826333453902731478L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", unique = true, nullable = false)
	private long idUsuario;
	
	@Column(length = 50)
	private String email;

	@Column(length = 50)
	private String login;

	@Column(length = 15, unique = true, nullable = false)
	private String cedula;

	@Column(length = 256)
	private String password;

	@Lob
	private byte[] imagen;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean estado;

	// @Column(name = "fecha_auditoria")
	// private Timestamp fechaAuditoria;
	//
	// @Column(name = "hora_auditoria", length = 10)
	// private Timestamp horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	@Column(name = "primer_apellido", length = 100)
	private String primerApellido;

	@Column(name = "primer_nombre", length = 100)
	private String primerNombre;

	@Column(name = "segundo_apellido", length = 100)
	private String segundoApellido;

	@Column(name = "segundo_nombre", length = 100)
	private String segundoNombre;

	@Column(length = 1)
	private String sexo;

	@Column(length = 50)
	private String telefono;

	@Column(length = 500)
	private String direccion;

	@OneToMany(mappedBy = "usuario")
	private List<BitacoraEliminacion> bitacorasEliminacion;

	@OneToMany(mappedBy = "usuario")
	private List<BitacoraLogin> bitacorasLogin;

	@OneToMany(mappedBy = "id.usuario")
	private List<UsuarioAliado> usuariosAliados;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(long cedula, String cedulas, String email, String login,
			String password, byte[] imagen, boolean estado,
			String nombre, String apellido, String segundoNombre,
			String segundoApellido, String sexo, String telefono,
			String direccion) {
		super();
		this.idUsuario = cedula;
		this.cedula = cedulas;
		this.email = email;
		this.login = login;
		this.password = password;
		this.imagen = imagen;
		this.estado = estado;
		this.primerNombre = nombre;
		this.primerApellido = apellido;
		this.segundoNombre = segundoNombre;
		this.segundoApellido = segundoApellido;
		this.sexo = sexo;
		this.telefono = telefono;
		this.direccion = direccion;
		// this.fechaAuditoria = fechaAuditoria;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	// public Timestamp getFechaAuditoria() {
	// return fechaAuditoria;
	// }
	//
	// public void setFechaAuditoria(Timestamp fechaAuditoria) {
	// this.fechaAuditoria = fechaAuditoria;
	// }
	//
	// public Timestamp getHoraAuditoria() {
	// return horaAuditoria;
	// }
	//
	// public void setHoraAuditoria(Timestamp horaAuditoria) {
	// this.horaAuditoria = horaAuditoria;
	// }

	public String getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<BitacoraEliminacion> getBitacorasEliminacion() {
		return bitacorasEliminacion;
	}

	public void setBitacorasEliminacion(
			List<BitacoraEliminacion> bitacorasEliminacion) {
		this.bitacorasEliminacion = bitacorasEliminacion;
	}

	public List<BitacoraLogin> getBitacorasLogin() {
		return bitacorasLogin;
	}

	public void setBitacorasLogin(List<BitacoraLogin> bitacorasLogin) {
		this.bitacorasLogin = bitacorasLogin;
	}

	public List<UsuarioAliado> getUsuariosAliados() {
		return usuariosAliados;
	}

	public void setUsuariosAliados(List<UsuarioAliado> usuariosAliados) {
		this.usuariosAliados = usuariosAliados;
	}
}