package br.com.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO<T> {
	protected String persistenceName;
	protected EntityManagerFactory emf;
	protected EntityManager em;
	private final Class classe;
	
	public DAO(String persistenceName, Class classe) {
		this.persistenceName = persistenceName;
		this.classe = classe;
	}

	public EntityManager getEm() throws Exception{
		try {
			if(emf == null){
				emf = Persistence.createEntityManagerFactory(persistenceName);
			}
			if(em == null || !em.isOpen()){
				em = emf.createEntityManager();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error: Falhar ao criar conexao");
		}
		return em;
	}
	
	public void fecharConexao(){
		if(em != null && em.isOpen()){
			em.close();
		}
		if(emf != null && emf.isOpen()){
			emf.close();
		}
	}
	
	public void inserir(T entity) throws Exception{
		getEm().getTransaction().begin();
		getEm().persist(entity);
		getEm().getTransaction().commit();
	}

	public void alterar(T entity) throws Exception{
		getEm().getTransaction().begin();
		getEm().merge(entity);
		getEm().getTransaction().commit();
	}

	public Object buscar(long id) throws Exception{
		getEm().getTransaction().begin();
		T entity =  (T) getEm().find(classe, id);
		getEm().getTransaction().commit();
		return entity;
	}
	
	public ArrayList<T> listar() throws Exception{
		getEm().getTransaction().begin();
		ArrayList<T> lista =  (ArrayList<T>) getEm().createQuery("select o from " + classe.getSimpleName() + " o").getResultList();
		getEm().getTransaction().commit();
		return lista;
	}
	
	
	
	
	
}
