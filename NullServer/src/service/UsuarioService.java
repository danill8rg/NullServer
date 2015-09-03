package service;

import model.Usuario;

public interface UsuarioService extends SuperService<Usuario, Integer> {

	public Usuario logar(Usuario usuario) throws Exception;

	public boolean validarEmail(String email) throws Exception;

	public boolean relebrarEmail(String email);
}
