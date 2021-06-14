package com.inspiro.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends AbstractEntity {

    private String postInfo;
    private Long parentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDateTime postDate;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Love> loveList;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Post> commentsList;

    public Post(String postInfo) {
        this.postInfo = postInfo;
    }

    public boolean isActive;
}
