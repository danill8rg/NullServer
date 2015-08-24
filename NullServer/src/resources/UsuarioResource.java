package resources;


import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Usuario;

import org.json.JSONObject;

import service.UsuarioService;
import service.impl.UsuarioServiceImpl;

import com.google.gson.Gson;
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
@Path("/usuario")
public class UsuarioResource extends SuperResource{
	
	@GET
	@Path("{id}")
	public Response getUsuario(@PathParam("id") int id)	{
		setService(new UsuarioServiceImpl());
		try{
			Usuario user = (Usuario) getService().consultarObjetoId(id);
			Gson gson = new Gson();
			String json = gson.toJson(user);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
		
	}
	
	@GET
	@Path("validaremail/{email}")
	public Response getValidarEmail(@PathParam("email") String email)	{
		UsuarioService userService = new  UsuarioServiceImpl();
		try{
			boolean result = userService.validarEmail(email);
			JSONObject json = new JSONObject();
			json.put("resultado", result);
			System.out.println(json.toString());
			return Response.ok(json.toString()).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
		
	}


	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public	Response getUsuarioList(){
		
		service = new UsuarioServiceImpl();
		try{
			Usuario user = new Usuario();
			ArrayList<Usuario> listUsuarios = service.consultarTodos(user);
			Gson gson = new Gson();
			String json = gson.toJson(listUsuarios);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public	Response addUsuario(String jsonRecebido)	{
		try{
			service = new UsuarioServiceImpl();
			//Converte String JSON para objeto Java
			JSONObject dados_array_json = new JSONObject(jsonRecebido);
			String nome = null; String email = null; String senha = null;
			JSONObject dados_array = dados_array_json.getJSONObject("usuario");
			if( ! dados_array.isNull("nome")){
				nome = dados_array.getString("nome");
			}
			if( ! dados_array.isNull("email")){
				email = dados_array.getString("email");
			}
			if( ! dados_array.isNull("senha")){
				senha = dados_array.getString("senha");
			}
						
			UsuarioService userService = new  UsuarioServiceImpl();
			
			Usuario user_Aux = new Usuario(nome,senha,email);			
			user_Aux = userService.logar(user_Aux);
			
			if(user_Aux != null){
				String json = gerarJsonUsuario(user_Aux);
				return Response.ok(json, MediaType.APPLICATION_JSON).build();
			}
			
			if(!userService.validarEmail(email)){
				return Response.serverError().entity("E-mail já existe!").build();
			}
			
			if(nome == null || email == null || senha == null || nome.equalsIgnoreCase("") || email.equalsIgnoreCase("") || senha.equalsIgnoreCase("")){
				return Response.serverError().entity("Campo preenchido inadequadamente !").build();
			}
			Usuario user = new Usuario(nome,senha,email);
			//user.setIdUsuario(0);
			user = (Usuario) userService.gravar(user);
			String json = gerarJsonUsuario(user);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}catch(Exception e){
			e.printStackTrace();
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}

	private String gerarJsonUsuario(Usuario user) {
		JsonObject json  = new JsonObject();
		json.addProperty("idUsuario", user.getIdUsuario());
		json.addProperty("nome", user.getNome());
		json.addProperty("email", user.getEmail());
		return json.toString();
	}
	
	@POST
	@Path("logar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public	Response logar(String jsonRecebido)	{
		try{
			JSONObject dados_array_json = new JSONObject(jsonRecebido);
			String email = null; String senha = null;
			JSONObject dados_array = dados_array_json.getJSONObject("usuario");
			if( ! dados_array.isNull("email")){
				email = dados_array.getString("email");
			}
			if( ! dados_array.isNull("senha")){
				senha = dados_array.getString("senha");
			}
						
			UsuarioService userService = new  UsuarioServiceImpl();
			
			Usuario user_Aux = new Usuario("",senha,email);			
			user_Aux = userService.logar(user_Aux);
			
			if(user_Aux != null){
				String json = gerarJsonUsuario(user_Aux);
				return Response.ok(json, MediaType.APPLICATION_JSON).build();
			}
			return Response.ok("", MediaType.APPLICATION_JSON).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.ok("", MediaType.APPLICATION_JSON).build();
		}
	}

}
