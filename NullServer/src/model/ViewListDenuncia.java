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
@Table(name = "view_list_denuncia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewListDenuncia.findAll", query = "SELECT v FROM ViewListDenuncia v"),
    @NamedQuery(name = "ViewListDenuncia.findByIdDenuncia", query = "SELECT v FROM ViewListDenuncia v WHERE v.idDenuncia = :idDenuncia"),
    @NamedQuery(name = "ViewListDenuncia.findByTipoDenuncia", query = "SELECT v FROM ViewListDenuncia v WHERE v.tipoDenuncia = :tipoDenuncia"),
    @NamedQuery(name = "ViewListDenuncia.findByObservacao", query = "SELECT v FROM ViewListDenuncia v WHERE v.observacao = :observacao"),
    @NamedQuery(name = "ViewListDenuncia.findByBairro", query = "SELECT v FROM ViewListDenuncia v WHERE v.bairro = :bairro"),
    @NamedQuery(name = "ViewListDenuncia.findByCidade", query = "SELECT v FROM ViewListDenuncia v WHERE v.cidade = :cidade"),
    @NamedQuery(name = "ViewListDenuncia.findByDataAconteceu", query = "SELECT v FROM ViewListDenuncia v WHERE v.dataAconteceu = :dataAconteceu"),
    @NamedQuery(name = "ViewListDenuncia.findByCaminho", query = "SELECT v FROM ViewListDenuncia v WHERE v.caminho = :caminho"),
    @NamedQuery(name = "ViewListDenuncia.findByIdUsuario", query = "SELECT v FROM ViewListDenuncia v WHERE v.idUsuario = :idUsuario")})
public class ViewListDenuncia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_denuncia")
    private Integer idDenuncia;
    @Column(name = "tipo_denuncia")
    private String tipoDenuncia;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "data_aconteceu")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAconteceu;
    @Column(name = "caminho")
    private String caminho;
    @Column(name = "id_usuario")
    private Integer idUsuario;

    public ViewListDenuncia() {
    }

    public Integer getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(Integer idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Date getDataAconteceu() {
        return dataAconteceu;
    }

    public void setDataAconteceu(Date dataAconteceu) {
        this.dataAconteceu = dataAconteceu;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
