package com.inspiro.views.dialog;


import com.inspiro.data.service.PostServiceImp;
import com.inspiro.data.service.UserServiceImp;
import com.inspiro.views.postsview.AddPostDialog;
import github.tobsef.vaadin.paperfab.SpeedDial;

import static com.vaadin.flow.component.icon.VaadinIcon.TWITTER;


public class AddSpeedDial extends SpeedDial {

    private final PostServiceImp postServiceImp;
    private final UserServiceImp userServiceImp;

    public AddSpeedDial(PostServiceImp postServiceImp, UserServiceImp userServiceImp) {
        this.postServiceImp = postServiceImp;
        this.userServiceImp = userServiceImp;
        setBackdrop(false);
        setMarginRight("32px");
        setColor("var(--material-primary-text-color)");
        setColorAction("var(--material-primary-text-color)");
        addMenuItem("Add new post!", TWITTER.create(), e -> createNewPost());
    }

    public void createNewPost() {
        AddPostDialog AddPostDialog = new AddPostDialog(postServiceImp, userServiceImp);
        close();
        AddPostDialog.open();
    }
}
