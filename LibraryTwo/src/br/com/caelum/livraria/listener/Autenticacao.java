package br.com.caelum.livraria.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.User;

public class Autenticacao implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) { //Depois de filtrar, faça isso:
		FacesContext context = event.getFacesContext();
		String page = context.getViewRoot().getViewId(); //Retorna a página que estou querendo acessar
		
		if (page.equals("/login.xhtml")) {
			return; //Se for, deixe passar
		}
		
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		if (user != null) {
			return;
		}
		
		NavigationHandler handler= context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login.xhtml?faces-redirect=true");
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW; //Filtre somente na primeira fase
	}

}
