package br.com.resource;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.w3c.dom.ranges.RangeException;

import br.com.bo.UsuarioBO;
import br.com.model.Usuario;

@Path("/usuario")
public class UsuarioResource {
	private UsuarioBO usuarioBo;
	
	private UsuarioBO getBO(){
		if(usuarioBo == null){
			usuarioBo = new UsuarioBO();
		}
		return usuarioBo;
	}
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Usuario> listar(){
		return getBO().listar();
	}
	
	@GET
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON})
	public Usuario buscar(@PathParam("id") long id){
		return getBO().buscar(id);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.TEXT_PLAIN})
	public String excluir(@PathParam("id") long id){
		return getBO().excluir(id);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.TEXT_PLAIN})
	public String inserir(Usuario usuario){
		return getBO().inserir(usuario);
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.TEXT_PLAIN})
	public String alterar(Usuario usuario){
		return getBO().alterar(usuario);
	}
	
}
