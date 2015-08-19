/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Notebook
 */
@Entity
@Table(name = "tb_denuncia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Denuncia.findAll", query = "SELECT d FROM Denuncia d"),
    @NamedQuery(name = "Denuncia.findByIdDenuncia", query = "SELECT d FROM Denuncia d WHERE d.idDenuncia = :idDenuncia"),
    @NamedQuery(name = "Denuncia.findByDataAconteceu", query = "SELECT d FROM Denuncia d WHERE d.dataAconteceu = :dataAconteceu"),
    @NamedQuery(name = "Denuncia.findByObservacao", query = "SELECT d FROM Denuncia d WHERE d.observacao = :observacao"),
    @NamedQuery(name = "Denuncia.findByAtivo", query = "SELECT d FROM Denuncia d WHERE d.ativo = :ativo")})
public class Denuncia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="denuncia_seq", sequenceName="tb_denuncia_id_denuncia_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="denuncia_seq")
    @Basic(optional = false)
    @Column(name = "id_denuncia")
    private Integer idDenuncia;
    @Basic(optional = false)
    @Column(name = "data_aconteceu")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAconteceu;
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @JoinColumn(name = "id_local_denuncia", referencedColumnName = "id_local_denuncia")
    @ManyToOne(optional = false)
    private LocalDenuncia localDenuncia;
    @JoinColumn(name = "id_tipo_denuncia", referencedColumnName = "id_tipo_denuncia")
    @ManyToOne(optional = false)
    private TipoDenuncia tipoDenuncia;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "denuncia")
    private List<ImagemDenuncia> imagemDenunciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "denuncia")
    private List<Mensagem> mensagemList;

    public Denuncia() {
    }

    public Denuncia(Integer idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public Denuncia(Integer idDenuncia, Date dataAconteceu, boolean ativo) {
        this.idDenuncia = idDenuncia;
        this.dataAconteceu = dataAconteceu;
        this.ativo = ativo;
    }

    public Denuncia(Integer idDenuncia, Date dataAconteceu, String observacao,
			boolean ativo, LocalDenuncia localDenuncia,
			TipoDenuncia tipoDenuncia, Usuario usuario) {
		super();
		this.idDenuncia = idDenuncia;
		this.dataAconteceu = dataAconteceu;
		this.observacao = observacao;
		this.ativo = ativo;
		this.localDenuncia = localDenuncia;
		this.tipoDenuncia = tipoDenuncia;
		this.usuario = usuario;
	}

	public Integer getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(Integer idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public Date getDataAconteceu() {
        return dataAconteceu;
    }

    public void setDataAconteceu(Date dataAconteceu) {
        this.dataAconteceu = dataAconteceu;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDenuncia getLocalDenuncia() {
        return localDenuncia;
    }

    public void setLocalDenuncia(LocalDenuncia idLocalDenuncia) {
        this.localDenuncia = idLocalDenuncia;
    }

    public TipoDenuncia getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(TipoDenuncia idTipoDenuncia) {
        this.tipoDenuncia = idTipoDenuncia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario idUsuario) {
        this.usuario = idUsuario;
    }

    @XmlTransient
    public List<ImagemDenuncia> getImagemDenunciaList() {
        return imagemDenunciaList;
    }

    public void setImagemDenunciaList(List<ImagemDenuncia> imagemDenunciaList) {
        this.imagemDenunciaList = imagemDenunciaList;
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
        hash += (idDenuncia != null ? idDenuncia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Denuncia)) {
            return false;
        }
        Denuncia other = (Denuncia) object;
        if ((this.idDenuncia == null && other.idDenuncia != null) || (this.idDenuncia != null && !this.idDenuncia.equals(other.idDenuncia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Denuncia[ idDenuncia=" + idDenuncia + " ]";
    }
    
}
