package resources;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.ViewListDenuncia;
import service.ViewListDenunciaService;
import service.impl.ViewListDenunciaServiceImpl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 
 * @author Danillo...
 * Classe responsável por fornecer os modulos de serviços disponiveis na 
 * webService para a classe Usuario...
 * Serao disponibilizados os serviços de 
 * get deverá retornar sem a senha...
 * post adicionar ususario
 * put atualização do usuario com senha antiga e nova...
 *
 */

//Sets the path to base URL + /usuario caminho para a URL...
@Path("/listDenuncia")
public class ViewListDenunciaResource extends SuperResource{

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public	Response getUsuarioList(){
		
		service = new ViewListDenunciaServiceImpl();
		try{
			ViewListDenuncia view = new ViewListDenuncia();
			ArrayList<ViewListDenuncia> listaDenuncias = service.consultarTodos(view);
	
			String json = gerarJsonListDenuncias(listaDenuncias);
			System.out.println(json);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}

	private String gerarJsonListDenuncias(
			ArrayList<ViewListDenuncia> listaDenuncias) {
		SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("dd/MM/yyyy HH:mm:ss");
        
		JsonArray arrayJson = new JsonArray();		
		for(int i = 0; i < listaDenuncias.size() && i < 15 ; i++ ){
			JsonObject json = new JsonObject();
			ViewListDenuncia view = listaDenuncias.get(i);
			if(view.getBairro() != null){
				json.addProperty("bairro", view.getBairro());
			}else{
				json.addProperty("bairro", "Nao informado");
			}
			if(view.getCidade() != null){
				json.addProperty("cidade", view.getCidade());
			}else{
				json.addProperty("cidade", "Nao informado");
			}
			if(view.getCaminho() != null){
				json.addProperty("caminhoImagem", view.getCaminho());
			}else{
				json.addProperty("caminhoImagem", "http://rcisistemas.minivps.info:8080/conexaocidada/ImagemDenuncia/semImagem.png");
			}
			json.addProperty("idDenuncia", view.getIdDenuncia());
			json.addProperty("dataAconteceu", sdf1.format(view.getDataAconteceu()));
			json.addProperty("observacao", view.getObservacao());
			json.addProperty("tipoDenuncia", view.getTipoDenuncia());
			arrayJson.add(json);
		}
		return arrayJson.toString();
	}
	
	@GET
	@Path("{id}")
	public Response getUsuario(@PathParam("id") int id)	{
		ViewListDenunciaService serviceView = new ViewListDenunciaServiceImpl();
		try{
			ArrayList<ViewListDenuncia> listaDenuncias = serviceView.consultarPorUsuario(id);
	
			String json = gerarJsonListDenunciasIlimitado(listaDenuncias);
			System.out.println(json);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
		
	}

	private String gerarJsonListDenunciasIlimitado(
			ArrayList<ViewListDenuncia> listaDenuncias) {
		SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("dd/MM/yyyy HH:mm:ss");
        
		JsonArray arrayJson = new JsonArray();		
		for(int i = 0; i < listaDenuncias.size(); i++ ){
			JsonObject json = new JsonObject();
			ViewListDenuncia view = listaDenuncias.get(i);
			if(view.getBairro() != null){
				json.addProperty("bairro", view.getBairro());
			}else{
				json.addProperty("bairro", "Nao informado");
			}
			if(view.getCidade() != null){
				json.addProperty("cidade", view.getCidade());
			}else{
				json.addProperty("cidade", "Nao informado");
			}
			if(view.getCaminho() != null){
				json.addProperty("caminhoImagem", view.getCaminho());
			}else{
				json.addProperty("caminhoImagem", "http://rcisistemas.minivps.info:8080/conexaocidada/ImagemDenuncia/semImagem.png");
			}
			json.addProperty("idDenuncia", view.getIdDenuncia());
			json.addProperty("dataAconteceu", sdf1.format(view.getDataAconteceu()));
			json.addProperty("observacao", view.getObservacao());
			json.addProperty("tipoDenuncia", view.getTipoDenuncia());
			arrayJson.add(json);
		}
		return arrayJson.toString();
	}
	
}
