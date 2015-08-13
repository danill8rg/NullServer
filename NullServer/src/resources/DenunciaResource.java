package resources;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.jna.platform.win32.WinGDI.BITMAPINFO;

import model.Denuncia;
import model.ImagemDenuncia;
import model.LocalDenuncia;
import model.Mensagem;
import model.TipoDenuncia;
import model.Usuario;
import service.ImagemDenunciaService;
import service.MensagemService;
import service.TipoDenunciaService;
import service.UsuarioService;
import service.impl.DenunciaServiceImpl;
import service.impl.ImagemDenunciaServiceImpl;
import service.impl.LocalDenunciaServiceImpl;
import service.impl.MensagemServiceImpl;
import service.impl.TipoDenunciaServiceImpl;
import service.impl.UsuarioServiceImpl;
import util.Utils;

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
@Path("/denuncia")
public class DenunciaResource extends SuperResource{
	
	@GET
	@Path("{id}")
	public Response getUsuario(@PathParam("id") int id)	{
		setService(new DenunciaServiceImpl());
		try{
			Denuncia denuncia = (Denuncia) getService().consultarObjetoId(id);
			List<ImagemDenuncia> listImg = new ArrayList<ImagemDenuncia>();
			ImagemDenunciaService servImagem = new ImagemDenunciaServiceImpl();
			listImg = servImagem.consultarPorDenuncia(id);
			
			List<Mensagem> listMen = new ArrayList<Mensagem>();
			MensagemService servMensagem = new MensagemServiceImpl();
			listMen = servMensagem.consultarPorDenuncia(id);
			
			JsonObject json_formatado_denuncia = gerarJsonDenuncia(denuncia, listImg, listMen );
			//String json = gson.toJson(user);
			return Response.ok(json_formatado_denuncia.toString()).build();	
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
	
//	@POST
//	@Path("addUser")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@ValidateOnExecution
//	public	Response addUsuario(String jsonRecebido)	{
//		try{
//			service = new UsuarioServiceImpl();
//			//Converte String JSON para objeto Java
//			JSONObject dados_array_json = new JSONObject(jsonRecebido);
//			
//			JSONObject dados_array = dados_array_json.getJSONObject("usuario");
//			String nome = dados_array.getString("nome");
//			String email = dados_array.getString("email");
//			String senha = dados_array.getString("senha");
//			
//			UsuarioService userService = new  UsuarioServiceImpl();
//			if(!userService.validarEmail(email)){
//				return Response.serverError().entity("E-mail já existe!").build();
//			}
//			
//			if(nome == null || email == null || senha == null || nome.equalsIgnoreCase("") || email.equalsIgnoreCase("") || senha.equalsIgnoreCase("")){
//				return Response.serverError().entity("Campo preenchido inadequadamente !").build();
//			}
//			Usuario user = new Usuario(nome,senha,email);
//			//user.setIdUsuario(0);
//			user = (Usuario) service.gravar(user);
//			
//			Gson gson = new Gson();
//			String json = gson.toJson(user);
//			return Response.ok(json, MediaType.APPLICATION_JSON).build();
//		}catch(Exception e){
//			e.printStackTrace();
//			 return Response.serverError().entity(e.getMessage()).build();
//		}
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateOnExecution
	public	Response addDenuncia(String jsonRecebido)	{
		try{
			//Converte String JSON para objeto Java
			JSONObject dados_array_json = new JSONObject(jsonRecebido);
	
			String latitude = dados_array_json.getString("latitude");
			String longitude = dados_array_json.getString("longitude");
			String tipoDenuncia = dados_array_json.getString("tipoDenuncia");
			String observacao = dados_array_json.getString("observacao");
			String imagem = dados_array_json.getString("imagem");
			String formatoImage = dados_array_json.getString("formatoImage");
			String dataAconteceu = dados_array_json.getString("dataAconteceu");
			if(latitude == null || longitude == null || tipoDenuncia == null || observacao.equalsIgnoreCase("") || latitude.equalsIgnoreCase("") || longitude.equalsIgnoreCase("")){
				return Response.serverError().entity("Campo preenchido inadequadamente !").build();
			}
			
			LocalDenuncia local = new LocalDenuncia(latitude, longitude);
			service = new LocalDenunciaServiceImpl();
			local = (LocalDenuncia) service.gravar(local);
			
			TipoDenunciaService serviceTipoDenucia = new TipoDenunciaServiceImpl();
			TipoDenuncia tipo = serviceTipoDenucia.consultarDescricao(tipoDenuncia);
			
			if(tipo.getIdTipoDenuncia()  == null || tipo.getIdTipoDenuncia() == 0 ) {
				tipo.setIdTipoDenuncia(1);
			}
			
			service = new UsuarioServiceImpl();
			Usuario user = (Usuario) service.consultarObjetoId(Integer.parseInt("1"));
			
			service = new DenunciaServiceImpl();
			Denuncia denuncia = new Denuncia();
			denuncia.setAtivo(true);
			if( dataAconteceu != null ){
				denuncia.setDataAconteceu(new Date());
			}else{
				denuncia.setDataAconteceu(new Date());
			}
			denuncia.setLocalDenuncia(local);
			denuncia.setTipoDenuncia(tipo);
			denuncia.setObservacao(observacao);
			denuncia.setUsuario(user);			
			denuncia = (Denuncia) service.gravar(denuncia);
			
			if(imagem != null){
				byte[] arquivo = null;
				arquivo = Base64.decodeBase64(imagem.toString());
				String caminhoImagem = Utils.SalvarImagm(arquivo, formatoImage);
				ImagemDenuncia imgDenuncia = new ImagemDenuncia();
				imgDenuncia.setAtivo(true);
				imgDenuncia.setCaminho(caminhoImagem);
				imgDenuncia.setDenuncia(denuncia);
				imgDenuncia.setDescricao("...fazer posteriomente...");
				service = new ImagemDenunciaServiceImpl();
				imgDenuncia = (ImagemDenuncia) service.gravar(imgDenuncia);
			}
			String json = gerarJson(denuncia);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}catch(Exception e){
			e.printStackTrace();
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}

	private String gerarJson(Denuncia denuncia) {
		JSONObject json_denuncia = new JSONObject();
		json_denuncia.put("latitude", denuncia.getLocalDenuncia().getLatitude());
		json_denuncia.put("longitude", denuncia.getLocalDenuncia().getLongitude());
		json_denuncia.put("idDenuncia", denuncia.getIdDenuncia());
		json_denuncia.put("tipoDenuncia", denuncia.getTipoDenuncia().getDescricao());
		json_denuncia.put("idusuario", denuncia.getUsuario().getIdUsuario());
		json_denuncia.put("usuario", denuncia.getUsuario().getNome());
		return json_denuncia.toString();
	}

	private JsonObject gerarJsonDenuncia(Denuncia denuncia,
			List<ImagemDenuncia> listImg, List<Mensagem> listMen) {
		JsonArray arrayImagem = new JsonArray();
		JsonArray arrayMensagem = new JsonArray();
		JsonObject jsonDenuncia = new JsonObject();
		JsonObject auxJson;
		for(ImagemDenuncia imgDenuncia : listImg){
			auxJson = new JsonObject();
			auxJson.addProperty("caminho", imgDenuncia.getCaminho());
			String encodedImage2 = null;
	        String formatoImage = null;
			arrayImagem.add(auxJson);
		}
		for(Mensagem men: listMen){
			auxJson = new JsonObject();
			auxJson.addProperty("conteudo", men.getTexto());
			auxJson.addProperty("usuario", men.getUsuario().getNome());
			auxJson.addProperty("idusuario", men.getUsuario().getIdUsuario());
			auxJson.addProperty("dataAdicionado", men.getDataAdicionado().toString());
			arrayMensagem.add(auxJson);
		}
		jsonDenuncia.add("listMensagem", arrayMensagem);
		jsonDenuncia.add("listImagem", arrayImagem);
		jsonDenuncia.addProperty("observacao", denuncia.getObservacao());
		jsonDenuncia.addProperty("dataDenuncia", denuncia.getDataAconteceu().toString());
		jsonDenuncia.addProperty("tipoDenuncia", denuncia.getTipoDenuncia().getDescricao());
		jsonDenuncia.addProperty("usuarioDenuncia", denuncia.getUsuario().getNome());
		jsonDenuncia.addProperty("idUsuarioDenuncia", denuncia.getUsuario().getIdUsuario());
		return jsonDenuncia;
	}

}
