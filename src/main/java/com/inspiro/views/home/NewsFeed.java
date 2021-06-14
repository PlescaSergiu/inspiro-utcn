package com.inspiro.views.home;


import com.inspiro.data.entity.Love;
import com.inspiro.data.entity.Post;
import com.inspiro.data.entity.User;
import com.inspiro.data.service.LoveServiceImp;
import com.inspiro.data.service.PersonServiceImp;
import com.inspiro.data.service.PostServiceImp;
import com.inspiro.data.service.UserServiceImp;
import com.inspiro.data.utils.Utils;
import com.inspiro.data.utils.converter.FormattingUtils;
import com.inspiro.views.dialog.AddSpeedDial;
import com.inspiro.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("News feed!")
@Route(value = "", layout = MainView.class)
@CssImport(value = "./styles/views/home/home-view.css", include = "lumo-badge")
@JsModule("@vaadin/vaadin-lumo-styles/badge.js")
public class NewsFeed extends Div implements AfterNavigationObserver {

    private final PersonServiceImp personServiceImp;
    private final UserServiceImp userServiceImp;
    private final PostServiceImp postServiceImp;
    private final LoveServiceImp loveServiceImp;

    Grid<Post> grid = new Grid<>();


    public NewsFeed(PersonServiceImp personServiceImp, UserServiceImp userServiceImp,
                    PostServiceImp postServiceImp, LoveServiceImp loveServiceImp) {
        this.userServiceImp = userServiceImp;
        this.personServiceImp = personServiceImp;
        this.postServiceImp = postServiceImp;
        this.loveServiceImp = loveServiceImp;

        setId("home-view");
        addClassName("home-view");
        setSizeFull();

        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(post -> createPostsCards(post));
        grid.getDataProvider().refreshAll();
        add(grid);

    }

    private HorizontalLayout createPostsCards(Post givenPost) {
        User user = givenPost.getUser();

        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        Image image = new Image();
        image.setSrc(user.getImage());

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(user.getUsername());
        name.addClassName("name");
        Span date = new Span(FormattingUtils.convertToFullTime(givenPost.getPostDate()));
        date.addClassName("date");
        header.add(name, date);

        //TODO Format text size to fill posts card
        Span post = new Span(givenPost.getPostInfo());
        post.addClassName("post");

        description.add(header, post, horizontalLayoutButtons(givenPost));
        card.setWidth("100%");
        card.add(image, description);
        return card;
    }

    private HorizontalLayout horizontalLayoutButtons(Post post ) {
        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        Icon loveIcon = new Icon(VaadinIcon.THUMBS_UP);
        loveIcon.setSize("15px");
        loveIcon.addClassName("loves");
        loveIcon.addClickListener(e -> addLove(post));
        Span likes = new Span(String.valueOf(post.getLoveList().size()));
        likes.addClassName("likes_number");


        Icon commentIcon = new Icon(VaadinIcon.COMMENT);
        commentIcon.setSize("15px");
        commentIcon.addClassName("comments");
        Span comments = new Span(String.valueOf(post.getCommentsList().size()));
        comments.addClassName("comments_number");


        Icon shareIcon = new Icon(VaadinIcon.SHARE);
        shareIcon.setSize("15px");
        shareIcon.addClassName("shares");
        Span shares = new Span("0");
        shares.addClassName("shares_number");


        actions.add(loveIcon, likes, commentIcon, comments, shareIcon, shares);
        return actions;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        AddSpeedDial speedDial = new AddSpeedDial(postServiceImp, userServiceImp);
        add(speedDial);

        List<Post> posts = postServiceImp.findAll()
                .stream()
                .sorted(Comparator.comparing(Post::getPostDate).reversed())
                .collect(Collectors.toList());
        posts
                .removeIf(p -> p.getUser().getUsername().equals(Utils.getUserNameOfCurrentUser()));
        grid.setItems(posts);
    }

    @Transactional
    public void addLove(Post post){
        Love love = new Love();
        love.setLikeAuthor(Utils.getUserNameOfCurrentUser());
        loveServiceImp.save(love);
        post.getLoveList().add(love);
        postServiceImp.save(post);
    }
}
