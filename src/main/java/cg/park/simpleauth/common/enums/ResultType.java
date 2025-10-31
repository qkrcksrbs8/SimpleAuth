package cg.park.simpleauth.common.enums;

public enum ResultType {
    SUCCESS("S0001", "정상 처리되었습니다."),
    INVALID_PARAMETER("P0001", "파라미터를 확인해 주세요."),
    INVALID_TOKEN("P0002", "유효한 토큰이 아닙니다."),
    INVALID_LOGIN("P0003", "로그인 정보를 확인해 주세요."),
    UNKNOWN_ERROR("E0001", "오류가 발생했습니다.");

    private final String code;
    private final String message;

    ResultType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

}
