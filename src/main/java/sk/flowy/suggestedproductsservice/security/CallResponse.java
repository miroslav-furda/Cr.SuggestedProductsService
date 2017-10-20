package sk.flowy.suggestedproductsservice.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallResponse {
    private String success;
    private String error;

    boolean hasError() {
        return error != null;
    }
}
