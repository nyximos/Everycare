package wd.team4.everycare.service.exception;

public class CareTargetNotFoundException  extends BasicException{

    public CareTargetNotFoundException() {
        super("error 301", "해당 케어 대상인을 찾을 수 없습니다.");
    }

    public CareTargetNotFoundException (Long id){
        super("error 301", "해당 케어 대상인을 찾을 수 없습니다.");
    }

    public CareTargetNotFoundException(String code, String message) {
        super(code, message);
    }
}
