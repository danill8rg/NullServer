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
@Table(name = "tb_pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p"),
    @NamedQuery(name = "Pais.findByIdPais", query = "SELECT p FROM Pais p WHERE p.idPais = :idPais"),
    @NamedQuery(name = "Pais.findByNome", query = "SELECT p FROM Pais p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pais.findByBandeira", query = "SELECT p FROM Pais p WHERE p.bandeira = :bandeira"),
    @NamedQuery(name = "Pais.findByLingua", query = "SELECT p FROM Pais p WHERE p.lingua = :lingua")})
public class Pais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="pais_seq", sequenceName="tb_pais_id_pais_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pais_seq")
    @Basic(optional = false)
    @Column(name = "id_pais")
    private Integer idPais;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "bandeira")
    private String bandeira;
    @Column(name = "lingua")
    private String lingua;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    private List<Estado> estadoList;

    public Pais() {
    }

    public Pais(Integer idPais) {
        this.idPais = idPais;
    }

    public Pais(Integer idPais, String nome) {
        this.idPais = idPais;
        this.nome = nome;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
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

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    @XmlTransient
    public List<Estado> getEstadoList() {
        return estadoList;
    }

    public void setEstadoList(List<Estado> estadoList) {
        this.estadoList = estadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pais[ idPais=" + idPais + " ]";
    }
    
}
