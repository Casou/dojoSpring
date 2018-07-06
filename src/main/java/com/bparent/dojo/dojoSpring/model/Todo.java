package com.bparent.dojo.dojoSpring.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "TODO")
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name="text")
    private String text;

}
