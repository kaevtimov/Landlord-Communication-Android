package source.kevtimov.landlordcommunicationapp.validation;

public interface Validator<T> {

    boolean isObjectValid(T object);
}
