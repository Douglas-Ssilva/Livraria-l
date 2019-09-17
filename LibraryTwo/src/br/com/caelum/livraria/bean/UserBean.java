package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UserLogin;
import br.com.caelum.livraria.modelo.User;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user= new User();
	
	public String login() {
		System.out.println("Entrando no system....");
		boolean existe = new UserLogin().login(user);
		if (existe) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Loged successfully!",""));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", this.user);
			return "livro?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true); //Mantenha a msg por 2 requisições
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User or password invalids!"));
		return "/login?faces-redirect=true";
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
	
	public User getUser() {
		return user;
	}



}
