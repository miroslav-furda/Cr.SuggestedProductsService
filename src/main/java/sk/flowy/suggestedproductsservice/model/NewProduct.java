package sk.flowy.suggestedproductsservice.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewProduct {

    private List<String> ean;
    private String name;
    private String supplier;
    private String picture;
}
