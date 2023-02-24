package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "consumer")
@Data
public class Consumer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id")
    private long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Bill> bills;

}
