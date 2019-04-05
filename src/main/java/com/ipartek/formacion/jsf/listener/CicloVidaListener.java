package com.ipartek.formacion.jsf.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CicloVidaListener implements javax.faces.event.PhaseListener {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LogManager.getRootLogger();

	@Override
	public void beforePhase(PhaseEvent phaseEvent) {
		if (LOG.isInfoEnabled()) {
			LOG.trace("ANTES PHASE: " + phaseEvent.getPhaseId().toString());
		}
		
	}

	@Override
	public void afterPhase(PhaseEvent phaseEvent) {

		if (LOG.isInfoEnabled()) {
			LOG.trace("DESPUES PHASE: " + phaseEvent.getPhaseId().toString());
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
