package wd.team4.everycare.service.exception;

public class MemberHasExistException extends BasicException{

    public MemberHasExistException(){
        super("error 102", "존재하는 아이디 입니다.");
    }

    public MemberHasExistException(String id){
        super("error 102", "존재하는 아이디 입니다." + id);
    }

    public MemberHasExistException(String code, String message) {
        super(code, message);
    }
}
