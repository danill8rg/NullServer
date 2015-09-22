package resources;


import java.io.File;
import java.util.ArrayList;
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
import model.ImagemDenuncia;
import model.LocalDenuncia;
import model.Mensagem;
import model.TipoDenuncia;
import model.Usuario;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

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
@Path("/denuncia")
public class DenunciaResource extends SuperResource{
	
	@GET
	@Path("{id}")
	public Response getDenuncia(@PathParam("id") int id)	{
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
			if(latitude == null || longitude == null || tipoDenuncia == null || latitude.equalsIgnoreCase("") || longitude.equalsIgnoreCase("")){
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
			
			if(observacao != null && observacao.length() > 300 ){
				observacao = observacao.substring(0, 298);
			}
			
			service = new UsuarioServiceImpl();
			Usuario user = (Usuario) service.consultarObjetoId(Integer.parseInt("1"));
			
			service = new DenunciaServiceImpl();
			Denuncia denuncia = new Denuncia();
			denuncia.setDataAconteceu(new Date());
			denuncia.setAtivo(true);
			denuncia.setLocalDenuncia(local);
			denuncia.setTipoDenuncia(tipo);
			denuncia.setObservacao(observacao);
			denuncia.setUsuario(user);			
			denuncia = (Denuncia) service.gravar(denuncia);
			System.out.println("gravou denuncia...");
			if(imagem != null){
				byte[] arquivo = null;
				arquivo = Base64.decodeBase64(imagem.toString());
				String caminhoImagem = Utils.SalvarImagm(arquivo, formatoImage);
				
				File file_imagem = new File(caminhoImagem);
				
				ImagemDenuncia imgDenuncia = new ImagemDenuncia();
				imgDenuncia.setAtivo(true);
				imgDenuncia.setCaminho("http://rcisistemas.minivps.info:8080/conexaocidada/ImagemDenuncia/" + file_imagem.getName());
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
			String encodedImage = Utils.getImageBase64(imgDenuncia.getCaminho());
	        auxJson.addProperty("imgBase64", encodedImage);
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
		jsonDenuncia.addProperty("idDenuncia", denuncia.getIdDenuncia());
		return jsonDenuncia;
	}

	@POST
	@Path("denuncia_rapida")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public	Response addDenunciaRapida(String jsonRecebido)	{
		try{
			//Converte String JSON para objeto Java
			JSONObject dados_array_json = new JSONObject(jsonRecebido);
	
			String latitude = dados_array_json.getString("latitude");
			String longitude = dados_array_json.getString("longitude");
			String tipoDenuncia = dados_array_json.getString("tipoDenuncia");

			if(latitude == null || longitude == null || tipoDenuncia == null || latitude.equalsIgnoreCase("") || longitude.equalsIgnoreCase("")){
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
			denuncia.setDataAconteceu(new Date());
			denuncia.setAtivo(true);
			denuncia.setLocalDenuncia(local);
			denuncia.setTipoDenuncia(tipo);
			denuncia.setObservacao("Denuncia Rapida.");
			denuncia.setUsuario(user);			
			denuncia = (Denuncia) service.gravar(denuncia);
			System.out.println("gravou denuncia...");
			
			String json = gerarJson(denuncia);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}catch(Exception e){
			e.printStackTrace();
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("denuncia_site")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public	Response addDenunciaSite(String jsonRecebido)	{
		try{
			//Converte String JSON para objeto Java
			JSONObject dados_array_json = new JSONObject(jsonRecebido);
			JSONObject json_dado = dados_array_json.getJSONObject("denuncia");
			String latitude = null;
			String longitude = null;
			String tipoDenuncia = null;
			String observacao = null;
			JSONArray arrayImagem = null;
			String dataAdicionada_string = null;
			int idUsuario = 0;	
			if( ! json_dado.isNull("latitude")){
				latitude = String.valueOf(json_dado.getDouble("latitude"));
			}
			if( ! json_dado.isNull("longitude")){
				longitude = String.valueOf(json_dado.getDouble("longitude"));
			}
			if( ! json_dado.isNull("tipoDenuncia")){
				tipoDenuncia = json_dado.getString("tipoDenuncia");
			}
			if( ! json_dado.isNull("observacao")){
				observacao = json_dado.getString("observacao");
			}
			if( ! json_dado.isNull("dataAdicionada")){
				dataAdicionada_string = json_dado.getString("dataAdicionada");
			}
			if( ! json_dado.isNull("arrayImagemm")){
				arrayImagem = json_dado.getJSONArray("arrayImagemm");
			}
			if( ! json_dado.isNull("idUsuario")){
				idUsuario = json_dado.getInt("idUsuario");
			}
			
			if(latitude == null || longitude == null || tipoDenuncia == null || latitude.equalsIgnoreCase("") 
					|| longitude.equalsIgnoreCase("") || idUsuario == 0 ){
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
			Usuario user = (Usuario) service.consultarObjetoId(idUsuario);
			
			service = new DenunciaServiceImpl();
			Denuncia denuncia = new Denuncia();
			denuncia.setDataAconteceu(new Date());
			denuncia.setAtivo(true);
			denuncia.setLocalDenuncia(local);
			denuncia.setTipoDenuncia(tipo);
			denuncia.setObservacao(observacao);
			denuncia.setUsuario(user);			
			denuncia = (Denuncia) service.gravar(denuncia);
			System.out.println("gravou denuncia...");
			if(arrayImagem != null){
				for(int i = 0; i < arrayImagem.length(); i++){
					JSONObject json_imagem = arrayImagem.getJSONObject(i);					
					ImagemDenuncia imgDenuncia = new ImagemDenuncia();
					imgDenuncia.setAtivo(true);
					imgDenuncia.setCaminho(json_imagem.getString("image"));
					imgDenuncia.setDenuncia(denuncia);
					imgDenuncia.setDescricao("...fazer posteriomente...");
					service = new ImagemDenunciaServiceImpl();
					imgDenuncia = (ImagemDenuncia) service.gravar(imgDenuncia);
				}
			}
			String json = gerarJson(denuncia);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}catch(Exception e){
			e.printStackTrace();
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("adddenuncia_app_42")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public	Response addDenunciaAPP(String jsonRecebido)	{
		try{
			//Converte String JSON para objeto Java
			JSONObject dados_array_json = new JSONObject(jsonRecebido);
			String latitude = null; String longitude = null; String tipoDenuncia = null;
			String observacao = null; int idUsuario = 0;
			JSONArray array = new JSONArray();
			if( ! dados_array_json.isNull("latitude")){
				latitude = dados_array_json.getString("latitude");
			}
			if( ! dados_array_json.isNull("longitude")){
				longitude = dados_array_json.getString("longitude");
			}
			if( ! dados_array_json.isNull("tipoDenuncia")){
				tipoDenuncia = dados_array_json.getString("tipoDenuncia");
			}
			if( ! dados_array_json.isNull("observacao")){
				observacao = dados_array_json.getString("observacao");
			}
			if( ! dados_array_json.isNull("idUsuario")){
				idUsuario = dados_array_json.getInt("idUsuario");
			}
			if( ! dados_array_json.isNull("arrayImagem")){
				array = dados_array_json.getJSONArray("arrayImagem");
			}	
			
			if(latitude == null || longitude == null || latitude.equalsIgnoreCase("") || longitude.equalsIgnoreCase("")){
				return Response.ok("", MediaType.APPLICATION_JSON).build();
			}
			
			LocalDenuncia local = new LocalDenuncia(latitude, longitude);
			service = new LocalDenunciaServiceImpl();
			local = (LocalDenuncia) service.gravar(local);

			TipoDenunciaService serviceTipoDenucia = new TipoDenunciaServiceImpl();
			TipoDenuncia tipo = serviceTipoDenucia.consultarDescricao(tipoDenuncia);
			
			if(tipo == null || tipo.getIdTipoDenuncia()  == null || tipo.getIdTipoDenuncia() == 0 ) {
				tipo.setIdTipoDenuncia(1);
			}
			
			if(observacao != null && observacao.length() > 300 ){
				observacao = observacao.substring(0, 298);
			}
			
			service = new UsuarioServiceImpl();
			Usuario user ;
			try{
				if(idUsuario != 0){
					user = (Usuario) service.consultarObjetoId(idUsuario);
				}else{
					user = (Usuario) service.consultarObjetoId(1);
				}
			} catch(Exception e){
				user = (Usuario) service.consultarObjetoId(1);
			}
			
			if(user == null || user.getIdUsuario() == null || user.getIdUsuario() == 0 ){
				user = (Usuario) service.consultarObjetoId(1);
			}
			
			service = new DenunciaServiceImpl();
			Denuncia denuncia = new Denuncia();
			
			
			if(! dados_array_json.isNull("dataAconteceu") ){
				denuncia.setDataAconteceu(new Date( dados_array_json.getLong("dataAconteceu")));
			}else{
				denuncia.setDataAconteceu(new Date());
			}
			denuncia.setAtivo(true);
			denuncia.setLocalDenuncia(local);
			denuncia.setTipoDenuncia(tipo);
			denuncia.setObservacao(observacao);
			denuncia.setUsuario(user);			
			denuncia = (Denuncia) service.gravar(denuncia);
			System.out.println("gravou denuncia...");
			for(int cont = 0; cont < array.length() ; cont++){
				String imagem = null; String formatoImage = null;
				JSONObject jsonObject = array.getJSONObject(cont);
				if(!jsonObject.isNull("imagem")){
					imagem = jsonObject.getString("imagem");
				}
				if(!jsonObject.isNull("formatoImage")){
					formatoImage = jsonObject.getString("formatoImage");
				}
				if(imagem != null && formatoImage != null){
					byte[] arquivo = null;
					arquivo = Base64.decodeBase64(imagem.toString());
					String caminhoImagem = Utils.SalvarImagm(arquivo, formatoImage);
				
					File file_imagem = new File(caminhoImagem);
					
					ImagemDenuncia imgDenuncia = new ImagemDenuncia();
					imgDenuncia.setAtivo(true);
					imgDenuncia.setCaminho("http://rcisistemas.minivps.info:8080/conexaocidada/ImagemDenuncia/" + file_imagem.getName());
					imgDenuncia.setDenuncia(denuncia);
					imgDenuncia.setDescricao("...fazer posteriomente...");
					service = new ImagemDenunciaServiceImpl();
					imgDenuncia = (ImagemDenuncia) service.gravar(imgDenuncia);
				}
			}
			dados_array_json.put("idDenuncia", denuncia.getIdDenuncia());
			dados_array_json.put("dataAconteceu", denuncia.getDataAconteceu().getTime());
			if( ! dados_array_json.isNull("arrayImagem")){
				dados_array_json.remove("arrayImagem");
			}	
			//String json = gerarJson(denuncia);
			String json = dados_array_json.toString();
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.ok("", MediaType.APPLICATION_JSON).build();
		}
	}
}
