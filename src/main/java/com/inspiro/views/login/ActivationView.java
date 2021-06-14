package com.inspiro.views.login;


import com.inspiro.data.security.registration.RegistrationService;
import com.inspiro.data.service.AuthService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.List;
import java.util.Map;

@Route("activate")
public class ActivationView extends Composite<Component> implements BeforeEnterObserver {

    private VerticalLayout layout;

    private final RegistrationService registrationService;

    public ActivationView(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        try {
            Map<String, List<String>> params = event.getLocation().getQueryParameters().getParameters();
            String code = params.get("code").get(0);
            registrationService.activate(code);
            layout.add(
                    new Text("Account activated."),
                    new RouterLink("Login", LoginView.class)
            );
        } catch (AuthService.AuthException e) {
            layout.add(new Text("Invalid link."));
        }
    }

    @Override
    protected Component initContent() {
        layout = new VerticalLayout();
        return layout;
    }
}
