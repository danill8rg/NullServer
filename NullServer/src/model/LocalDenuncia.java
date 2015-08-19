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
@Table(name = "tb_local_denuncia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocalDenuncia.findAll", query = "SELECT l FROM LocalDenuncia l"),
    @NamedQuery(name = "LocalDenuncia.findByIdLocalDenuncia", query = "SELECT l FROM LocalDenuncia l WHERE l.idLocalDenuncia = :idLocalDenuncia"),
    @NamedQuery(name = "LocalDenuncia.findByLatitude", query = "SELECT l FROM LocalDenuncia l WHERE l.latitude = :latitude"),
    @NamedQuery(name = "LocalDenuncia.findByLongitude", query = "SELECT l FROM LocalDenuncia l WHERE l.longitude = :longitude"),
    @NamedQuery(name = "LocalDenuncia.findByRua", query = "SELECT l FROM LocalDenuncia l WHERE l.rua = :rua"),
    @NamedQuery(name = "LocalDenuncia.findByComplemento", query = "SELECT l FROM LocalDenuncia l WHERE l.complemento = :complemento"),
    @NamedQuery(name = "LocalDenuncia.findByCep", query = "SELECT l FROM LocalDenuncia l WHERE l.cep = :cep")})
public class LocalDenuncia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="local_seq", sequenceName="tb_local_denuncia_id_local_denuncia_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="local_seq")
    @Basic(optional = false)
    @Column(name = "id_local_denuncia")
    private Integer idLocalDenuncia;
    @Basic(optional = false)
    @Column(name = "latitude")
    private String latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "rua")
    private String rua;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "cep")
    private String cep;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localDenuncia")
    private List<Denuncia> denunciaList;
    @JoinColumn(name = "id_bairro", referencedColumnName = "id_bairro")
    @ManyToOne
    private Bairro bairro;

    public LocalDenuncia() {
    }

    public LocalDenuncia(Integer idLocalDenuncia) {
        this.idLocalDenuncia = idLocalDenuncia;
    }

    public LocalDenuncia(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocalDenuncia(Integer idLocalDenuncia, String latitude,
			String longitude, String rua, String complemento, String cep,
			List<Denuncia> denunciaList, Bairro bairro) {
		super();
		this.idLocalDenuncia = idLocalDenuncia;
		this.latitude = latitude;
		this.longitude = longitude;
		this.rua = rua;
		this.complemento = complemento;
		this.cep = cep;
		this.denunciaList = denunciaList;
		this.bairro = bairro;
	}

	public LocalDenuncia(Integer idLocalDenuncia, String latitude,
			String longitude, String rua, String complemento, String cep,
			Bairro bairro) {
		super();
		this.idLocalDenuncia = idLocalDenuncia;
		this.latitude = latitude;
		this.longitude = longitude;
		this.rua = rua;
		this.complemento = complemento;
		this.cep = cep;
		this.bairro = bairro;
	}

	public Integer getIdLocalDenuncia() {
        return idLocalDenuncia;
    }

    public void setIdLocalDenuncia(Integer idLocalDenuncia) {
        this.idLocalDenuncia = idLocalDenuncia;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @XmlTransient
    public List<Denuncia> getDenunciaList() {
        return denunciaList;
    }

    public void setDenunciaList(List<Denuncia> denunciaList) {
        this.denunciaList = denunciaList;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro idBairro) {
        this.bairro = idBairro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocalDenuncia != null ? idLocalDenuncia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalDenuncia)) {
            return false;
        }
        LocalDenuncia other = (LocalDenuncia) object;
        if ((this.idLocalDenuncia == null && other.idLocalDenuncia != null) || (this.idLocalDenuncia != null && !this.idLocalDenuncia.equals(other.idLocalDenuncia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LocalDenuncia[ idLocalDenuncia=" + idLocalDenuncia + " ]";
    }
    
}
