package com.inspiro.views.postsview.mypost;


import com.inspiro.data.entity.Post;
import com.inspiro.data.service.PostServiceImp;
import com.inspiro.data.utils.Utils;
import com.inspiro.data.utils.converter.FormattingUtils;
import com.inspiro.views.main.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;


@Route(value = "/myposts", layout = MainView.class)
@PageTitle("My Posts ")
public class MyPostsView  extends VerticalLayout {


    private final PostServiceImp postServiceImp;
    private Grid<PostShowForm> grid = new Grid<>(PostShowForm.class);
    private PostForm form;

    public MyPostsView(PostServiceImp postServiceImp) {
        this.postServiceImp = postServiceImp;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new PostForm();
        form.addClassName("content");
        form.addListener(PostForm.DeleteEvent.class, this::deletePost);
        form.addListener(PostForm.CloseEvent.class, e -> closeEditor());

        add(grid, form);
        populateList();
    }

    private void configureGrid() {
        grid.addClassName("post-grid");
        grid.setSizeFull();
        grid.setColumns("postText","author", "postDate", "postStatus");


    }

    public void populateList(){
        List<PostShowForm> postSetList = new ArrayList<>();
        for (Post p:postServiceImp.getAllByUserName(Utils.getUserNameOfCurrentUser())    ) {
                postSetList.add(convertPostToShow(p));
        }
       grid.setItems(postSetList);
    }

    private void closeEditor() {
        form.setPost(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void deletePost(PostForm.DeleteEvent evt) {
        postServiceImp.deletePost(evt.getPost());
        populateList();
        closeEditor();
    }


    public PostShowForm convertPostToShow(Post post){
        PostShowForm postShowForm = new PostShowForm();
        postShowForm.setPostText(post.getPostInfo());
        postShowForm.setAuthor(post.getUser().getUsername());
        postShowForm.setPostDate(FormattingUtils.convertToFullTime(post.getPostDate()));
        postShowForm.setPostStatus(PostStatus.PUBLIC);
        return postShowForm;
    }
}
