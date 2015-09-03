package service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import model.Bairro;
import model.Cidade;
import model.Estado;
import model.LocalDenuncia;
import model.Pais;

import org.json.JSONArray;
import org.json.JSONObject;

import service.LocalDenunciaService;
import dao.BairroDao;
import dao.CidadeDao;
import dao.EstadoDao;
import dao.PaisDao;
import dao.impl.BairroDaoImpl;
import dao.impl.CidadeDaoImpl;
import dao.impl.EstadoDaoImpl;
import dao.impl.LocalDenunciaDaoImpl;
import dao.impl.PaisDaoImpl;

public class LocalDenunciaServiceImpl extends SuperServiceImpl<LocalDenuncia, Integer> implements LocalDenunciaService {
	
	@Override
	public LocalDenuncia gravar(LocalDenuncia local) {
		if(!validar(local)){
			return null;
		}
		setDao(new LocalDenunciaDaoImpl());
		try{
			local = procurarEndereco(local);
			local = getDao().gravar(local);
			return local;
		}catch(Exception e){
			System.out.println("Erro ao gravar LocalDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public LocalDenuncia atualizar(LocalDenuncia local) {
		if(!validar(local)){
			return null;
		}
		setDao(new LocalDenunciaDaoImpl());
		try{
			local = procurarEndereco(local);
			local = getDao().atualizar(local);
			return local;
		}catch(Exception e){
			System.out.println("Erro ao atualizar LocalDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public void excluir(LocalDenuncia object) {
		setDao(new LocalDenunciaDaoImpl());
		try{
			getDao().excluir(object);
		}catch(Exception e){
			System.out.println("Erro ao excluir LocalDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
		}		
		
	}

	@Override
	public ArrayList<LocalDenuncia> consultarTodos(LocalDenuncia object) {
		setDao(new LocalDenunciaDaoImpl());
		try{
			ArrayList<LocalDenuncia> arrayLocais = new  ArrayList<LocalDenuncia>(getDao().consultarTodos(object));
			for(LocalDenuncia local : arrayLocais){
				local.setDenunciaList(null);
			}
			return arrayLocais;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todas as imagem Denuncias");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return  new ArrayList<LocalDenuncia>();
		}				
	}

	@Override
	public LocalDenuncia consultarObjetoId(int entityID) {
		setDao(new LocalDenunciaDaoImpl());
		try{
			LocalDenuncia local= getDao().consultarObjetoId(entityID);
			if(local != null){
				local.setDenunciaList(null);
			}
			return local;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todas as imagem Denuncias");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return  new LocalDenuncia();
		}		
		
		
	}

	private boolean validar(LocalDenuncia object) {
		if(object != null ){
			return true;
		}
		return false;
	}
	
	private LocalDenuncia procurarEndereco(LocalDenuncia local) {
		
		try{
			String jsonString = null;
			URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + local.getLatitude() + "," + local.getLongitude() + "&sensor=true");
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();		
			conexao.connect();
		        int status = conexao.getResponseCode();

		        switch (status) {
		        	case 200:
		            case 201:{
		                BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream(),"UTF-8"));
		                StringBuilder sb = new StringBuilder();
		                String line;
		                while ((line = br.readLine()) != null) {
		                    sb.append(line);
		                }
		                br.close();
		                jsonString = sb.toString();
		            }break;
		            default : {
		            	System.out.println("Localiza��o n�o encontrada...");
		            }break;
		        }
		    if(jsonString == null) return local;
		    
		    String nomeBairro = null;
			String nomeCidade = null;
			String nomeEstado = null;
			String nomePais = null;
			String cep = null;
			String rua = null;
			
		    JSONObject json = new JSONObject(jsonString);
		    JSONArray array = json.getJSONArray("results");
		    JSONObject jsonteste = null;
		    for (int cont = 0; cont < array.length(); cont++){
		   
		    	  JSONObject arrayAddress = array.optJSONObject(cont);
		    	 if(arrayAddress != null){		    					    	 
			    		 JSONArray jsonArrayAux = arrayAddress.getJSONArray("address_components");
			    		for(int conta = 0 ; conta < jsonArrayAux.length() ; conta++ ){
			    			JSONObject jsonAux = jsonArrayAux.getJSONObject(conta);
			    			String long_name = null;
				    		 if(! jsonAux.isNull("long_name")){
				    			 long_name = jsonAux.getString("long_name");
				    		 }
				    		 String short_name = null;
				    		 if(! jsonAux.isNull("short_name")){
				    			 short_name = jsonAux.getString("short_name");
				    		 }
				    		 JSONArray types = null;
				    		 if(! jsonAux.isNull("types")){
				    			 types = jsonAux.getJSONArray("types");			    		 
				    		 }
				    		
				    		 String valor1 = null;
				    		 String valor2 = null;
				    		 
				    		 if(types != null && types.length() == 2 ){
				    			 valor1 = types.getString(0);
				    			 valor2 = types.getString(1);
				    		 }else{
				    			 valor1 = types.getString(0);
				    		 }
				    		 if(types.length() == 2 && valor1.equalsIgnoreCase("neighborhood") && valor2.equalsIgnoreCase("political")){
				    			 nomeBairro = long_name; 
				    		 }
				    		 if(types.length() == 2 && valor1.equalsIgnoreCase("administrative_area_level_2") && valor2.equalsIgnoreCase("political")){
				    			 nomeCidade = long_name; 
				    		 }
				    		 if(types.length() == 2 && valor1.equalsIgnoreCase("administrative_area_level_1") && valor2.equalsIgnoreCase("political")){
				    			 nomeEstado = short_name; 
				    		 }
				    		 if(types.length() == 2 && valor1.equalsIgnoreCase("country") && valor2.equalsIgnoreCase("political")){
				    			 nomePais = long_name; 
				    		 }
				    		 if(types.length() == 1 && valor1.equalsIgnoreCase("postal_code")){
				    			 cep = long_name; 
				    		 }
				    		 if(types.length() == 1 && valor1.equalsIgnoreCase("route")){
				    			 rua = long_name; 
				    		 }
			    		}
		    	 }
		    }
		    
		    local.setCep(cep); local.setRua(rua);
		    
		    Cidade cidade = null;
		    CidadeDao daoCidade = new CidadeDaoImpl();
		    if(nomeCidade != null){
		    	cidade = daoCidade.consultaNome(nomeCidade);
		    }
		    Bairro bairro = null;
		    BairroDao daoBairro = new BairroDaoImpl();
		    if(nomeBairro !=  null){
				bairro = daoBairro.consultarNome(nomeBairro);
		    }
		    Estado estado = null;
		    EstadoDao daoEstado = new EstadoDaoImpl();
		    if(nomeEstado != null){
				estado = daoEstado.consultarNome(nomeEstado);
		    }
			
		    Pais pais = null;
		    PaisDao daoPais = new PaisDaoImpl();
		    if(nomePais != null){
		    	pais = daoPais.consultarNome(nomePais);
		    }
			
		    if(nomePais != null && nomeEstado != null && nomeCidade != null && nomeBairro != null ){
				return salvarComTudo(nomePais,nomeEstado,nomeCidade,nomeBairro,pais,estado,cidade , bairro, local);
		    } else{
		    	if(nomePais != null && nomeEstado == null && nomeCidade != null && nomeBairro != null ){
					return salvarSemEstado(nomePais,nomeEstado,nomeCidade,nomeBairro,pais,estado,cidade , bairro, local);
			    } else{
			    	if(nomePais != null && nomeEstado != null && nomeCidade == null && nomeBairro != null ){
						return salvarSemCidade(nomePais,nomeEstado,nomeCidade,nomeBairro,pais,estado,cidade , bairro, local);
				    } else{
				    	if(nomePais != null && nomeEstado != null && nomeCidade != null && nomeBairro == null ){
							return salvarSemBairro(nomePais,nomeEstado,nomeCidade,nomeBairro,pais,estado,cidade , bairro, local);
					    } else{
					    	if(nomePais != null && nomeEstado == null && nomeCidade != null && nomeBairro == null ){
								return salvarSemEstadoSemBairro(nomePais,nomeEstado,nomeCidade,nomeBairro,pais,estado,cidade , bairro, local);
						    }
					    }
				    }
			    }
		    }
					return local;
		}catch(Exception e){
			return local;
		}
	}

	private LocalDenuncia salvarSemEstadoSemBairro(String nomePais,
			String nomeEstado, String nomeCidade, String nomeBairro, Pais pais,
			Estado estado, Cidade cidade, Bairro bairro, LocalDenuncia local) {
		try{
			CidadeDao daoCidade = new CidadeDaoImpl();
			BairroDao daoBairro = new BairroDaoImpl();
			EstadoDao daoEstado = new EstadoDaoImpl();
			PaisDao daoPais = new PaisDaoImpl();
			nomeBairro = "Nao informado";
			nomeEstado = "Nao informado";
			bairro = new Bairro();
			estado = new Estado();
			if(pais.getIdPais()== null ){
				pais.setNome(nomePais);
				pais = daoPais.gravar(pais);
				estado.setNome(nomeEstado);
				estado.setPais(pais);
				estado = daoEstado.gravar(estado);
				cidade.setNome(nomeCidade);
				cidade.setEstado(estado);
				cidade = daoCidade.gravar(cidade);
				bairro.setNome(nomeBairro);
				bairro.setCidade(cidade);
				bairro = daoBairro.gravar(bairro);
				local.setBairro(bairro);
				return local;
			}else{
				estado.setNome(nomeEstado);
				estado.setPais(pais);
				estado = daoEstado.gravar(estado);
				if(cidade.getIdCidade() == null){
					cidade.setNome(nomeCidade);
					cidade.setEstado(estado);
					cidade = daoCidade.gravar(cidade);
					bairro.setNome(nomeBairro);
					bairro.setCidade(cidade);
					bairro = daoBairro.gravar(bairro);
					local.setBairro(bairro);
					return local;
				}else{
					bairro.setNome(nomeBairro);
					bairro.setCidade(cidade);
					bairro = daoBairro.gravar(bairro);
					local.setBairro(bairro);
					return local;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return local;
	}

	private LocalDenuncia salvarSemBairro(String nomePais, String nomeEstado,
			String nomeCidade, String nomeBairro, Pais pais, Estado estado,
			Cidade cidade, Bairro bairro, LocalDenuncia local) {
		try{
			CidadeDao daoCidade = new CidadeDaoImpl();
			BairroDao daoBairro = new BairroDaoImpl();
			EstadoDao daoEstado = new EstadoDaoImpl();
			PaisDao daoPais = new PaisDaoImpl();
			nomeBairro = "Nao informado";
			bairro = new Bairro();
			if(pais.getIdPais()== null ){
				pais.setNome(nomePais);
				pais = daoPais.gravar(pais);
				estado.setNome(nomeEstado);
				estado.setPais(pais);
				estado = daoEstado.gravar(estado);
				cidade.setNome(nomeCidade);
				cidade.setEstado(estado);
				cidade = daoCidade.gravar(cidade);
				bairro.setNome(nomeBairro);
				bairro.setCidade(cidade);
				bairro = daoBairro.gravar(bairro);
				local.setBairro(bairro);
				return local;
			}else{
				if(estado.getIdEstado() == null){
					estado.setNome(nomeEstado);
					estado.setPais(pais);
					estado = daoEstado.gravar(estado);
					cidade.setNome(nomeCidade);
					cidade.setEstado(estado);
					cidade = daoCidade.gravar(cidade);
					bairro.setNome(nomeBairro);
					bairro.setCidade(cidade);
					bairro = daoBairro.gravar(bairro);
					local.setBairro(bairro);
					return local;
				}else{
					if(cidade.getIdCidade() == null){
						cidade.setNome(nomeCidade);
						cidade.setEstado(estado);
						cidade = daoCidade.gravar(cidade);
						bairro.setNome(nomeBairro);
						bairro.setCidade(cidade);
						bairro = daoBairro.gravar(bairro);
						local.setBairro(bairro);
						return local;
					}else{
						bairro.setNome(nomeBairro);
						bairro.setCidade(cidade);
						bairro = daoBairro.gravar(bairro);
						local.setBairro(bairro);
						return local;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return local;
	}

	private LocalDenuncia salvarSemCidade(String nomePais, String nomeEstado,
			String nomeCidade, String nomeBairro, Pais pais, Estado estado,
			Cidade cidade, Bairro bairro, LocalDenuncia local) {
		try{
			CidadeDao daoCidade = new CidadeDaoImpl();
			BairroDao daoBairro = new BairroDaoImpl();
			EstadoDao daoEstado = new EstadoDaoImpl();
			PaisDao daoPais = new PaisDaoImpl();
			nomeCidade = "Nao foi Informado";
			cidade = new Cidade();
			if(pais.getIdPais()== null ){
				pais.setNome(nomePais);
				pais = daoPais.gravar(pais);
				estado.setNome(nomeEstado);
				estado.setPais(pais);
				estado = daoEstado.gravar(estado);
				cidade.setNome(nomeCidade);
				cidade.setEstado(estado);
				cidade = daoCidade.gravar(cidade);
				bairro.setNome(nomeBairro);
				bairro.setCidade(cidade);
				bairro = daoBairro.gravar(bairro);
				local.setBairro(bairro);
				return local;
			}else{
				if(estado.getIdEstado() == null){
					estado.setNome(nomeEstado);
					estado.setPais(pais);
					estado = daoEstado.gravar(estado);
					cidade.setNome(nomeCidade);
					cidade.setEstado(estado);
					cidade = daoCidade.gravar(cidade);
					bairro.setNome(nomeBairro);
					bairro.setCidade(cidade);
					bairro = daoBairro.gravar(bairro);
					local.setBairro(bairro);
					return local;
				}else{
					cidade.setNome(nomeCidade);
					cidade.setEstado(estado);
					cidade = daoCidade.gravar(cidade);
					if(bairro.getIdBairro() == null){
						bairro.setNome(nomeBairro);
						bairro.setCidade(cidade);
						bairro = daoBairro.gravar(bairro);
						local.setBairro(bairro);
						return local;
					}
				}
			}
			if(bairro.getCidade().getIdCidade() != cidade.getIdCidade()){
				Bairro bairroAux = new Bairro();
				bairroAux.setNome(nomeBairro);
				bairroAux.setCidade(cidade);
				bairroAux = daoBairro.gravar(bairroAux);
				local.setBairro(bairroAux);
				return local;
			}
			local.setBairro(bairro);
			return local;
		}catch(Exception e){
			e.printStackTrace();
		}
		return local;
	}

	private LocalDenuncia salvarSemEstado(String nomePais, String nomeEstado,
			String nomeCidade, String nomeBairro, Pais pais, Estado estado,
			Cidade cidade, Bairro bairro, LocalDenuncia local) {
		try{
			CidadeDao daoCidade = new CidadeDaoImpl();
			BairroDao daoBairro = new BairroDaoImpl();
			EstadoDao daoEstado = new EstadoDaoImpl();
			PaisDao daoPais = new PaisDaoImpl();
			nomeEstado = "Nao informado";
			estado = new Estado();
			if(pais.getIdPais()== null ){
				pais.setNome(nomePais);
				pais = daoPais.gravar(pais);
				estado.setNome(nomeEstado);
				estado.setPais(pais);
				estado = daoEstado.gravar(estado);
				cidade.setNome(nomeCidade);
				cidade.setEstado(estado);
				cidade = daoCidade.gravar(cidade);
				bairro.setNome(nomeBairro);
				bairro.setCidade(cidade);
				bairro = daoBairro.gravar(bairro);
				local.setBairro(bairro);
				return local;
			}else{
				estado.setNome(nomeEstado);
				estado.setPais(pais);
				estado = daoEstado.gravar(estado);
				if(cidade.getIdCidade() == null){
					cidade.setNome(nomeCidade);
					cidade.setEstado(estado);
					cidade = daoCidade.gravar(cidade);
					bairro.setNome(nomeBairro);
					bairro.setCidade(cidade);
					bairro = daoBairro.gravar(bairro);
					local.setBairro(bairro);
					return local;
				}else{
					if(bairro.getIdBairro() == null){
						bairro.setNome(nomeBairro);
						bairro.setCidade(cidade);
						bairro = daoBairro.gravar(bairro);
						local.setBairro(bairro);
						return local;
					}
				}
			}
			if(bairro.getCidade().getIdCidade() != cidade.getIdCidade()){
				Bairro bairroAux = new Bairro();
				bairroAux.setNome(nomeBairro);
				bairroAux.setCidade(cidade);
				bairroAux = daoBairro.gravar(bairroAux);
				local.setBairro(bairroAux);
				return local;
			}
			local.setBairro(bairro);
			return local;
		}catch(Exception e){
			e.printStackTrace();
		}
		return local;
	}

	private LocalDenuncia salvarComTudo(String nomePais, String nomeEstado,
			String nomeCidade, String nomeBairro, Pais pais, Estado estado,
			Cidade cidade, Bairro bairro, LocalDenuncia local) {
		try{
			CidadeDao daoCidade = new CidadeDaoImpl();
			BairroDao daoBairro = new BairroDaoImpl();
			EstadoDao daoEstado = new EstadoDaoImpl();
			PaisDao daoPais = new PaisDaoImpl();
			if(pais.getIdPais()== null ){
				pais.setNome(nomePais);
				pais = daoPais.gravar(pais);
				estado.setNome(nomeEstado);
				estado.setPais(pais);
				estado = daoEstado.gravar(estado);
				cidade.setNome(nomeCidade);
				cidade.setEstado(estado);
				cidade = daoCidade.gravar(cidade);
				bairro.setNome(nomeBairro);
				bairro.setCidade(cidade);
				bairro = daoBairro.gravar(bairro);
				local.setBairro(bairro);
				return local;
			}else{
				if(estado.getIdEstado() == null){
					estado.setNome(nomeEstado);
					estado.setPais(pais);
					estado = daoEstado.gravar(estado);
					cidade.setNome(nomeCidade);
					cidade.setEstado(estado);
					cidade = daoCidade.gravar(cidade);
					bairro.setNome(nomeBairro);
					bairro.setCidade(cidade);
					bairro = daoBairro.gravar(bairro);
					local.setBairro(bairro);
					return local;
				}else{
					if(cidade.getIdCidade() == null){
						cidade.setNome(nomeCidade);
						cidade.setEstado(estado);
						cidade = daoCidade.gravar(cidade);
						bairro.setNome(nomeBairro);
						bairro.setCidade(cidade);
						bairro = daoBairro.gravar(bairro);
						local.setBairro(bairro);
						return local;
					}else{
						if(bairro.getIdBairro() == null){
							bairro.setNome(nomeBairro);
							bairro.setCidade(cidade);
							bairro = daoBairro.gravar(bairro);
							local.setBairro(bairro);
							return local;
						}
					}
				}
			}
			if(bairro.getCidade().getIdCidade() != cidade.getIdCidade()){
				Bairro bairroAux = new Bairro();
				bairroAux.setNome(nomeBairro);
				bairroAux.setCidade(cidade);
				bairroAux = daoBairro.gravar(bairroAux);
				local.setBairro(bairroAux);
				return local;
			}
			local.setBairro(bairro);
			return local;
		}catch(Exception e){
			e.printStackTrace();
		}
		return local;
	}

		
}
