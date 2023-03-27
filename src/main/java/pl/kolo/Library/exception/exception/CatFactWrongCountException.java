package pl.kolo.Library.exception.exception;

public class CatFactWrongCountException extends RuntimeException{
    public CatFactWrongCountException(int Count){
        super("Cat Fact Client cannot accept such an input number");
    }
}
