package whu.edu;

public class IOCExcptions extends Exception{
    private ErrorType errorType;
    public enum ErrorType{
        FILE_NOTFOUND,
        FILE_READ_ERROR,
        CLASS_NOT_FOUND,
        ID_REPEAT,
        METHOD_CALL_ERROR,
        INVOCATION_ERROR,
        ILLEGAL_ACCESS

    }
    public IOCExcptions(ErrorType errorType,String message){
        super(message);
        this.errorType = errorType;
    }
    public ErrorType getErrorType(){
        return errorType;
    }
}
