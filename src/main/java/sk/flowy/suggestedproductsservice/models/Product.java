package sk.flowy.suggestedproductsservice.models;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ToString
public class Product implements Serializable {

    private Long id;
    private String nazov;
    private String nazov_clear;
    private int aktivny;
    private int weighted;
    private int nonBlockable;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;

    public Product(String nazov) {
        this.nazov = nazov;
    }
}
