/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Notebook
 */
@Entity
@Table(name = "tb_tipo_denuncia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDenuncia.findAll", query = "SELECT t FROM TipoDenuncia t"),
    @NamedQuery(name = "TipoDenuncia.findByIdTipoDenuncia", query = "SELECT t FROM TipoDenuncia t WHERE t.idTipoDenuncia = :idTipoDenuncia"),
    @NamedQuery(name = "TipoDenuncia.findByDescricao", query = "SELECT t FROM TipoDenuncia t WHERE  lower(t.descricao) LIKE lower(:descricao)"),
    @NamedQuery(name = "TipoDenuncia.findByAtivo", query = "SELECT t FROM TipoDenuncia t WHERE t.ativo = :ativo")})
public class TipoDenuncia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="tipo_denuncia_seq", sequenceName="tb_tipo_denuncia_id_tipo_denuncia_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tipo_denuncia_seq")
    @Basic(optional = false)
    @Column(name = "id_tipo_denuncia")
    private Integer idTipoDenuncia;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDenuncia")
    private List<Denuncia> denunciaList;

    public TipoDenuncia() {
    }

    public TipoDenuncia(Integer idTipoDenuncia) {
        this.idTipoDenuncia = idTipoDenuncia;
    }

    public TipoDenuncia(Integer idTipoDenuncia, String descricao, boolean ativo) {
        this.idTipoDenuncia = idTipoDenuncia;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public Integer getIdTipoDenuncia() {
        return idTipoDenuncia;
    }

    public void setIdTipoDenuncia(Integer idTipoDenuncia) {
        this.idTipoDenuncia = idTipoDenuncia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDenuncia != null ? idTipoDenuncia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDenuncia)) {
            return false;
        }
        TipoDenuncia other = (TipoDenuncia) object;
        if ((this.idTipoDenuncia == null && other.idTipoDenuncia != null) || (this.idTipoDenuncia != null && !this.idTipoDenuncia.equals(other.idTipoDenuncia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TipoDenuncia[ idTipoDenuncia=" + idTipoDenuncia + " ]";
    }
    
}
