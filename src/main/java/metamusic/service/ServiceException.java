package metamusic.service;

public class ServiceException extends RuntimeException {
    public ServiceException(Exception exc) {
        super(exc);
    }
}
