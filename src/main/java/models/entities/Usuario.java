package models.entities;
public class Usuario {
    private int idUsuario;
    String email;
    private String nombreUsuario;
    private String clave;
    private String foto;
    private String tipoUsuario;
    private String estado;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String clave, String foto, String tipoUsuario, String estado) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.foto = foto;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", email=" + email + ", nombreUsuario=" + nombreUsuario + ", clave="
				+ clave + ", foto=" + foto + ", tipoUsuario=" + tipoUsuario + ", estado=" + estado + "]";
	}

}