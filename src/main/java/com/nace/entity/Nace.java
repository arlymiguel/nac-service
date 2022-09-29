package com.nace.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Table(name = "nace")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nace {

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

    @Column(name="include", length = Integer.MAX_VALUE)
    private String include;

    @Column(name="rulings", length = Integer.MAX_VALUE)
    private String rulings;

    @Column(name="exclude", length = Integer.MAX_VALUE)
    private String exclude;

    @Column(name="reference")
    private String reference;

}
