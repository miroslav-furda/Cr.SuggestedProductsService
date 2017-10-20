package sk.flowy.suggestedproductsservice.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewProduct {

    private List<String> ean;
    private String name;
    private Integer supplier;
    private String picture;
}
