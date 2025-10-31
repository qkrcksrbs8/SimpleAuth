package cg.park.simpleauth.common.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Auth {

    @NotBlank(message = "로그인 정보를 확인해 주세요.")
    private String userId;

    @NotBlank(message = "로그인 정보를 확인해 주세요.")
    private String userPassword;

}
