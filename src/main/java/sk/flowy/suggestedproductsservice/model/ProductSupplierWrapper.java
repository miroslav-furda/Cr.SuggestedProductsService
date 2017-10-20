package sk.flowy.suggestedproductsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProductSupplierWrapper implements Serializable {
    private Long delivererId;
    private Long productId;
}
