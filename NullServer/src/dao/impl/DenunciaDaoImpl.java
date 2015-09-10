package dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Denuncia;
import model.TipoDenuncia;
import model.Usuario;
import dao.DenunciaDao;


public class DenunciaDaoImpl extends SuperDaoImpl<Denuncia, Integer> implements DenunciaDao {

	@Override
	public List<Denuncia> consultarDataAconteceu(Date dataInicial,
			Date dataFinal, boolean Situacao) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ativo", Situacao); 
        parameters.put("dataInicial", dataInicial); 
        parameters.put("dataFinal", dataFinal);
        return super.consultarPorNamedQueryEParametros("SELECT d FROM Denuncia d WHERE d.ativo = :ativo and d.dataAconteceu BETWEEN :dataInicial AND :dataFinal", parameters);
	}

	@Override
	public List<Denuncia> consultarTipoDenuncia(TipoDenuncia tipoDenuncia,
			boolean Situacao) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ativo", Situacao); 
        parameters.put("idTipoDenuncia", tipoDenuncia.getIdTipoDenuncia()); 
        return super.consultarPorNamedQueryEParametros("SELECT d FROM Denuncia d join d.tipoDenuncia WHERE d.ativo = :ativo and d.tipoDenuncia.idTipoDenuncia = :idTipoDenuncia", parameters);
		
	}

	
	public List<Denuncia> consultarAtivos() {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ativo", true);   
        return super.consultarPorNamedQueryEParametros("SELECT d FROM Denuncia d WHERE d.ativo = :ativo", parameters);
		
	}

	@Override
	public List<Denuncia> consultarParaMapa() {
		Map<String, Object> parameters = new HashMap<String, Object>();
        List<Denuncia> listaDenuncias = super.consultarPorNamedQueryEParametros("Denuncia.findAll", parameters);
        if(listaDenuncias != null){
        	return listaDenuncias;
        }
        return new ArrayList<Denuncia>();
	}

}
