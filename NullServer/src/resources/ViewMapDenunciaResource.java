package resources;


import java.util.ArrayList;

import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.gson.Gson;

import model.Usuario;
import model.ViewMapDenuncia;
import service.UsuarioService;
import service.impl.UsuarioServiceImpl;
import service.impl.ViewMapDenunciaServiceImpl;

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
			ArrayList<ViewMapDenuncia> list= service.consultarTodos(view);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}

}
