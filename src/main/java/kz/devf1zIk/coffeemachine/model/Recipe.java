package kz.devf1zIk.coffeemachine.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
