package com.example.pai8.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    @Lob
    private byte[] attachment;
    @JoinColumn(name = "address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
