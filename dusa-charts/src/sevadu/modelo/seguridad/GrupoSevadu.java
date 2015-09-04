package sevadu.modelo.seguridad;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@Table(name = "grupo", schema="sevaduco_dusa_estadistica_ventas")
public class GrupoSevadu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_grupo", unique = true, nullable = false)
	private long idGrupo;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean estado;

	@Column(length = 500)
	private String nombre;

	// bi-directional many-to-many association to Arbol
	@ManyToMany
	@JoinTable(name = "arbol_grupo", joinColumns = { @JoinColumn(name = "id_grupo", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_arbol", nullable = false) })
	private Set<ArbolSevadu> arbols;

	// bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy = "grupos")
	private Set<UsuarioSeguridadSevadu> usuarios;

	public GrupoSevadu() {
	}

	public GrupoSevadu(long idGrupo, boolean estado, String nombre, Set<ArbolSevadu> arbols) {
		super();
		this.idGrupo = idGrupo;
		this.estado = estado;
		this.nombre = nombre;
		this.arbols = arbols;
	}

	public long getIdGrupo() {
		return this.idGrupo;
	}

	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<ArbolSevadu> getArbols() {
		return this.arbols;
	}

	public void setArbols(Set<ArbolSevadu> arbols) {
		this.arbols = arbols;
	}

	public Set<UsuarioSeguridadSevadu> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<UsuarioSeguridadSevadu> usuarios) {
		this.usuarios = usuarios;
	}

}