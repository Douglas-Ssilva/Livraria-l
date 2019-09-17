package br.com.caelum.livraria.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LogPhaseListener implements PhaseListener{

	private static final long serialVersionUID = 1L;

	//É executado depois de uma fase
	@Override
	public void afterPhase(PhaseEvent arg0) {
	}

	//É executado antes de uma fase
	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("Fase: "+ event.getPhaseId());
	}

	//Se refere a qual fase o phase atende
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE; //Todas as fases
	}

}
