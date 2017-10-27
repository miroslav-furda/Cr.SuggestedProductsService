package sk.flowy.suggestedproductsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProductSupplierWrapper implements Serializable {
    private static final long serialVersionUID = 7735681261909112749L;
    private Long delivererId;
    private Long productId;
}
