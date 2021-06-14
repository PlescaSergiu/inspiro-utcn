package com.inspiro.views.postsview.mypost;


import com.inspiro.data.entity.Post;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class PostForm extends FormLayout {
    TextField postInfo = new TextField("First name");


    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Post> binder = new BeanValidationBinder<>(Post.class);
    private Post post;

    public PostForm() {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        add(
                postInfo,
                createButtonsLayout()
        );
    }

    public void setPost(Post post) {
        this.post = post;
        binder.readBean(post);
    }

    private Component createButtonsLayout() {

        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        close.addClickShortcut(Key.ESCAPE);

        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, post)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));
        return new HorizontalLayout( delete, close);
    }


    // Events
    public static abstract class ContactFormEvent extends ComponentEvent<PostForm> {
        private Post post;

        protected ContactFormEvent(PostForm source, Post post) {
            super(source, false);
            this.post = post;
        }

        public Post getPost() {
            return post;
        }
    }


    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(PostForm source, Post post) {
            super(source, post);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(PostForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
