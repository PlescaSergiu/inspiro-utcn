package com.inspiro.views.postsview.mypost;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostShowForm {

    private String postText;
    private String author;
    private String  postDate;
    private PostStatus postStatus;

}
