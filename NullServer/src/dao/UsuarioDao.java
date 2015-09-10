package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDao extends SuperDao<Usuario, Integer> {
	
	public List<Usuario> consultarNome(String valorConsulta);
	
	public List<Usuario> consultarEmail(String valorConsulta);
	
	public Usuario logar(String email, String Senha);

	public List<Usuario> consultarAtivos();

	public boolean validarEmail(String email);
	
	public Usuario consultarPorEmail(String email);

	public String proximoIdUsuario();
	
}
