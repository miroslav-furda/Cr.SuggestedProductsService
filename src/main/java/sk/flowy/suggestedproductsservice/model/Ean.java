package sk.flowy.suggestedproductsservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ean")
public class Ean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hodnota")
    private String value;

    @Column(name = "typ")
    @NonNull
    private String type;

    @Column(name = "id_produkt")
    private Integer idProduct;

    @Column(name = "created_at")
    private Timestamp created;

    @Column(name = "updated_at")
    private Timestamp updated;

    @Column(name = "deleted_at")
    private Timestamp deleted;

    @ManyToOne
    @JoinColumn(name = "id_produkt", insertable = false, updatable = false)
    @JsonBackReference
    private Product product;
}

