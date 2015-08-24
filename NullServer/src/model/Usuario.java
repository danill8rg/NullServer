package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Notebook
 */
@Entity
@Table(name = "tb_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    //@NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE lower(u.email) LIKE lower(:email)"),
    //@NamedQuery(name = "Usuario.findByEmailAtivo", query = "SELECT u FROM Usuario u WHERE lower(u.email) LIKE lower(:email) and u.ativo = :ativo"),
    @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
    //@NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE lower(u.nome) LIKE lower(:nome)"),
    //@NamedQuery(name = "Usuario.findByNomeAtivo", query = "SELECT u FROM Usuario u WHERE lower(u.nome) LIKE lower(:nome) and u.ativo = :ativo"),
    @NamedQuery(name = "Usuario.logar", query = "SELECT u FROM Usuario u WHERE u.nome = :nome and u.senha = :senha"),
    @NamedQuery(name = "Usuario.logarEmail", query = "SELECT u FROM Usuario u WHERE u.email LIKE :email and u.senha LIKE :senha"),
    @NamedQuery(name = "Usuario.findByAtivo", query = "SELECT u FROM Usuario u WHERE u.ativo = :ativo")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="webuser_idwebuser_seq", sequenceName="tb_usuario_id_usuario_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="webuser_idwebuser_seq")
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch=FetchType.LAZY)
    private List<Denuncia> denunciaList;
    @JoinColumn(name = "id_tipo_usuario", referencedColumnName = "id_tipo_usuario")
    @ManyToOne(optional = false)
    private TipoUsuario tipoUsuario;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario", fetch=FetchType.LAZY)
    private DadosContaUsuario dadosContaUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch=FetchType.LAZY)
    private List<Mensagem> mensagemList;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String email, String senha, boolean ativo) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }
    
    public Usuario(String nome, String senha, String email ) {
    	this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = true;
        TipoUsuario tipoUsuario = new TipoUsuario(1,"Normal", true);
        this.tipoUsuario = tipoUsuario;
    }


    public Usuario(Integer idUsuario, String email, String senha, String nome,
			boolean ativo, TipoUsuario tipoUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.ativo = ativo;
		this.tipoUsuario = tipoUsuario;
	}

	public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public List<Denuncia> getDenunciaList() {
        return denunciaList;
    }

    public void setDenunciaList(List<Denuncia> denunciaList) {
        this.denunciaList = denunciaList;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario idTipoUsuario) {
        this.tipoUsuario = idTipoUsuario;
    }

    public DadosContaUsuario getDadosContaUsuario() {
        return dadosContaUsuario;
    }

    public void setDadosContaUsuario(DadosContaUsuario dadosContaUsuario) {
        this.dadosContaUsuario = dadosContaUsuario;
    }

    @XmlTransient
    public List<Mensagem> getMensagemList() {
        return mensagemList;
    }

    public void setMensagemList(List<Mensagem> mensagemList) {
        this.mensagemList = mensagemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
