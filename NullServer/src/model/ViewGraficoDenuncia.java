/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Notebook
 */
@Entity
@Table(name = "view_grafico_denuncia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewGraficoDenuncia.findAll", query = "SELECT v FROM ViewGraficoDenuncia v"),
    @NamedQuery(name = "ViewGraficoDenuncia.findById", query = "SELECT v FROM ViewGraficoDenuncia v WHERE v.id = :id"),
    @NamedQuery(name = "ViewGraficoDenuncia.findByTipoDenuncia", query = "SELECT v FROM ViewGraficoDenuncia v WHERE v.tipoDenuncia = :tipoDenuncia"),
    @NamedQuery(name = "ViewGraficoDenuncia.findByQuantidade", query = "SELECT v FROM ViewGraficoDenuncia v WHERE v.quantidade = :quantidade")})
public class ViewGraficoDenuncia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "tipo_denuncia")
    private String tipoDenuncia;
    @Column(name = "quantidade")
    private BigInteger quantidade;

    public ViewGraficoDenuncia() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public BigInteger getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigInteger quantidade) {
        this.quantidade = quantidade;
    }
    
}
