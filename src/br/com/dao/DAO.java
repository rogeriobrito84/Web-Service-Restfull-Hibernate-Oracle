package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.util.FabricaConexao;
import br.com.util.PersistenceJPA;

public class DAO<T> {
	protected String persistenceName;
	protected EntityManagerFactory emf;
	protected EntityManager em;
	private final Class classe;
	
	public DAO(Class classe) {
		this.classe = classe;
	}

	public EntityManager getEm() throws Exception{
		try {
			emf = FabricaConexao.getConexao(PersistenceJPA.servicosJPA.name());
			if(em == null || !em.isOpen()){
				em = emf.createEntityManager();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error: Falhar ao criar conexao");
		}
		return em;
	}
	
	public void fecharConexao() throws Exception{
		if(em != null && em.isOpen()){
//			em.createNamedQuery("shutdown");
			em.close();
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
	
	public T excluir(long id) throws Exception{
		getEm().getTransaction().begin();
		T entity =   (T) getEm().find(classe, id);
		getEm().remove(entity);
		getEm().getTransaction().commit();
		return entity;
	}

	public T buscar(long id) throws Exception{
		getEm().getTransaction().begin();
		T entity =   (T) getEm().find(classe, id);
		getEm().getTransaction().commit();
		return entity;
	}
	
	public List<T> listar() throws Exception{
		getEm().getTransaction().begin();
		List<T> lista =  (ArrayList<T>) getEm().createQuery("select o from " + classe.getSimpleName() + " o").getResultList();
		getEm().getTransaction().commit();
		return lista;
	}
	
	public String testeRetorno(){
		return "Teste retorno";
	}
	
	
	
	
	
}
