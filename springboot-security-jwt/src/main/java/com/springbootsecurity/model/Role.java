package com.springbootsecurity.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name="TB_ROLE")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
