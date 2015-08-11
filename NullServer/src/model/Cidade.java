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
@Table(name = "tb_cidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c"),
    @NamedQuery(name = "Cidade.findByIdCidade", query = "SELECT c FROM Cidade c WHERE c.idCidade = :idCidade"),
    @NamedQuery(name = "Cidade.findByNome", query = "SELECT c FROM Cidade c WHERE c.nome = :nome")})
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="cidade_seq", sequenceName="tb_cidade_id_cidade_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cidade_seq")
    @Basic(optional = false)
    @Column(name = "id_cidade")
    private Integer idCidade;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidade", fetch=FetchType.LAZY)
    private List<Bairro> bairroList;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estado estado;

    public Cidade() {
    }

    public Cidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public Cidade(Integer idCidade, String nome) {
        this.idCidade = idCidade;
        this.nome = nome;
    }

    public Cidade(Integer idCidade, String nome, Estado estado) {
		super();
		this.idCidade = idCidade;
		this.nome = nome;
		this.estado = estado;
	}

	public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Bairro> getBairroList() {
        return bairroList;
    }

    public void setBairroList(List<Bairro> bairroList) {
        this.bairroList = bairroList;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado idEstado) {
        this.estado = idEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCidade != null ? idCidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.idCidade == null && other.idCidade != null) || (this.idCidade != null && !this.idCidade.equals(other.idCidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cidade[ idCidade=" + idCidade + " ]";
    }
    
}
