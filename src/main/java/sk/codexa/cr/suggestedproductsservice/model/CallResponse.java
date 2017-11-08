package sk.codexa.cr.suggestedproductsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallResponse {
    private String success;
    private String error;

    public boolean hasError() {
        return error != null;
    }
}
