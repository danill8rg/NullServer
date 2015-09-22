package resources;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.LocalDenuncia;
import model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import service.UsuarioService;
import service.impl.LocalDenunciaServiceImpl;
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
@Path("/localDenuncia")
public class LocalDenunciaResource extends SuperResource{
	
	@GET
	@Path("{lat}/{log}")
	public Response getUsuario(@PathParam("lat") String lat, @PathParam("log") String log)	{
		setService(new LocalDenunciaServiceImpl());
		try{
			if(lat == null || log == null){
				return Response.ok("").build();
			}
			Double teste_latitude = Double.valueOf(lat); 
			Double teste_longitude = Double.valueOf(log); 
			
			if(teste_latitude == null || teste_longitude == null ){
				return Response.ok("").build();
			}
			
			String jsonString = null;
			URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + log + "&sensor=true");
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();		
			conexao.connect();
		        int status = conexao.getResponseCode();

		        switch (status) {
		        	case 200:
		            case 201:{
		                BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream(),"UTF-8"));
		                StringBuilder sb = new StringBuilder();
		                String line;
		                while ((line = br.readLine()) != null) {
		                    sb.append(line);
		                }
		                br.close();
		                jsonString = sb.toString();
		            }break;
		            default : {
		            	System.out.println("Localização não encontrada...");
		            }break;
		        }
		    if(jsonString == null) {
		    	return Response.ok("").build();
		    }
		    
		    String nomeBairro = null;
			String nomeCidade = null;
			String nomeEstado = null;
			String nomePais = null;
			String cep = null;
			String rua = null;
			
		    JSONObject json = new JSONObject(jsonString);
		    JSONArray array = json.getJSONArray("results");
		    JSONObject jsonteste = null;
		    for (int cont = 0; cont < array.length(); cont++){
		   
		    	  JSONObject arrayAddress = array.optJSONObject(cont);
		    	 if(arrayAddress != null){		    					    	 
			    		 JSONArray jsonArrayAux = arrayAddress.getJSONArray("address_components");
			    		for(int conta = 0 ; conta < jsonArrayAux.length() ; conta++ ){
			    			JSONObject jsonAux = jsonArrayAux.getJSONObject(conta);
			    			String long_name = null;
				    		 if(! jsonAux.isNull("long_name")){
				    			 long_name = jsonAux.getString("long_name");
				    		 }
				    		 String short_name = null;
				    		 if(! jsonAux.isNull("short_name")){
				    			 short_name = jsonAux.getString("short_name");
				    		 }
				    		 JSONArray types = null;
				    		 if(! jsonAux.isNull("types")){
				    			 types = jsonAux.getJSONArray("types");			    		 
				    		 }
				    		
				    		 String valor1 = null;
				    		 String valor2 = null;
				    		 
				    		 if(types != null && types.length() == 2 ){
				    			 valor1 = types.getString(0);
				    			 valor2 = types.getString(1);
				    		 }else{
				    			 valor1 = types.getString(0);
				    		 }
				    		 if(types.length() == 2 && valor1.equalsIgnoreCase("neighborhood") && valor2.equalsIgnoreCase("political")){
				    			 nomeBairro = long_name; 
				    		 }
				    		 if(types.length() == 2 && valor1.equalsIgnoreCase("administrative_area_level_2") && valor2.equalsIgnoreCase("political")){
				    			 nomeCidade = long_name; 
				    		 }
				    		 if(types.length() == 2 && valor1.equalsIgnoreCase("administrative_area_level_1") && valor2.equalsIgnoreCase("political")){
				    			 nomeEstado = short_name; 
				    		 }
				    		 if(types.length() == 2 && valor1.equalsIgnoreCase("country") && valor2.equalsIgnoreCase("political")){
				    			 nomePais = long_name; 
				    		 }
				    		 if(types.length() == 1 && valor1.equalsIgnoreCase("postal_code")){
				    			 cep = long_name; 
				    		 }
				    		 if(types.length() == 1 && valor1.equalsIgnoreCase("route")){
				    			 rua = long_name; 
				    		 }
			    		}
		    	 }
		    }

		    JsonObject json_resultado = new JsonObject();
		    json_resultado.addProperty("rua", rua);
		    json_resultado.addProperty("cep", cep);
		    json_resultado.addProperty("nomePais", nomePais);
		    json_resultado.addProperty("nomeEstado", nomeEstado);
		    json_resultado.addProperty("nomeCidade", nomeCidade);
		    json_resultado.addProperty("nomeBairro", nomeBairro);
			return Response.ok(json_resultado.toString()).build();	
		}catch(Exception e){
			return Response.ok(" ").build();
		}
		
	}

}
