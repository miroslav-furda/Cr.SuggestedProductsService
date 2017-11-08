package sk.codexa.cr.suggestedproductsservice.model;

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

    private static final long serialVersionUID = 9092612321248884295L;
    private Long id;
    private String name;
    private List<Ean> eans;
}
