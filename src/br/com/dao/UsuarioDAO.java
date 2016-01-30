package br.com.dao;

import java.util.List;

import br.com.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {

	public UsuarioDAO(Class classe) {
		super(classe);
	}
	public static void main(String[] args) {
		UsuarioDAO usu = new UsuarioDAO(Usuario.class);
		try {
			List<Usuario> lista = usu.listar();
			usu.fecharConexao();
			System.out.println(lista.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
