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
@Table(name = "tb_estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findByIdEstado", query = "SELECT e FROM Estado e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "Estado.findByNome", query = "SELECT e FROM Estado e WHERE e.nome = :nome"),
    @NamedQuery(name = "Estado.findByBandeira", query = "SELECT e FROM Estado e WHERE e.bandeira = :bandeira")})
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="estado_seq", sequenceName="tb_estado_id_estado_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="estado_seq")
    @Basic(optional = false)
    @Column(name = "id_estado")
    private Integer idEstado;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "bandeira")
    private String bandeira;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private List<Cidade> cidadeList;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(optional = false)
    private Pais pais;

    public Estado() {
    }

    public Estado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Estado(Integer idEstado, String nome) {
        this.idEstado = idEstado;
        this.nome = nome;
    }

    public Estado(Integer idEstado, String nome, String bandeira, Pais pais) {
		super();
		this.idEstado = idEstado;
		this.nome = nome;
		this.bandeira = bandeira;
		this.pais = pais;
	}

	public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    @XmlTransient
    public List<Cidade> getCidadeList() {
        return cidadeList;
    }

    public void setCidadeList(List<Cidade> cidadeList) {
        this.cidadeList = cidadeList;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais idPais) {
        this.pais = idPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Estado[ idEstado=" + idEstado + " ]";
    }
    
}
