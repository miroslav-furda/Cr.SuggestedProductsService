package sk.flowy.suggestedproductsservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produkt")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated product ID")
    private Long id;

    @Column(name = "nazov")
    private String name;

    @Column(name = "nazov_clear")
    private String nameClear;

    @Column(name = "aktivny")
    private Integer active;

    private Boolean weighted;

    @Column(name = "nonBlockable")
    private Integer nonBlockable;

    @Column(name = "created_at")
    private Timestamp created;

    @Column(name = "updated_at")
    private Timestamp updated;

    @Column(name = "deleted_at")
    private Timestamp deleted;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ean> eans;

    public Product(String name) {
        this.name = name;
    }
}