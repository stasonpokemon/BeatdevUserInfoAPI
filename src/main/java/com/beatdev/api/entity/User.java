package com.beatdev.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class presents an user's entity, which will be stored in the database.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    @ToString.Exclude
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "img_link", nullable = false)
    private String imageLink;

    @Column(name = "user_status", nullable = false)
    private UserStatus status;

}
