package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private Integer idAutor;
	private Integer id;

	public void messageValidator(FacesContext context, UIComponent component, Object value)throws ValidatorException {
		String s = value.toString();
		if (!s.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN should start with 1!"));
		}
		
	}
	
	public String redirect() {
		System.out.println("Redirecionando...");
		return "autor?faces-redirect=true";
	}
	
	public void loadById() {
		this.livro= new DAO<Livro>(Livro.class).buscaPorId(id);
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Autor> getAutoresLivro(){
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros(){
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public void addAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(idAutor);
		this.livro.adicionaAutor(autor);
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor!"));
			return;
		}
		
		if (this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		}else {
			new DAO<Livro>(Livro.class).atualiza(this.livro); 
		}

		livro = new Livro();
	}
	
	public void delete(Livro livro) {
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	public void edit(Livro livro) {
		this.livro= livro;
	}
	
	public void removeAutor(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
