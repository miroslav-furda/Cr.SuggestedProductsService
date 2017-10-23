package sk.flowy.suggestedproductsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Object that maps to client specific object in receipt project.
 */
@Data
@AllArgsConstructor
public class ReceiptProduct implements Serializable {

    private Long id;
    private String name;
    private List<Ean> eans;
}
