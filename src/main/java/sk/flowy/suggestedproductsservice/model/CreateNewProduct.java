package sk.flowy.suggestedproductsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewProduct {

    private String eanNumber;
    private String productName;
    private String contractorName;
    private String picture;
}
