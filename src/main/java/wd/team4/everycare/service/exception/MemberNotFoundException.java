package wd.team4.everycare.service.exception;

public class MemberNotFoundException extends BasicException{
    public MemberNotFoundException(){
        super("error 101","Not Found User");
    }
    public MemberNotFoundException(String id){
        super("error 101", "Not Found with id: " + id);
    }

    public MemberNotFoundException(String code, String message) {
        super(code, message);
    }
}
