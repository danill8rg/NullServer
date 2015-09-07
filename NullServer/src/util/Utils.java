package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;

import service.ImagemDenunciaService;
import service.impl.ImagemDenunciaServiceImpl;

public class Utils {
	
	public static String SalvarImagm(byte[] bytes, String formato) throws Exception {
		String file_name = null;
		ImagemDenunciaService serviceImagem =  new ImagemDenunciaServiceImpl();
		String nome = serviceImagem.proximoIdImagem();
		file_name = nome;
		byte[] imgBytes = bytes;
		File file;
	    try {
	    	
	    	file_name = "/var/tomcat7/webapps/NullPointer/ImagemDenuncia/" + nome + "." + formato;
	    	//file_name = "C:/Users/Notebook/git/NullPointer2/WebContent/ImagemDenuncia/" + nome + "." + formato;
	    	file = new File(file_name);
	    	    if (!file.exists()) {
	    	        file.createNewFile();
	    	    }
	    	    FileOutputStream fop = new FileOutputStream(file);
	    	    fop.write(imgBytes);
	    	    fop.flush();
	    	    fop.close();	        
	        
	        } catch (Exception e) {
	            throw new Exception(    "Erro ao converter os bytes recebidos para imagem");
	        }
	    return compactar(file.getAbsolutePath());
	}

	private static String compactar(String file) {
		try{
			BufferedImage originalImage = ImageIO.read(new File(file));
		    int width = 1280;
		    int height = 720;
		    BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		    resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
		    //ImageIO.write(resizedImage, "jpg", new File("/var/tomcat7/webapps/NullPointer/ImagemDenuncia/" + nome));	        
		    ImageIO.write(resizedImage, "jpg", new File(file));
		}catch(Exception e){
			System.out.println("Erro ao converter os bytes recebidos para imagem");
	        e.getStackTrace();
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
	        
	        BufferedImage originalImage = ImageIO.read(new File("/var/tomcat7/webapps/NullPointer/ImagemDenuncia/" + nome));
	        
	        //BufferedImage originalImage = ImageIO.read(new File("C:/Users/danillo/git/NullServer/NullServer/WebContent/image/temp/" + nome));
	        int width = 1280;
	        int height = 720;
	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
	        ImageIO.write(resizedImage, "jpg", new File("/var/tomcat7/webapps/NullPointer/ImagemDenuncia/" + nome));	        
	        //ImageIO.write(resizedImage, "jpg", new File("C:/Users/danillo/git/NullServer/NullServer/WebContent/image/temp/" + nome));	        
	        return "http://rcisistemas.minivps.info:8080/NullServer/image/temp/" + nome;
	        //return "http://localhost:8080/NullServer/image/temp/" + nome;
	            
	    } catch (Exception e) {
	           System.out.println("Erro ao converter os bytes recebidos para imagem");
	           e.getStackTrace();
	        }
		return null;
	}

}