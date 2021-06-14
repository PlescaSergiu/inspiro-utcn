package com.inspiro.views.login;

import com.inspiro.data.security.registration.RegistrationService;
import com.inspiro.data.service.AuthService;
import com.inspiro.data.utils.EmailValidator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.AllArgsConstructor;


@Route("register")
@AllArgsConstructor
public class RegisterView extends Composite {

    private final AuthService authService;
    private RegistrationService registrationService;



    @Override
    protected Component initContent() {
        TextField username = new TextField("Username");
        EmailField emailField =  new EmailField("Email");
        PasswordField password1 = new PasswordField("Password");
        PasswordField password2 = new PasswordField("Confirm password");
        Button sendButton = new Button("Send",
                event -> register(
                        username.getValue(),
                        emailField.getValue(),
                        password1.getValue(),
                        password2.getValue()));

        VerticalLayout layout = new VerticalLayout(
                new H2("Register"),
                username,
                emailField,
                password1,
                password2,
                sendButton);
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        return layout;
    }

    private void register(String username, String email, String password1, String password2) {
        if (username.trim().isEmpty()) {
            Notification.show("Enter a username");
        } else if (email.isEmpty()){
            Notification.show("Enter valid email");
        } else if (password1.isEmpty()) {
            Notification.show("Enter a password");
        } else if (!password1.equals(password2)) {
            Notification.show("Passwords don't match");
        }else if (!EmailValidator.validateEmail(email)) {
            Notification.show("Enter valid email");
        } else {
            registrationService.register(username, email, password1);
            UI.getCurrent().navigate("home");
            Notification.show("Check your email.");

        }
    }
}
