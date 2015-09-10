package service.impl;

import java.util.ArrayList;
import java.util.List;

import model.Denuncia;
import service.DenunciaService;
import dao.DenunciaDao;
import dao.impl.DenunciaDaoImpl;

public class DenunciaServiceImpl extends SuperServiceImpl<Denuncia, Integer> implements DenunciaService {
	
	@Override
	public Denuncia gravar(Denuncia object) {
		dao = new DenunciaDaoImpl();
		if(validar(object)){
			return dao.gravar(object);
		}
		return null;
	}

	@Override
	public Denuncia atualizar(Denuncia object) {
		dao = new DenunciaDaoImpl();
		if(validar(object)){
			return dao.atualizar(object);
		}
		return null;
	}

	@Override
	public void excluir(Denuncia object) {
		dao = new DenunciaDaoImpl();
		dao.excluir(object);
	}

	@Override
	public ArrayList<Denuncia> consultarTodos(Denuncia object) {
		dao = new DenunciaDaoImpl();
		return new ArrayList<Denuncia>(dao.consultarTodos(object));
	}

	@Override
	public Denuncia consultarObjetoId(int entityID) {
		dao = new DenunciaDaoImpl();
		try{
			Denuncia denuncia  =  dao.consultarObjetoId(entityID);
			if(denuncia != null){
				denuncia.setImagemDenunciaList(null);
				denuncia.setMensagemList(null);
				return denuncia;
			}
			return new Denuncia();			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro consultar denuncia por ID erro =" + e.getMessage());
			return new Denuncia();
		}	
	}

	private boolean validar(Denuncia object) {
		if(object.getLocalDenuncia() != null && object.getLocalDenuncia().getIdLocalDenuncia() != null && object.getLocalDenuncia().getIdLocalDenuncia() != 0){
			return true;
		}
		return false;
	}

	@Override
	public List<Denuncia> consultarParaMapa() {
		DenunciaDao daoDenuncia = new DenunciaDaoImpl();
		return daoDenuncia.consultarParaMapa();
	}
}
