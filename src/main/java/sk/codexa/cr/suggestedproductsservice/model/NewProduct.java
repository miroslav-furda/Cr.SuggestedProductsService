package sk.codexa.cr.suggestedproductsservice.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewProduct implements Serializable {

    private static final long serialVersionUID = 8706174970893752799L;
    private List<String> ean;
    private String name;
    private String supplier;
    private String picture;
}
