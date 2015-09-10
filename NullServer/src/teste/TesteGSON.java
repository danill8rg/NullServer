package teste;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TesteGSON {

	public static void main(String[] args) throws IOException {
		System.out.println("testando...");
		
		String file = "C:/Users/Notebook/git/NullPointer2/WebContent/ImagemDenuncia/" + "3.png" ;
		 BufferedImage originalImage = ImageIO.read(new File(file));
	        int width = 1280;
	        int height = 1280;
	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
	        ImageIO.write(resizedImage, "jpg", new File(file));
	        
	        BufferedImage originalImg=ImageIO.read(new File(file));
	        

	        BufferedImage bim=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
	        

	        Graphics2D g2=bim.createGraphics();
	        

	        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	        qualityHints.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
	        g2.setRenderingHints(qualityHints);
	        
	        g2.setClip(new RoundRectangle2D.Double(0,0,width,height,width,height));
	        
	        g2.drawImage(originalImg,0,0,null);
	        
	        g2.dispose();
	        
	        // Write to a new image file
	        ImageIO.write(bim,"PNG",new File(file));
	        System.out.println("acabou....");
//		
//		TipoUsuario tipo = new TipoUsuario(1, "Normal", true);
//		Gson gson = new Gson();
//		String json = gson.toJson(tipo);
//		
//		System.out.println("json = " + json);
//		
//		Usuario user = new Usuario(1,"email","senha","nome", true,tipo);
//		Usuario user2 = new Usuario(1,"email","senha","nome", true,tipo);
//		Usuario user3 = new Usuario(1,"email","senha","nome", true,tipo);
//		Usuario user4 = new Usuario(1,"email","senha","nome", true,tipo);
//		
//		UsuarioServiceImpl service = new UsuarioServiceImpl();
//		
//		ArrayList<Usuario> lista = service.consultarTodos(user);
//		lista.add(user);
//		lista.add(user2);
//		lista.add(user3);
//		lista.add(user4);
//		
//		
//		
//		String user_json = gson.toJson(lista);
//		
//		System.out.println("user_json = " + user_json);
		
//	 String teste ="111 teste";
//	 System.out.println(teste);
//	 teste =teste.substring(0, teste.indexOf(" "));
//	 System.out.println("'"+teste+"'");
//		LocalDenunciaServiceImpl teste = new LocalDenunciaServiceImpl();
//		
//		LocalDenuncia local = new LocalDenuncia();
//		local.setLatitude("-16.306306306306308");
//		local.setLongitude("-48.94168176327416");
//		
//		teste.gravar(local);
		
	}

}
