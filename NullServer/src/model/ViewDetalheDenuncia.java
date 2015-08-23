/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@Table(name = "view_detalhe_denuncia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewDetalheDenuncia.findAll", query = "SELECT v FROM ViewDetalheDenuncia v"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByIdDenuncia", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.idDenuncia = :idDenuncia"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByDataAconteceu", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.dataAconteceu = :dataAconteceu"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByObservacao", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.observacao = :observacao"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByLatitude", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.latitude = :latitude"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByLongitude", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.longitude = :longitude"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByTipoDenuncia", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.tipoDenuncia = :tipoDenuncia"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByNomeDenunciante", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.nomeDenunciante = :nomeDenunciante"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByIdDenunciante", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.idDenunciante = :idDenunciante"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByBairro", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.bairro = :bairro"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByCidade", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.cidade = :cidade"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByEstado", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.estado = :estado"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByPais", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.pais = :pais"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByRua", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.rua = :rua"),
    @NamedQuery(name = "ViewDetalheDenuncia.findByCep", query = "SELECT v FROM ViewDetalheDenuncia v WHERE v.cep = :cep")})
public class ViewDetalheDenuncia implements Serializable {
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
    @Column(name = "nome_denunciante")
    private String nomeDenunciante;
    @Column(name = "id_denunciante")
    private Integer idDenunciante;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado")
    private String estado;
    @Column(name = "pais")
    private String pais;
    @Column(name = "rua")
    private String rua;
    @Column(name = "cep")
    private String cep;

    public ViewDetalheDenuncia() {
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

    public String getNomeDenunciante() {
        return nomeDenunciante;
    }

    public void setNomeDenunciante(String nomeDenunciante) {
        this.nomeDenunciante = nomeDenunciante;
    }

    public Integer getIdDenunciante() {
        return idDenunciante;
    }

    public void setIdDenunciante(Integer idDenunciante) {
        this.idDenunciante = idDenunciante;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
}
