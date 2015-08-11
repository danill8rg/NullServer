/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Notebook
 */
@Entity
@Table(name = "tb_bairro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bairro.findAll", query = "SELECT b FROM Bairro b"),
    @NamedQuery(name = "Bairro.findByIdBairro", query = "SELECT b FROM Bairro b WHERE b.idBairro = :idBairro"),
    @NamedQuery(name = "Bairro.findByNome", query = "SELECT b FROM Bairro b WHERE b.nome = :nome")})
public class Bairro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="bairro_seq", sequenceName="tb_bairro_id_bairro_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="bairro_seq")
    @Basic(optional = false)
    @Column(name = "id_bairro")
    private Integer idBairro;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "bairro", fetch=FetchType.LAZY)
    private List<LocalDenuncia> localDenunciaList;
    @JoinColumn(name = "id_cidade", referencedColumnName = "id_cidade")
    @ManyToOne(optional = false)
    private Cidade cidade;

    public Bairro() {
    }

    public Bairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public Bairro(Integer idBairro, String nome) {
        this.idBairro = idBairro;
        this.nome = nome;
    }

    public Bairro(Integer idBairro, String nome, Cidade cidade) {
		super();
		this.idBairro = idBairro;
		this.nome = nome;
		this.cidade = cidade;
	}

	public Integer getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<LocalDenuncia> getLocalDenunciaList() {
        return localDenunciaList;
    }

    public void setLocalDenunciaList(List<LocalDenuncia> localDenunciaList) {
        this.localDenunciaList = localDenunciaList;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade idCidade) {
        this.cidade = idCidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBairro != null ? idBairro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bairro)) {
            return false;
        }
        Bairro other = (Bairro) object;
        if ((this.idBairro == null && other.idBairro != null) || (this.idBairro != null && !this.idBairro.equals(other.idBairro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Bairro[ idBairro=" + idBairro + " ]";
    }
    
}
