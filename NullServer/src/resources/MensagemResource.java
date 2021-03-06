package resources;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Denuncia;
import model.Mensagem;
import model.Usuario;
import model.ViewMensagem;

import org.json.JSONArray;
import org.json.JSONObject;

import service.DenunciaService;
import service.UsuarioService;
import service.ViewMensagemService;
import service.impl.DenunciaServiceImpl;
import service.impl.MensagemServiceImpl;
import service.impl.UsuarioServiceImpl;
import service.impl.ViewMensagemServiceImpl;
import util.UtilEmail;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 
 * @author Danillo...
 * Classe respons�vel por fornecer os modulos de servi�os disponiveis na 
 * webService para a classe Usuario...
 * Serao disponibilizados os servi�os de 
 * get dever� retornar sem a senha...
 * post adicionar ususario
 * put atualiza��o do usuario com senha antiga e nova...
 *
 */

//Sets the path to base URL + /usuario caminho para a URL...
@Path("/mensagem")
public class MensagemResource extends SuperResource{
	
	@GET
	@Path("{id}")
	public Response getMensangem(@PathParam("id") int id)	{
		ViewMensagemService serviceView = new ViewMensagemServiceImpl();
		try{
			List<ViewMensagem> listaMensagem = (serviceView.consultarPorDenuncia(id));
			Gson gson = new Gson();
			String json = gson.toJson(listaMensagem);
			System.out.println("mensagem = " + json);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
		
	}
	
	private String gerarJsonViewMensagem(List<ViewMensagem> listaMensagem) {
		JSONArray array_json = new JSONArray();
		SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("dd/MM/yyyy HH:mm:ss");
        
		for(ViewMensagem view : listaMensagem){
			JSONObject json = new JSONObject();
			if(view.getCaminhoImagem() != null){
				json.put("caminhoimagem", view.getCaminhoImagem());
			}else{
				json.put("caminhoimagem", "http://rcisistemas.minivps.info:8080/conexaocidada/ImagemDenuncia/usuarioSemImagem.png");
			}
			json.put("texto", view.getTexto());
			json.put("idDenuncia", view.getIdDenuncia());
			json.put("dataAdicionada", view.getDataAdicionado());
			json.put("nomeUsuario", view.getNomeUsuario());
			json.put("idMensagem", view.getIdMensagem());
			array_json.put(json);
		}
		
		return array_json.toString();
	}


	@POST
	@Path("addMensagem")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public	Response addUsuario(String jsonRecebido)	{
		try{
			service = new MensagemServiceImpl();
			//Converte String JSON para objeto Java
			JSONObject dados_array_json = new JSONObject(jsonRecebido);
			
			String texto = null; int idDenuncia = 1 ; int idUsuario = 1;
			
			if( ! dados_array_json.isNull("texto") ){
				texto = dados_array_json.getString("texto");
			}
			
			if( ! dados_array_json.isNull("idDenuncia") ){
				idDenuncia = dados_array_json.getInt("idDenuncia");
			}
			
			if( ! dados_array_json.isNull("idUsuario") ){
				idUsuario = dados_array_json.getInt("idUsuario");
			}
			
			UsuarioService userService = new  UsuarioServiceImpl();
			Usuario user = userService.consultarObjetoId(idUsuario);
			
			DenunciaService denunciaService = new DenunciaServiceImpl();
			Denuncia denuncia = denunciaService.consultarObjetoId(idDenuncia);
			
			if(denuncia == null) denuncia = new Denuncia(1);
			if(user == null) user = new Usuario(1);
			
			Mensagem mensage = new Mensagem();
			mensage.setAtivo(true);
			mensage.setDataAdicionado(new Date());
			mensage.setDenuncia(denuncia);
			mensage.setUsuario(user);
			mensage.setTexto(texto);
			mensage = (Mensagem) service.gravar(mensage);
			String json = gerarJsonMensagem(mensage);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}catch(Exception e){
			e.printStackTrace();
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("addMensagemSite")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public	Response addMensagemSite(String jsonRecebido)	{
		try{
			service = new MensagemServiceImpl();
			//Converte String JSON para objeto Java
			JSONObject json_recebido = new JSONObject(jsonRecebido);
			
			JSONObject dados_array_json = json_recebido.getJSONObject("mensagem");
			
			String texto = null; int idDenuncia = 1 ; int idUsuario = 1;
			
			if( ! dados_array_json.isNull("texto") ){
				texto = dados_array_json.getString("texto");
			}
			
			if( ! dados_array_json.isNull("idDenuncia") ){
				idDenuncia = dados_array_json.getInt("idDenuncia");
			}
			
			if( ! dados_array_json.isNull("idUsuario") &&  dados_array_json.getInt("idUsuario") != 0){
				idUsuario = dados_array_json.getInt("idUsuario");
			}
			
			UsuarioService userService = new  UsuarioServiceImpl();
			Usuario user = userService.consultarObjetoId(idUsuario);
			
			DenunciaService denunciaService = new DenunciaServiceImpl();
			Denuncia denuncia = denunciaService.consultarObjetoId(idDenuncia);
			
			if(denuncia == null) denuncia = new Denuncia(1);
			if(user == null) user = new Usuario(1);
			
			Mensagem mensage = new Mensagem();
			mensage.setAtivo(true);
			mensage.setDataAdicionado(new Date());
			mensage.setDenuncia(denuncia);
			mensage.setUsuario(user);
			mensage.setTexto(texto);
			mensage = (Mensagem) service.gravar(mensage);
			String json = gerarJsonMensagem(mensage);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}catch(Exception e){
			e.printStackTrace();
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}

	private String gerarJsonMensagem(Mensagem mensage) {
		JsonObject array_json = new JsonObject();
		SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("dd/MM/yyyy HH:mm:ss");
        
        array_json.addProperty("texto", mensage.getTexto());
        array_json.addProperty("idMensagem", mensage.getIdMensagem());
        array_json.addProperty("idUsuario", mensage.getUsuario().getIdUsuario());
        array_json.addProperty("dataAdicionado", sdf1.format(mensage.getDataAdicionado()));
        array_json.addProperty("idDenuncia", mensage.getDenuncia().getIdDenuncia());
			
		return array_json.toString();
	}

	
	@POST
	@Path("addcontato")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public	Response addContato(String jsonRecebido)	{
		try{
			service = new MensagemServiceImpl();
			//Converte String JSON para objeto Java
			JSONObject json_recebido = new JSONObject(jsonRecebido);
			
			JSONObject dados_array_json = json_recebido.getJSONObject("mensagem");
			
			String texto = ""; String email = "" ; String nome = "";
			
			if( ! dados_array_json.isNull("texto") ){
				texto = dados_array_json.getString("texto");
			}
			
			if( ! dados_array_json.isNull("email") ){
				email = dados_array_json.getString("email");
			}
			
			if( ! dados_array_json.isNull("nome") ){
				nome = dados_array_json.getString("nome");
			}
			
			String mensage = "";
			
			
			mensage += "Nome do usuario : " + nome;
			
			mensage += "E-mail do usuario : " + email;
			
			mensage += "Texto do usuario : " + texto;
			
			UtilEmail.mandaEmail("danill8rg@gmail.com", mensage);
			
			return Response.ok("ok", MediaType.APPLICATION_JSON).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.ok("").build();
		}
	}
}
