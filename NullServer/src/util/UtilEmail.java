package util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class UtilEmail {
	
	public static void mandaEmail(String para, String mensagem) {
	      SimpleEmail email = new SimpleEmail();  
	      try {  
	      email.setDebug(true);  
	      email.setHostName("smtp.gmail.com");  
	      email.setAuthentication("danill8rg@gmail.com","da424232");  
	      email.setSSL(true);  
	      email.addTo(para); //pode ser qualquer email  
	      email.setFrom("danill8rg@gmail.com"); //será passado o email que você fará a autenticação 
	      email.setSubject("NullPointer lembrar senha");  
	      email.setMsg(mensagem);  
	      email.send();  
	      } catch (EmailException e) {  
	    	  System.out.println(e.getMessage());  
	      }   
	}
	
}
