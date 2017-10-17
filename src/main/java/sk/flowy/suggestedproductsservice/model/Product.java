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
    private String nameClear;

    @Column(name = "aktivny")
    private Boolean active;

    private Boolean weighted;

    @Column(name = "nonBlockable")
    private Integer nonBlockable;

    @Column(name = "created_at")
    private Timestamp created;

    @Column(name = "updated_at")
    private Timestamp updated;

    @Column(name = "deleted_at")
    private Timestamp deleted;

    public Product(String name) {
        this.name = name;
    }
}