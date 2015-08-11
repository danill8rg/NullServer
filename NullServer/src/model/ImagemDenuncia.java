/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Notebook
 */
@Entity
@Table(name = "tb_imagem_denuncia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImagemDenuncia.findAll", query = "SELECT i FROM ImagemDenuncia i"),
    @NamedQuery(name = "ImagemDenuncia.findUltimo", query = "SELECT i FROM ImagemDenuncia i order by i.idImagemDenuncia desc"),
    @NamedQuery(name = "ImagemDenuncia.findByIdImagemDenuncia", query = "SELECT i FROM ImagemDenuncia i WHERE i.idImagemDenuncia = :idImagemDenuncia"),
    @NamedQuery(name = "ImagemDenuncia.findByCaminho", query = "SELECT i FROM ImagemDenuncia i WHERE i.caminho = :caminho"),
    @NamedQuery(name = "ImagemDenuncia.findByDescricao", query = "SELECT i FROM ImagemDenuncia i WHERE i.descricao = :descricao"),
    @NamedQuery(name = "ImagemDenuncia.findByIdDenuncia", query = "SELECT i FROM ImagemDenuncia i join Denuncia d on d.idDenuncia =  i.denuncia.idDenuncia WHERE i.denuncia.idDenuncia = :idDenuncia"),
    @NamedQuery(name = "ImagemDenuncia.findByAtivo", query = "SELECT i FROM ImagemDenuncia i WHERE i.ativo = :ativo")})
public class ImagemDenuncia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_imagem_denuncia")
    private Integer idImagemDenuncia;
    @Basic(optional = false)
    @Column(name = "caminho")
    private String caminho;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @JoinColumn(name = "id_denuncia", referencedColumnName = "id_denuncia")
    @ManyToOne(optional = false)
    private Denuncia denuncia;

    public ImagemDenuncia() {
    }

    public ImagemDenuncia(Integer idImagemDenuncia) {
        this.idImagemDenuncia = idImagemDenuncia;
    }

    public ImagemDenuncia(Integer idImagemDenuncia, String caminho, boolean ativo) {
        this.idImagemDenuncia = idImagemDenuncia;
        this.caminho = caminho;
        this.ativo = ativo;
    }

    public ImagemDenuncia(Integer idImagemDenuncia, String caminho,
			String descricao, boolean ativo, Denuncia denuncia) {
		super();
		this.idImagemDenuncia = idImagemDenuncia;
		this.caminho = caminho;
		this.descricao = descricao;
		this.ativo = ativo;
		this.denuncia = denuncia;
	}

	public Integer getIdImagemDenuncia() {
        return idImagemDenuncia;
    }

    public void setIdImagemDenuncia(Integer idImagemDenuncia) {
        this.idImagemDenuncia = idImagemDenuncia;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
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

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia idDenuncia) {
        this.denuncia = idDenuncia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagemDenuncia != null ? idImagemDenuncia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagemDenuncia)) {
            return false;
        }
        ImagemDenuncia other = (ImagemDenuncia) object;
        if ((this.idImagemDenuncia == null && other.idImagemDenuncia != null) || (this.idImagemDenuncia != null && !this.idImagemDenuncia.equals(other.idImagemDenuncia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ImagemDenuncia[ idImagemDenuncia=" + idImagemDenuncia + " ]";
    }
    
}
