package com.inspiro.views.postsview;



import com.inspiro.data.entity.Post;
import com.inspiro.data.entity.User;
import com.inspiro.data.service.PostServiceImp;
import com.inspiro.data.service.UserServiceImp;
import com.inspiro.data.utils.Utils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;


public class AddPostDialog extends Dialog {

    private TextArea comment;
    private final PostServiceImp postServiceImp;

    private final UserServiceImp userServiceImp;

    public AddPostDialog(PostServiceImp postServiceImp, UserServiceImp userServiceImp) {
        this.postServiceImp = postServiceImp;
        this.userServiceImp = userServiceImp;
        setCloseOnOutsideClick(false);
        setWidth("360px");
        setHeight("550px");

        VerticalLayout content = new VerticalLayout();
        content.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        content.setSizeFull();
        add(content);
        addTitle(content);
        addComment(content);
        addActions(content);
    }

    private void addActions(VerticalLayout content) {
        Button ok = new Button("Submit");
        ok.addClickListener(e -> Notification.show("You post was shared!!!"));
        ok.addClickListener(e -> submit());
        Button cancel = new Button("Cancel");
        cancel.addClickListener(e -> cancel());

        HorizontalLayout actions = new HorizontalLayout(cancel, ok);
        actions.setWidthFull();
        actions.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        actions.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        content.add(actions);
    }

    private void cancel() {
        close();
    }

    private void submit() {
        Post post = new Post();
        String username = Utils.getUserNameOfCurrentUser();
        User user = userServiceImp.findUserByUsername(username);
        if (!comment.getValue().isEmpty()) {
            post.setUser(user);
            post.setPostInfo(comment.getValue());
            postServiceImp.save(post);
        }
        close();
    }

    private void addTitle(VerticalLayout content) {
        HorizontalLayout title = new HorizontalLayout();
        title.setAlignItems(FlexComponent.Alignment.BASELINE);
        title.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        title.setWidthFull();
        title.add(new Icon(VaadinIcon.TWITTER_SQUARE));
        title.add(new H2("Add new post"));
        title.setMargin(false);
        title.setPadding(false);
        content.add(title);
    }

    private void addComment(VerticalLayout content) {
        comment = new TextArea();
        comment.setWidthFull();
        comment.setHeightFull();
        comment.setPlaceholder("Share your thoughts!");
        comment.addClassName("text-add-dialog");
        content.setFlexGrow(1, comment);
        content.add(comment);
    }

}
