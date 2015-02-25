package br.com.util;

import br.com.dao.DAO;
import br.com.model.Usuario;

public class Testes {

	public static void main(String[] args) {
		DAO<Usuario> usu = new DAO<>("servicosJPA", Usuario.class);
		try {
			usu.getEm();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
