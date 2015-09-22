package resources;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Usuario;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import service.ImagemDenunciaService;
import service.UsuarioService;
import service.impl.ImagemDenunciaServiceImpl;
import service.impl.UsuarioServiceImpl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 
 * @author Danillo... Classe responsável por fornecer os modulos de serviços
 *         disponiveis na webService para a classe Usuario... Serao
 *         disponibilizados os serviços de get deverá retornar sem a senha...
 *         post adicionar ususario put atualização do usuario com senha antiga e
 *         nova...
 *
 */

// Sets the path to base URL + /usuario caminho para a URL...
@Path("/img")
public class ImagemDenunciaResource extends SuperResource {

@GET
@Path("{id}")
public Response getUsuario(@PathParam("id") int id) {
	setService(new UsuarioServiceImpl());
	try {
		Usuario user = (Usuario) getService().consultarObjetoId(id);
		Gson gson = new Gson();
		String json = gson.toJson(user);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	} catch (Exception e) {
		return Response.serverError().entity(e.getMessage()).build();
	}

}

@POST
@Path("/fileupload")
@Consumes("*/*")
@Produces(MediaType.APPLICATION_JSON)
public Response uploadFile(File image) throws IOException {
	String fileNameAux = image.getName() + ".jpg";
	String file_path;
	try {
		FileInputStream fis = new FileInputStream(image.getAbsolutePath());
		InputStream is = fis;

		InputStream inputStream = is;
		byte[] bytes = IOUtils.toByteArray(inputStream);
		// constructs upload file path
		//file_path = "C:/Users/Notebook/git/NullPointer2/WebContent/ImagemDenuncia/temp/" + fileNameAux;
		file_path = "/var/tomcat7/webapps/conexaocidada/ImagemDenuncia/temp/" + fileNameAux;
		writeFile(bytes, file_path);
		System.out.println("Success !!!!!");
		fis.close();
		JsonObject json = new  JsonObject();
		//json.addProperty("caminho", "http://localhost:8080/NullPointer/ImagemDenuncia/temp/" + fileNameAux);
		json.addProperty("caminho", "http://rcisistemas.minivps.info:8080/conexaocidada/ImagemDenuncia/temp/" + fileNameAux);
		return Response.ok(json.toString()).build();	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return Response.status(200).entity("").build();
}

//Utility method
private void writeFile(byte[] content, String filename) throws IOException
{
    File file = new File(filename);
    if (!file.exists()) {
        file.createNewFile();
    }
    FileOutputStream fop = new FileOutputStream(file);
    fop.write(content);
    fop.flush();
    fop.close();
    
    BufferedImage originalImage = ImageIO.read(new File(filename));
    int width = 1280;
    int height = 720;
    BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
    //ImageIO.write(resizedImage, "jpg", new File("/var/tomcat7/webapps/NullPointer/ImagemDenuncia/" + nome));	        
    ImageIO.write(resizedImage, "jpg", new File(filename));	
}


@GET
@Path("validaremail/{email}")
public Response getValidarEmail(@PathParam("email") String email) {
	UsuarioService userService = new UsuarioServiceImpl();
	try {
		boolean result = userService.validarEmail(email);
		JSONObject json = new JSONObject();
		json.put("resultado", result);
		System.out.println(json.toString());
		return Response.ok(json.toString()).build();
	} catch (Exception e) {
		return Response.serverError().entity(e.getMessage()).build();
	}

}

@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getUsuarioList() {
	ImagemDenunciaService service = new ImagemDenunciaServiceImpl();
	try {
		String resposta = service.proximoIdImagem();
		// ArrayList<Usuario> listUsuarios = service.consultarTodos(user);
		JsonObject json = new JsonObject();
		json.addProperty("id", resposta);
		return Response.ok(json.toString()).build();
	} catch (Exception e) {
		return Response.serverError().entity(e.getMessage()).build();
	}
}

@POST
@Path("addUser")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response addUsuario(String jsonRecebido) {
	try {
		service = new UsuarioServiceImpl();
		// Converte String JSON para objeto Java
		JSONObject dados_array_json = new JSONObject(jsonRecebido);

		JSONObject dados_array = dados_array_json.getJSONObject("usuario");
		String nome = dados_array.getString("nome");
		String email = dados_array.getString("email");
		String senha = dados_array.getString("senha");

		UsuarioService userService = new UsuarioServiceImpl();
		if (!userService.validarEmail(email)) {
			return Response.serverError().entity("E-mail já existe!").build();
		}

		if (nome == null || email == null || senha == null
				|| nome.equalsIgnoreCase("") || email.equalsIgnoreCase("")
				|| senha.equalsIgnoreCase("")) {
			return Response.serverError()
					.entity("Campo preenchido inadequadamente !").build();
		}
		Usuario user = new Usuario(nome, senha, email);
		// user.setIdUsuario(0);
		user = (Usuario) service.gravar(user);

		Gson gson = new Gson();
		String json = gson.toJson(user);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	} catch (Exception e) {
		e.printStackTrace();
		return Response.serverError().entity(e.getMessage()).build();
	}
}

}
