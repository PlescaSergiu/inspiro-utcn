package com.inspiro.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String phone;
    private LocalDateTime birthday;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
