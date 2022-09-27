package com.gft.deutsche.nace.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "naces")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nace implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order")
    private String order;

    @Column(name="level")
    private String level;

    @Column(name="code")
    private String code;

    @Column(name="parent")
    private String parent;

    @Column(name="description")
    private String description;

    @Column(name="include")
    private String include;

    @Column(name="rulings")
    private String rulings;

    @Column(name="exclude")
    private String exclude;

    @Column(name="reference")
    private String reference;

}
