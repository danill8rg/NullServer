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
@Table(name = "view_mensagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewMensagem.findAll", query = "SELECT v FROM ViewMensagem v"),
    @NamedQuery(name = "ViewMensagem.findByIdMensagem", query = "SELECT v FROM ViewMensagem v WHERE v.idMensagem = :idMensagem"),
    @NamedQuery(name = "ViewMensagem.findByIdDenuncia", query = "SELECT v FROM ViewMensagem v WHERE v.idDenuncia = :idDenuncia"),
    @NamedQuery(name = "ViewMensagem.findByDataAdicionado", query = "SELECT v FROM ViewMensagem v WHERE v.dataAdicionado = :dataAdicionado"),
    @NamedQuery(name = "ViewMensagem.findByTexto", query = "SELECT v FROM ViewMensagem v WHERE v.texto = :texto"),
    @NamedQuery(name = "ViewMensagem.findByCaminhoImagem", query = "SELECT v FROM ViewMensagem v WHERE v.caminhoImagem = :caminhoImagem")})
public class ViewMensagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_mensagem")
    private Integer idMensagem;
    @Column(name = "id_denuncia")
    private Integer idDenuncia;
    @Column(name = "nome_usuario")
    private String nomeUsuario;
    @Column(name = "data_adicionado")
    private String dataAdicionado;
    @Column(name = "texto")
    private String texto;
    @Column(name = "caminho_imagem")
    private String caminhoImagem;

    public ViewMensagem() {
    }

    public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Integer getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(Integer idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDataAdicionado() {
        return dataAdicionado;
    }

    public void setDataAdicionado(String dataAdicionado) {
        this.dataAdicionado = dataAdicionado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
    
}
