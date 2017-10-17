package sk.flowy.suggestedproductsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produkt")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nazov")
    private String name;

    @Column(name = "nazov_clear")
    private String name_clear;

    @Column(name = "aktivny")
    private boolean active;
    private boolean weighted;
    private int nonBlockable;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;

    public Product(String name) {
        this.name = name;
    }
    
}