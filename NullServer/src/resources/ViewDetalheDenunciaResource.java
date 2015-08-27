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
import service.ImagemDenunciaService;
import service.ViewDetalheDenunciaService;
import service.impl.ImagemDenunciaServiceImpl;
import service.impl.ViewDetalheDenunciaServiceImpl;
import util.Utils;

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
				bairro = "Não registrado";
			}
			json.addProperty("bairro", bairro);
			json.addProperty("idDenuncia", v.getIdDenuncia());
			array_json.add(json);
		}
		return array_json;
	}
	
}
