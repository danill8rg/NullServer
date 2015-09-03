package resources;


import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.ViewGraficoDenuncia;
import model.ViewMapDenuncia;
import service.ViewGraficoDenunciaService;
import service.impl.ViewGraficoDenunciaServiceImpl;
import service.impl.ViewMapDenunciaServiceImpl;

import com.google.gson.Gson;
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
@Path("/viewMap")
public class ViewMapDenunciaResource extends SuperResource{
	
	@GET
	@Path("{id}")
	public Response getView(@PathParam("id") int id)	{
		setService(new ViewMapDenunciaServiceImpl());
		try{
			ViewMapDenuncia view = (ViewMapDenuncia) getService().consultarObjetoId(id);
			Gson gson = new Gson();
			String json = gson.toJson(view);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
		
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public	Response getUsuarioList(){
		
		service = new ViewMapDenunciaServiceImpl();
		try{
			ViewMapDenuncia view = new ViewMapDenuncia();
			ArrayList<ViewMapDenuncia> list = service.consultarTodos(view);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("quantidade")
	public Response getQuantidade()	{
		ViewGraficoDenunciaService serviceView = new ViewGraficoDenunciaServiceImpl();
		try{
			ViewGraficoDenuncia view = new ViewGraficoDenuncia();
			ArrayList<ViewGraficoDenuncia> list = serviceView.consultarTodos(view);
			JsonArray json = gerarJsonViewGraficoDenuncia(list);
			System.out.println(json.toString());
			return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
		
	}


	private JsonArray gerarJsonViewGraficoDenuncia(
			ArrayList<ViewGraficoDenuncia> list) {
		JsonArray jsonArray = new JsonArray();
		for(ViewGraficoDenuncia view : list){
			JsonObject json = new JsonObject();
			json.addProperty("tipo", view.getTipoDenuncia());
			json.addProperty("qtde", view.getQuantidade());
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	@POST
	@Path("/teste")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getViewTeste()	{
		setService(new ViewMapDenunciaServiceImpl());
		try{
			JsonObject json = new JsonObject();
			json.addProperty("isThereMore", false);
			JsonArray jsonArray = new JsonArray();
		
			json.add("cars", jsonArray);
			
			return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
		
	}
	
	@GET
	@Path("/appMapa")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getViewAPpp()	{
		service = new ViewMapDenunciaServiceImpl();
		try{
			ViewMapDenuncia view = new ViewMapDenuncia();
			ArrayList<ViewMapDenuncia> list = service.consultarTodos(view);
			for(ViewMapDenuncia v : list ){
				v.setNomeUsuario(null);
				v.setObservacao(null);
			}
			Gson gson = new Gson();
			String json = gson.toJson(list);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}

}
