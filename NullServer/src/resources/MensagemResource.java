package resources;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Usuario;
import model.ViewMensagem;

import org.json.JSONObject;

import service.UsuarioService;
import service.ViewMensagemService;
import service.impl.UsuarioServiceImpl;
import service.impl.ViewMensagemServiceImpl;

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
@Path("/mensagem")
public class MensagemResource extends SuperResource{
	
	@GET
	@Path("{id}")
	public Response getUsuario(@PathParam("id") int id)	{
		ViewMensagemService serviceView = new ViewMensagemServiceImpl();
		try{
			List<ViewMensagem> listaMensagem = (serviceView.consultarPorDenuncia(id));
			String json = gerarJsonViewMensagem(listaMensagem);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
		
	}
	
	private String gerarJsonViewMensagem(List<ViewMensagem> listaMensagem) {
		JsonArray array_json = new JsonArray();
		SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("dd/MM/yyyy HH:mm:ss");
        
		for(ViewMensagem view : listaMensagem){
			JsonObject json = new JsonObject();
			if(view.getCaminhoImagem() != null){
				json.addProperty("caminhoimagem", view.getCaminhoImagem());
			}else{
				json.addProperty("caminhoimagem", "http://rcisistemas.minivps.info:8080/NullPointer/ImagemDenuncia/usuarioSemImagem.png");
			}
			json.addProperty("texto", view.getTexto());
			json.addProperty("idDenuncia", view.getIdDenuncia());
			json.addProperty("dataAdicionada", sdf1.format(view.getDataAdicionado()));
			json.addProperty("nomeUsuario", view.getNomeUsuario());
			json.addProperty("idMensagem", view.getIdMensagem());
			array_json.add(json);
		}
		
		return array_json.toString();
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
			
			JSONObject dados_array = dados_array_json.getJSONObject("usuario");
			String nome = dados_array.getString("nome");
			String email = dados_array.getString("email");
			String senha = dados_array.getString("senha");
			
			UsuarioService userService = new  UsuarioServiceImpl();
			if(!userService.validarEmail(email)){
				return Response.serverError().entity("E-mail já existe!").build();
			}
			
			if(nome == null || email == null || senha == null || nome.equalsIgnoreCase("") || email.equalsIgnoreCase("") || senha.equalsIgnoreCase("")){
				return Response.serverError().entity("Campo preenchido inadequadamente !").build();
			}
			Usuario user = new Usuario(nome,senha,email);
			//user.setIdUsuario(0);
			user = (Usuario) service.gravar(user);
			
			Gson gson = new Gson();
			String json = gson.toJson(user);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}catch(Exception e){
			e.printStackTrace();
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}

	


}
