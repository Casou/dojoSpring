package com.bparent.dojo.dojoSpring.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="USERS")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column private String login;
    @Column private String name;
    @Column private String firstName;
    @Column private String job;

}
