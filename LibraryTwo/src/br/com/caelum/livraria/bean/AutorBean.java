package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	private Integer autorId;
	
	public List<Autor> getAutores(){
		System.out.println("Retornando autores!");
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void gravar() {
		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User saved successfully!", ""));
		}else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User edited successfully!", ""));
		}
		autor= new Autor();
	}
	
	public void delete(Autor autor) {
		new DAO<Autor>(Autor.class).remove(autor);
		FacesContext.getCurrentInstance().addMessage(null ,new FacesMessage(FacesMessage.SEVERITY_INFO ,"User deleted successfully!",""));
	}
	
	public void edit(Autor autor) {
		this.autor= autor;
		System.out.println(this.autor);
	}
	
	public void loadAutor() {
		this.autor= new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}
	
	public Autor getAutor() {
		return autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
}
