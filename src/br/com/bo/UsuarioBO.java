package br.com.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;
import br.com.util.PersistenceJPA;

public class UsuarioBO {
	private UsuarioDAO usuarioDAO;

	private UsuarioDAO getDAO(){
		if(usuarioDAO == null){
			usuarioDAO = new UsuarioDAO(Usuario.class);
		}
		return usuarioDAO;
	}
	
	private void fecharConexao() {
		try {
			getDAO().fecharConexao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void validarUsuario(Usuario usuario, boolean isAlteracao) throws Exception{
		String mensagem  = "";
		if(usuario == null){
			mensagem += "Usuário está nulo";
		}
		if(isAlteracao){
			if(usuario.getId() == 0){
				mensagem = "Usuário não informado!";
			}
		}
		if(usuario.getNome() == null || usuario.getEmail().isEmpty()){
			mensagem += "Nome do usuário não informado!";
		}
		if(usuario.getLogin() == null || usuario.getLogin().isEmpty()){
			mensagem += "Login do usuário não informado!";
		}
		if(usuario.getEmail() == null || usuario.getEmail().isEmpty()){
			mensagem += "Email do usuário não informado!";
		}
		if(usuario.getSenha() == null || usuario.getSenha().isEmpty()){
			mensagem += "Senha do usuário não informada!";
		}
		if(!mensagem.isEmpty()){
			throw new Exception(mensagem);
		}
	}
	
	public List<Usuario> listar(){
		List<Usuario> lista = null; 
		try {
			lista = getDAO().listar();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fecharConexao();
		}
		return lista;
	}

	public Usuario buscar(long id){
		Usuario usuario = null;
		if(id != 0){
			try {
				usuario = getDAO().buscar(id);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				fecharConexao();
			}
		}
		return usuario;
	}
	
	public String excluir(long id){
		String mensagem = "";
		Usuario usuario = null;
		if(id == 0){
			mensagem = "Usuário não informado!";
		}else{
			try {
				usuario = buscar(id);
				if(usuario == null){
					mensagem = "Usuário não cadastrado!";
					
				}else{
					getDAO().excluir(id);
					mensagem = usuario.getNome() + " excluido com sucesso!";
				}
			} catch (Exception e) {
				mensagem = e.getMessage();
			}finally{
				fecharConexao();
			}
		}
		return mensagem;
	}
	
	public String inserir(Usuario usuario){
		String mensagem = "";
		try {
			validarUsuario(usuario, false);
			getDAO().inserir(usuario);
			mensagem = usuario.getNome() + " inserido com sucesso!";
		} catch (Exception e) {
			mensagem = e.getMessage();
		}finally{
			fecharConexao();
		}
		return mensagem;
	}

	public String alterar(Usuario usuario){
		String mensagem = "";
		try {
			
			validarUsuario(usuario, true);
			getDAO().alterar(usuario);
			mensagem = usuario.getNome() + " alterado com sucesso!";
		} catch (Exception e) {
			mensagem = e.getMessage();
		}finally{
			fecharConexao();
		}
		return mensagem;
	}
	
}
