package id.mdm17.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataResponse {
    private int code;
    private String message;
    private Object data;
}
