package dao;

import java.util.Date;
import java.util.List;

import model.Denuncia;
import model.TipoDenuncia;

public interface DenunciaDao extends SuperDao<Denuncia, Integer> {
	
	public List<Denuncia> consultarDataAconteceu(Date dataInicial, Date dataFinal, boolean Situacao);
	
	public List<Denuncia> consultarTipoDenuncia(TipoDenuncia tipoDenuncia, boolean Situacao);

	public List<Denuncia> consultarParaMapa();

}
