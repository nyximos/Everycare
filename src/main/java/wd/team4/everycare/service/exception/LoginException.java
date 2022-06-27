package wd.team4.everycare.service.exception;

public class LoginException extends BasicException{

    public LoginException(){
        super("error", "활동이 정지된 아이디입니다.");
    }

    public LoginException(String id){
        super("error", "존재하지 않는 아이디 입니다." + id);
    }

    public LoginException(String code, String message) {
        super(code, message);
    }
}
