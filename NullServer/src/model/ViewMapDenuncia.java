package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Notebook
 */
@Entity
@Table(name = "view_map_denuncia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewMapDenuncia.findAll", query = "SELECT v FROM ViewMapDenuncia v"),
    @NamedQuery(name = "ViewMapDenuncia.findByIdDenuncia", query = "SELECT v FROM ViewMapDenuncia v WHERE v.idDenuncia = :idDenuncia"),
    @NamedQuery(name = "ViewMapDenuncia.findByDataAconteceu", query = "SELECT v FROM ViewMapDenuncia v WHERE v.dataAconteceu = :dataAconteceu"),
    @NamedQuery(name = "ViewMapDenuncia.findByObservacao", query = "SELECT v FROM ViewMapDenuncia v WHERE v.observacao = :observacao"),
    @NamedQuery(name = "ViewMapDenuncia.findByLatitude", query = "SELECT v FROM ViewMapDenuncia v WHERE v.latitude = :latitude"),
    @NamedQuery(name = "ViewMapDenuncia.findByLongitude", query = "SELECT v FROM ViewMapDenuncia v WHERE v.longitude = :longitude"),
    @NamedQuery(name = "ViewMapDenuncia.findByTipoDenuncia", query = "SELECT v FROM ViewMapDenuncia v WHERE v.tipoDenuncia = :tipoDenuncia"),
    @NamedQuery(name = "ViewMapDenuncia.findByNomeUsuario", query = "SELECT v FROM ViewMapDenuncia v WHERE v.nomeUsuario = :nomeUsuario")})
public class ViewMapDenuncia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_denuncia")
    private Integer idDenuncia;
    @Column(name = "data_aconteceu")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAconteceu;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "tipo_denuncia")
    private String tipoDenuncia;
    @Column(name = "nome_usuario")
    private String nomeUsuario;

    public ViewMapDenuncia() {
    }
    
    public ViewMapDenuncia(Integer idDenuncia, Date dataAconteceu, String observacao, String latitude, String longitude,
			String tipoDenuncia, String nomeUsuario) {
		super();
		this.idDenuncia = idDenuncia;
		this.dataAconteceu = dataAconteceu;
		this.observacao = observacao;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tipoDenuncia = tipoDenuncia;
		this.nomeUsuario = nomeUsuario;
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

    public String getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
}
