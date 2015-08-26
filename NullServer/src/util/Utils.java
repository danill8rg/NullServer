package util;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.tomcat.util.codec.binary.Base64;

import service.ImagemDenunciaService;
import service.impl.ImagemDenunciaServiceImpl;

public class Utils {
	
	public static String SalvarImagm(byte[] bytes, String formato) throws Exception {
		String file = null;
		ImagemDenunciaService serviceImagem =  new ImagemDenunciaServiceImpl();
		String nome = serviceImagem.proximoIdImagem();
		file = nome;
		byte[] imgBytes = bytes;
	    try {
	    	file =  nome + "." + formato;
	    	//FileOutputStream fos = new FileOutputStream("C:/Users/danillo/git/NullPointer/WebContent/ImagemDenuncia/" + file);
	        FileOutputStream fos = new FileOutputStream("/var/tomcat7/webapps/NullPointer/ImagemDenuncia/" + file);
	        fos.write(imgBytes);
	        FileDescriptor fd = fos.getFD();
	        fos.flush();
	        fd.sync();
	        fos.close();
	        } catch (Exception e) {
	            throw new Exception(    "Erro ao converter os bytes recebidos para imagem");
	        }
	    return file;
	}

	public static String getImageBase64(String caminho) {
		try {
			URL url = new URL(caminho);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();		
			InputStream input = conexao.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			while (true) {
			    int r = input.read(buffer);
			    if (r == -1) break;
			    out.write(buffer, 0, r);
			}

			byte[] ret = out.toByteArray();
			String result = Base64.encodeBase64String(ret);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String SalvarImagemTemp(byte[] arquivo, String nome) {
		byte[] imgBytes = arquivo;
	    try {
	    	//FileOutputStream fos = new FileOutputStream("C:/Users/danillo/git/NullServer/NullServer/WebContent/image/temp/" + nome);
	        FileOutputStream fos = new FileOutputStream("/var/tomcat7/webapps/NullPointer/ImagemDenuncia/" + nome);
	        fos.write(imgBytes);
	        FileDescriptor fd = fos.getFD();
	        fos.flush();
	        fd.sync();
	        fos.close();
	        //return "http://localhost:8080/NullServer/image/temp/"+ nome;
	        return "http://rcisistemas.minivps.info:8080/NullServer/image/temp/" + nome;
	        } catch (Exception e) {
	           System.out.println("Erro ao converter os bytes recebidos para imagem");
	           e.getStackTrace();
	        }
		return null;
	}

}