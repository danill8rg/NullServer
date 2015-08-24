/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "tb_mensagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensagem.findAll", query = "SELECT m FROM Mensagem m"),
    @NamedQuery(name = "Mensagem.findByIdMensagem", query = "SELECT m FROM Mensagem m WHERE m.idMensagem = :idMensagem"),
    @NamedQuery(name = "Mensagem.findByTexto", query = "SELECT m FROM Mensagem m WHERE m.texto = :texto"),
    //@NamedQuery(name = "Mensagem.findByDenuncia", query = "SELECT m FROM Mensagem m join Denuncia d on d.idDenuncia =  m.denuncia.idDenuncia WHERE m.denuncia.idDenuncia = :idDenuncia"),
    @NamedQuery(name = "Mensagem.findByDataAdicionado", query = "SELECT m FROM Mensagem m WHERE m.dataAdicionado = :dataAdicionado"),
    @NamedQuery(name = "Mensagem.findByAtivo", query = "SELECT m FROM Mensagem m WHERE m.ativo = :ativo")})
public class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="mensagem_seq", sequenceName="tb_mensagem_id_mensagem_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="mensagem_seq")
    @Basic(optional = false)
    @Column(name = "id_mensagem")
    private Integer idMensagem;
    @Basic(optional = false)
    @Column(name = "texto")
    private String texto;
    @Basic(optional = false)
    @Column(name = "data_adicionado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAdicionado;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @JoinColumn(name = "id_denuncia", referencedColumnName = "id_denuncia")
    @ManyToOne(optional = false)
    private Denuncia denuncia;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Mensagem() {
    }

    public Mensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Mensagem(Integer idMensagem, String texto, Date dataAdicionado, boolean ativo) {
        this.idMensagem = idMensagem;
        this.texto = texto;
        this.dataAdicionado = dataAdicionado;
        this.ativo = ativo;
    }

    public Mensagem(Integer idMensagem, String texto, Date dataAdicionado,
			boolean ativo, Denuncia denuncia, Usuario usuario) {
		super();
		this.idMensagem = idMensagem;
		this.texto = texto;
		this.dataAdicionado = dataAdicionado;
		this.ativo = ativo;
		this.denuncia = denuncia;
		this.usuario = usuario;
	}

	public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDataAdicionado() {
        return dataAdicionado;
    }

    public void setDataAdicionado(Date dataAdicionado) {
        this.dataAdicionado = dataAdicionado;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia idDenuncia) {
        this.denuncia = idDenuncia;
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
        hash += (idMensagem != null ? idMensagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensagem)) {
            return false;
        }
        Mensagem other = (Mensagem) object;
        if ((this.idMensagem == null && other.idMensagem != null) || (this.idMensagem != null && !this.idMensagem.equals(other.idMensagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Mensagem[ idMensagem=" + idMensagem + " ]";
    }
    
}
