package sk.flowy.suggestedproductsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoundProduct implements Serializable {

    private Long id;
    private String name;
    private List<Ean> eans;
    private Double price;
    private Double quantity;
}
