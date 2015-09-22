package resources;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import model.ImagemDenuncia;
import model.ViewDetalheDenuncia;
import model.ViewMensagem;
import service.ImagemDenunciaService;
import service.ViewDetalheDenunciaService;
import service.ViewMensagemService;
import service.impl.ImagemDenunciaServiceImpl;
import service.impl.ViewDetalheDenunciaServiceImpl;
import service.impl.ViewMensagemServiceImpl;
import util.Utils;

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
@Path("/viewDetalheDenuncia")
public class ViewDetalheDenunciaResource extends SuperResource{
	
	@GET
	@Path("{id}")
	public Response getView(@PathParam("id") int id)	{
		setService(new ViewDetalheDenunciaServiceImpl());
		try{
			List<ImagemDenuncia> listImg = new ArrayList<ImagemDenuncia>();
			ImagemDenunciaService servImagem = new ImagemDenunciaServiceImpl();
			listImg = servImagem.consultarPorDenuncia(id);
			ViewDetalheDenuncia view = (ViewDetalheDenuncia) getService().consultarObjetoId(id);
			JsonObject jsonobject = gerarJsonDenuncia(view, listImg);
			System.out.println(jsonobject.toString());
			return Response.ok(jsonobject.toString()).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("site/{id}")
	public Response getViewSite(@PathParam("id") int id)	{
		setService(new ViewDetalheDenunciaServiceImpl());
		try{
			List<ImagemDenuncia> listImg = new ArrayList<ImagemDenuncia>();
			ImagemDenunciaService servImagem = new ImagemDenunciaServiceImpl();
			listImg = servImagem.consultarPorDenuncia(id);
			ViewDetalheDenuncia view = (ViewDetalheDenuncia) getService().consultarObjetoId(id);
			JsonObject jsonobject = gerarJsonDenuncia(view, listImg);
			System.out.println(jsonobject.toString());
			return Response.ok(jsonobject.toString()).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
		
	}

	private JsonObject gerarJsonDenuncia(ViewDetalheDenuncia view, List<ImagemDenuncia> listImg) {
		JsonObject dados_array_json = new JsonObject();
		dados_array_json.addProperty("observacao", view.getObservacao());
		
		SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("dd/MM/yyyy HH:mm:ss");
        
		
		dados_array_json.addProperty("dataDenuncia", sdf1.format(view.getDataAconteceu()));
		dados_array_json.addProperty("tipoDenuncia", view.getTipoDenuncia());
		dados_array_json.addProperty("idDenuncia", view.getIdDenunciante());
		dados_array_json.addProperty("idDenuncia", view.getIdDenuncia());
		
		dados_array_json.addProperty("rua", view.getRua());
		dados_array_json.addProperty("bairro", view.getBairro());
		dados_array_json.addProperty("cidade", view.getCidade());
		dados_array_json.addProperty("estado", view.getEstado());
		dados_array_json.addProperty("pais", view.getPais());
		dados_array_json.addProperty("nomeDenunciante", view.getNomeDenunciante());
		dados_array_json.addProperty("cep", view.getCep());
		dados_array_json.addProperty("latitude", view.getLatitude());
		dados_array_json.addProperty("longitude", view.getLongitude());

		JsonArray arrayImagem = new JsonArray();
		JsonObject auxJson;
		for(ImagemDenuncia imgDenuncia : listImg){
			auxJson = new JsonObject();
			auxJson.addProperty("caminho", imgDenuncia.getCaminho());
			String encodedImage = Utils.getImageBase64(imgDenuncia.getCaminho());
	        auxJson.addProperty("imgBase64", encodedImage);
			arrayImagem.add(auxJson);
		}
		dados_array_json.add("listImagem", arrayImagem);
		return dados_array_json;
	}
	
	@GET
	@Path("myDenuncia/{id}")
	public Response getViewMinhasDenuncias(@PathParam("id") int id)	{
		if (id <= 0)  Response.ok("").build();	
		try{
			ViewDetalheDenunciaService serviceView = new ViewDetalheDenunciaServiceImpl();

			List<ViewDetalheDenuncia> listDenuncias = new ArrayList<ViewDetalheDenuncia>(serviceView.consultarTodos(new ViewDetalheDenuncia()));
			
			JsonArray jsonobject = gerarJsonDenunciaMyDenuncias(listDenuncias);
			System.out.println(jsonobject.toString());
			return Response.ok(jsonobject.toString()).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}

	private JsonArray gerarJsonDenunciaMyDenuncias(List<ViewDetalheDenuncia> listDenuncias) {
		JsonArray array_json = new JsonArray();
		ImagemDenunciaService sericeView = new ImagemDenunciaServiceImpl();
		SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("dd/MM/yyyy HH:mm:ss");
		for(ViewDetalheDenuncia v : listDenuncias){
			JsonObject json = new JsonObject();
			String caminhoImage = sericeView.caminhoPrimeiraImagem(v.getIdDenuncia());
			json.addProperty("image", caminhoImage);
			json.addProperty("tipoDenuncia", v.getTipoDenuncia());
			json.addProperty("tipoDenuncia", v.getTipoDenuncia());
			json.addProperty("dataDenuncia", sdf1.format(v.getDataAconteceu()));
			String bairro = v.getBairro();
			if (bairro == null){
				bairro = "N�o registrado";
			}
			json.addProperty("bairro", bairro);
			json.addProperty("idDenuncia", v.getIdDenuncia());
			array_json.add(json);
		}
		return array_json;
	}
	
	@GET
	@Path("myDenunciaApp/{id}")
	public Response getViewMinhasDenunciasApp(@PathParam("id") int id)	{
		if (id <= 0)  Response.ok("").build();	
		try{
			ViewDetalheDenunciaService serviceView = new ViewDetalheDenunciaServiceImpl();

			List<ViewDetalheDenuncia> listDenuncias = new ArrayList<ViewDetalheDenuncia>(serviceView.consultarPorUsuario(id));
			
			JsonArray jsonobject = gerarJsonDenunciaMyDenuncias(listDenuncias);
			System.out.println(jsonobject.toString());
			return Response.ok(jsonobject.toString()).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("detalhe_denuncia_app/{id}")
	public Response getViewDetalheAPP(@PathParam("id") int id)	{
		setService(new ViewDetalheDenunciaServiceImpl());
		try{
			List<ImagemDenuncia> listImg = new ArrayList<ImagemDenuncia>();
			ImagemDenunciaService servImagem = new ImagemDenunciaServiceImpl();
			listImg = servImagem.consultarPorDenuncia(id);
			
			List<ViewMensagem> listMensagem = new ArrayList<ViewMensagem>();
			ViewMensagemService servMensagem = new ViewMensagemServiceImpl();
			listMensagem = servMensagem.consultarPorDenuncia(id);
			
			ViewDetalheDenuncia view = (ViewDetalheDenuncia) getService().consultarObjetoId(id);
			
			JsonArray jsonobject = gerarJsonDenunciaAndroid(view, listImg, listMensagem);
			System.out.println(jsonobject.toString());
			return Response.ok(jsonobject.toString()).build();	
		}catch(Exception e){
			 return Response.serverError().entity(e.getMessage()).build();
		}
		
	}

	private JsonArray gerarJsonDenunciaAndroid(ViewDetalheDenuncia view,
			List<ImagemDenuncia> listImg, List<ViewMensagem> listMensagem) {
		JsonArray array = new JsonArray();
		JsonObject json = new JsonObject();
		SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("dd/MM/yyyy HH:mm:ss");
		try{
			json.addProperty("tipoDenuncia", view.getTipoDenuncia());
			json.addProperty("dataDenuncia", sdf1.format(view.getDataAconteceu()));	
			json.addProperty("idDenunciante", view.getIdDenunciante());
			json.addProperty("idDenuncia", view.getIdDenuncia());
			json.addProperty("rua", view.getRua());
			json.addProperty("bairro", view.getBairro());
			json.addProperty("cidade", view.getCidade());
			json.addProperty("estado", view.getEstado());
			json.addProperty("pais", view.getPais());
			json.addProperty("nomeDenunciante", view.getNomeDenunciante());
			json.addProperty("cep", view.getCep());
			json.addProperty("latitude", view.getLatitude());
			json.addProperty("longitude", view.getLongitude());
			json.addProperty("observacao", view.getObservacao());
			
			JsonArray arrayImagem = new JsonArray();
			for(ImagemDenuncia image : listImg){
				JsonObject json_image = new JsonObject();
				json_image.addProperty("caminho", image.getCaminho());
				arrayImagem.add(json_image);
			}
			
			JsonArray arrayMensagem = new JsonArray();
			for(ViewMensagem mensagem : listMensagem){
				JsonObject json_mensagem = new JsonObject();
				json_mensagem.addProperty("caminhoImagem", mensagem.getCaminhoImagem());
				json_mensagem.addProperty("nomeUsuario", mensagem.getNomeUsuario());
				json_mensagem.addProperty("texto", mensagem.getTexto());
				json_mensagem.addProperty("idDenuncia", mensagem.getIdDenuncia());
				json_mensagem.addProperty("idMensagem", mensagem.getIdMensagem());
				json_mensagem.addProperty("dataAdicionado", mensagem.getDataAdicionado());
				arrayMensagem.add(json_mensagem);
			}
			
			json.add("arrayMensagem", arrayMensagem);
			json.add("arrayImagem", arrayImagem);
		}catch(Exception e){
			e.printStackTrace();
		}
		array.add(json);
		return array;
	}
	
}
