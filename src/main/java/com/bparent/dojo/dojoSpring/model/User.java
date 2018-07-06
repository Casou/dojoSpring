package com.bparent.dojo.dojoSpring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column private String login;
    @Column private String name;
    @Column private String firstName;
    @Column private String job;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Todo> todos;

}
