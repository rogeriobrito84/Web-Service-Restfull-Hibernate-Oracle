package br.com.util;

import java.util.List;

import br.com.bo.UsuarioBO;
import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;

public class Testes {

	public static void main(String[] args) {
		UsuarioBO bo = new UsuarioBO();
		Usuario usu = new Usuario();
		usu.setNome("Clebson Lou");
		usu.setLogin("lou");
		usu.setEmail("lou@hotmail.com");
		usu.setSenha("1234");
		try {
//			dao.getEm();
//			dao.inserir(usu);
//			dao.fecharConexao();
//			usu.setId(3);
//			dao.alterar(usu);
			List<Usuario> lista = bo.listar();
			if(lista.size() > 0){
				for(Usuario u : lista){
					System.out.println("-----------------------");
					System.out.println("id: " + u.getId());
					System.out.println("nome: " + u.getNome());
					System.out.println("login: " + u.getLogin());
					System.out.println("email: " + u.getEmail());
					System.out.println("senha: " + u.getSenha());
				}				
			}else{
				System.out.println("Não existe registros!");
			}
//			Usuario u = dao.buscar(1);
//			if(u != null){
//				System.out.println("-----------------------");
//			System.out.println("id: " + u.getId());
//			System.out.println("nome: " + u.getNome());
//			System.out.println("login: " + u.getLogin());
//			System.out.println("email: " + u.getEmail());
//			System.out.println("senha: " + u.getSenha());
//			}
//			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistenceJPA.servicosJPA.name());
//			EntityManager em = emf.createEntityManager();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
