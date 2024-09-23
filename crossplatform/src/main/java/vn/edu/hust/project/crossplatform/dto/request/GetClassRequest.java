package vn.edu.hust.project.crossplatform.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetClassRequest {
    private String token;
    private String role;
    private int user_id;
}
