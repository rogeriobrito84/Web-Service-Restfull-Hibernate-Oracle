package br.com.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaConexao {
	public static EntityManagerFactory emf = null;
	
	public static EntityManagerFactory getConexao(String persistenceName){
		if(emf == null){
			emf = Persistence.createEntityManagerFactory(persistenceName);
		}
		return emf;
	}
	
}
