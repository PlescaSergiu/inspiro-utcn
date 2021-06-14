package com.inspiro.data.service;

import com.inspiro.data.dao.UserRepository;
import com.inspiro.data.entity.Role;
import com.inspiro.data.entity.User;
import com.inspiro.views.PersonView;
import com.inspiro.views.admin.AdminView;
import com.inspiro.views.home.NewsFeed;
import com.inspiro.views.logout.LogoutView;
import com.inspiro.views.main.MainView;
import com.inspiro.views.postsview.mypost.MyPostsView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AuthService {

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }

    public static class AuthException extends Exception {

    }

    private final UserRepository userRepository;
    private final MailSender mailSender;
    private User loggedUser;

    public AuthService(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

//
//    public void authenticate(String username, String password) throws AuthException {
//        User user = userRepository.getByUsername(username);
//        if (user != null && user.checkPassword(password) && user.isActive()) {
//            VaadinSession.getCurrent().setAttribute(User.class, user);
//            loggedUser = user;
//            createRoutes(user.getRole());
//        } else {
//            throw new AuthException();
//        }
//    }

    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).stream()
                .forEach(route ->
                        RouteConfiguration.forSessionScope().setRoute(
                                route.route, route.view, MainView.class));
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role) {
        var routes = new ArrayList<AuthorizedRoute>();

        if (role.equals(Role.USER)) {
            routes.add(new AuthorizedRoute("home", "Home", NewsFeed.class));
            routes.add(new AuthorizedRoute("details", "Details", PersonView.class));
            routes.add(new AuthorizedRoute("myposts", "My Posts", MyPostsView.class));
            routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));

        } else if (role.equals(Role.ADMIN)) {
            routes.add(new AuthorizedRoute("home", "Home", NewsFeed.class));
            routes.add(new AuthorizedRoute("admin", "Admin", AdminView.class));
            routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));

        }

        return routes;
    }



    public User getLoggedUser() {
        return loggedUser;
    }
}
