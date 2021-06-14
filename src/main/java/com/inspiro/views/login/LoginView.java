package com.inspiro.views.login;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "login")
@PageTitle("Login")
@CssImport("./styles/views/login/login-view.css")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

//    public LoginView(AuthService authService) {
//
//        setId("login-view");
//        var username = new TextField("Username");
//        var password = new PasswordField("Password");
//        add(
//                new H1("Welcome"),
//                username,
//                password,
//                new Button("Login", event -> {
//                    try {
//                        authService.authenticate(username.getValue(), password.getValue());
//                        UI.getCurrent().navigate("home");
//                    } catch (AuthService.AuthException e) {
//                        Notification.show("Wrong credentials.");
//                    }
//                }),
//                new RouterLink("Register", RegisterView.class)
//        );
//    }


    LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();

        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(FlexComponent.Alignment.CENTER);

        login.setAction("login");
        Anchor register =  new Anchor("/register", "Register");
        add(
                new H1("INSPIRO"),
                login,
                register
        );


    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

}
