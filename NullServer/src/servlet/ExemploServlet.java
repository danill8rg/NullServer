package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Denuncia;
import model.ImagemDenuncia;
import model.LocalDenuncia;
import model.TipoDenuncia;
import model.Usuario;

import org.apache.tomcat.util.codec.binary.Base64;

import service.DenunciaService;
import service.LocalDenunciaService;
import service.TipoDenunciaService;
import service.UsuarioService;
import service.impl.DenunciaServiceImpl;
import service.impl.ImagemDenunciaServiceImpl;
import service.impl.LocalDenunciaServiceImpl;
import service.impl.TipoDenunciaServiceImpl;
import service.impl.UsuarioServiceImpl;
import util.Utils;

public class ExemploServlet extends HttpServlet{
	
	public void init() {
	    try {
	      Class.forName("org.postgresql.Driver");
	      System.out.println("JDBC driver carregado");
	    }
	    catch (ClassNotFoundException e) {
	      System.out.println(e.toString());
	    }
	  }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processa(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processa(req, resp);
	}
	
	public void processa(HttpServletRequest request, HttpServletResponse response)throws IOException{
		String tipo = request.getParameter("tipo");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String idusuarioString = request.getParameter("idUsuario");
		String observacao = request.getParameter("observacao");
		
		PrintWriter out = response.getWriter();
		
		if(latitude == null || longitude == null || latitude.equalsIgnoreCase("") || longitude.equalsIgnoreCase("")){
			if(latitude == null && longitude == null || latitude.equalsIgnoreCase("") && longitude.equalsIgnoreCase("")){
				out.print("Erro ao inserir Denúncia! Não foi informado latitude e longitude.");
			}else{
				if(latitude == null || latitude.equalsIgnoreCase("")){
					out.print("Erro ao inserir Denúncia! Não foi informado latitude.");
				}else{
					if(longitude == null || longitude.equalsIgnoreCase("")){
						out.print("Erro ao inserir Denúncia! Não foi informado longitude.");
					}
				}
			}
		}else{
			try{
				Double verificar = Double.valueOf(latitude);
				verificar = Double.valueOf(longitude);
				LocalDenuncia local = new LocalDenuncia(latitude, longitude);
				LocalDenunciaService serviceLocal = new LocalDenunciaServiceImpl();
				local = (LocalDenuncia) serviceLocal.gravar(local);
				
				TipoDenunciaService serviceTipoDenucia = new TipoDenunciaServiceImpl();
				TipoDenuncia tipoDenuncia = null;
				if(tipo != null && !tipo.equalsIgnoreCase("")){
					tipoDenuncia = serviceTipoDenucia.consultarDescricao(tipo);
					
					if(tipoDenuncia == null || tipoDenuncia.getIdTipoDenuncia() == null || tipoDenuncia.getIdTipoDenuncia() == 0) {
						tipoDenuncia = serviceTipoDenucia.consultarObjetoId(1);
					}
				}else{
					tipoDenuncia = serviceTipoDenucia.consultarObjetoId(1);
				}
				
				UsuarioService userService = new UsuarioServiceImpl();
				Usuario user = null;
				
				if(idusuarioString != null && !idusuarioString.equalsIgnoreCase("")){
					user = (Usuario) userService.consultarObjetoId(Integer.parseInt(idusuarioString));
				}else{
					user = (Usuario) userService.consultarObjetoId(1);
				}
				
				if(observacao != null && observacao.length() > 300 ){
					observacao = observacao.substring(0, 298);
				}
				
				DenunciaService denunciaService = new DenunciaServiceImpl();
				Denuncia denuncia = new Denuncia();
				denuncia.setDataAconteceu(new Date());
				denuncia.setAtivo(true);
				denuncia.setLocalDenuncia(local);
				denuncia.setTipoDenuncia(tipoDenuncia);
				denuncia.setObservacao(observacao);
				denuncia.setUsuario(user);			
				denuncia = (Denuncia) denunciaService.gravar(denuncia);
				
				out.print("Sua Denúncia foi salva!");
			}catch(Exception e){
				e.printStackTrace();
				out.println("Ocorreu um Erro ao salvar Denúncia!");
				out.println("Por gentileza. Verifique o envio de dados.");
				out.println("Você deve obrigatoriamente enviar :");
				out.println("latitude: double em formato de String");
				out.println("longitude: double em formato de String");
				out.println("Dados adicionais");
				out.println("idUsuario: Inteiro com o número do id do Usuário");
				out.println("observacao: String de no máximo 300 caracteres contendo detalhes da denúncia.");
				out.println("tipo: String com o nome do tipo da denúncias tipos cadastrados :");
				out.println("'Nao foi Definido','Drogas','Assalto','Alcool','Assassinato','Acidente de Transito','Buraco na Rua','Desaparecimento de Pessoa','Luz de Poste Desligada','Roubo de Automovel','Roubo de Celular','Outro Tipo'");
				out.println("o valor do tipo deve ser exatamente do mesmo cadastrado.");
				out.println("Caso não seja encontrado será inserido como tipo:'Não foi Definido'.");
				out.println("Caso idUsuario não seja informado será salvo como usuario 1 :'anonimo'");
				out.println("");
				out.println("");
				out.println(e.getMessage());
				out.println(e.toString());
				
			}
		}
		out.close();
	}
	
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		String tipo = request.getParameter("tipo");
//		String latitude = request.getParameter("latitude");
//		String longitude = request.getParameter("longitude");
//
//
//			try {
//
//				Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Fapeg","postgres","123456");	        
//				String sql = "INSERT INTO ios (tipo,latitude,longitude) VALUES (?,?,?);";
//				PreparedStatement stm = con.prepareStatement(sql);
//				if (stm == null) {
//					System.out.println("\n\n\n STM = NULL \n\n\n");
//				} else {
//					stm.setString(1, tipo);
//					stm.setString(2, latitude);
//					stm.setString(3, longitude);
//
//				}
//
//				stm.executeUpdate();
//				stm.close();
//				con.close();
//				
//				PrintWriter out = response.getWriter();
//				out.print("TEXTO SERVLET");
//				out.close();
//
//			}
//			catch (SQLException e) {
//				System.out.println("Problema SQL.- " + e.toString());
//			}
//			catch (Exception e) {
//				System.out.println("Exceção geral !!!!");
//			}
//		
//
//	}
	
	

}
