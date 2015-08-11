package model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Notebook
 */
@Entity
@Table(name = "tb_dados_conta_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DadosContaUsuario.findAll", query = "SELECT d FROM DadosContaUsuario d"),
    @NamedQuery(name = "DadosContaUsuario.findByIdContaUsuario", query = "SELECT d FROM DadosContaUsuario d WHERE d.idContaUsuario = :idContaUsuario"),
    @NamedQuery(name = "DadosContaUsuario.findByNome", query = "SELECT d FROM DadosContaUsuario d WHERE d.nome = :nome"),
    @NamedQuery(name = "DadosContaUsuario.findByDataNascimento", query = "SELECT d FROM DadosContaUsuario d WHERE d.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "DadosContaUsuario.findByCaminhoImagem", query = "SELECT d FROM DadosContaUsuario d WHERE d.caminhoImagem = :caminhoImagem")})
public class DadosContaUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="dado_conta_seq", sequenceName="tb_dados_conta_usuario_id_conta_usuario_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="dado_conta_seq")
    @Basic(optional = false)
    @Column(name = "id_conta_usuario")
    private Integer idContaUsuario;
    @Column(name = "nome")
    private String nome;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    @Column(name = "caminho_imagem")
    private String caminhoImagem;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false)
    private Usuario usuario;

    public DadosContaUsuario() {
    }

    public DadosContaUsuario(Integer idContaUsuario) {
        this.idContaUsuario = idContaUsuario;
    }

    public DadosContaUsuario(Integer idContaUsuario, String nome,
			Date dataNascimento, String caminhoImagem, Usuario usuario) {
		super();
		this.idContaUsuario = idContaUsuario;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.caminhoImagem = caminhoImagem;
		this.usuario = usuario;
	}

	public Integer getIdContaUsuario() {
        return idContaUsuario;
    }

    public void setIdContaUsuario(Integer idContaUsuario) {
        this.idContaUsuario = idContaUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario idUsuario) {
        this.usuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContaUsuario != null ? idContaUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DadosContaUsuario)) {
            return false;
        }
        DadosContaUsuario other = (DadosContaUsuario) object;
        if ((this.idContaUsuario == null && other.idContaUsuario != null) || (this.idContaUsuario != null && !this.idContaUsuario.equals(other.idContaUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DadosContaUsuario[ idContaUsuario=" + idContaUsuario + " ]";
    }
    
}
