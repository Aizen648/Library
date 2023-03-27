package pl.kolo.Library.exception.exception;

public class MockException extends RuntimeException {
    public MockException(long number){
        super("error number "+number+" Not implemented yet, and will never be !!!");
    }
}
