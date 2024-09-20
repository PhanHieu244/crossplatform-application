package vn.edu.hust.project.crossplatform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;

@AllArgsConstructor
@Getter
@Setter
public class MetaResource {
    private Long code;
    private String message;

    public MetaResource(ResponseCode responseCode) {
        code = responseCode.getCode();
        message = responseCode.getMessage();
    }
}
