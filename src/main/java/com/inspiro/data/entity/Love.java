package com.inspiro.data.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Love extends AbstractEntity {

    private Long id;
    private String likeAuthor;

    @CreationTimestamp
    private Timestamp likeTime;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
