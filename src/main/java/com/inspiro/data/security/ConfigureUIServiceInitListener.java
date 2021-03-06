package com.inspiro.data.security;


import com.inspiro.views.login.ActivationView;
import com.inspiro.views.login.LoginView;
import com.inspiro.views.login.RegisterView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

	@Override
	public void serviceInit(ServiceInitEvent event) {
		event.getSource().addUIInitListener(uiEvent -> {
			final UI ui = uiEvent.getUI();
			ui.addBeforeEnterListener(this::beforeEnter);
		});
	}

	/**
	 * Reroutes the user if they're not authorized to access the view.
	 *
	 * @param event
	 *            before navigation event with event details
	 */
	private void beforeEnter(BeforeEnterEvent event) {
		if (!LoginView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()) {
			event.rerouteTo(LoginView.class);
		}
		if (RegisterView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()){
			event.rerouteTo(RegisterView.class);
		}
		if (ActivationView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()){
			event.rerouteTo(ActivationView.class);
		}

	}
}