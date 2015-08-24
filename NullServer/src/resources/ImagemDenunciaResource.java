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
public Response uploadFile(File image) throws IOException
{
    //Get API input data
    //Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
     
    //Get file name
    //String fileName = uploadForm.get("fileName").get(0).getBodyAsString();
	String fileNameAux = image.getName() + ".jpg";
	
    //Get file data to save
    //List<InputPart> inputParts = uploadForm.get("attachment");

    //for (InputPart inputPart : inputParts){
        try
        {
            //Use this header for extra processing if required
//            @SuppressWarnings("unused")
//            MultivaluedMap<String, String> header = inputPart.getHeaders();
//             
            // convert the uploaded file to inputstream
            //InputStream inputStream = inputPart.getBody(InputStream.class, null);
        	
//        	BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);    
//        	Graphics bg = bi.getGraphics();  
//        	bg.drawImage(image, 0, 0, null);    
//        	bg.dispose();   
//        	  
//        	ByteArrayOutputStream baos = new ByteArrayOutputStream();  
//        	ImageIO.write(bi, "jpg", baos );  
//        	baos.flush();  
        	  
//        	byte[] imageInByte = baos.toByteArray();  
//       	baos.close();  
        	
//        	byte[] imageInByte = image.
//        	  
        	FileInputStream fis = new FileInputStream(image.getAbsolutePath());
        	InputStream is = fis;

        	InputStream inputStream = is;
            byte[] bytes = IOUtils.toByteArray(inputStream);
            // constructs upload file path
            fileNameAux = "C:/Users/Notebook/git/NullPointer2/WebContent/ImagemDenuncia/temp/" + fileNameAux;
            writeFile(bytes, fileNameAux);
            System.out.println("Success !!!!!");
        	fis.close();  
        	
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    //}
    return Response.status(200)
            .entity("Uploaded file name : "+ fileNameAux).build();
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
