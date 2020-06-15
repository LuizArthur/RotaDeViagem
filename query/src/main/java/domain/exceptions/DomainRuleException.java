package domain.exceptions;

public class DomainRuleException extends Exception{
	private static final long serialVersionUID = 1L;

    public DomainRuleException(String message) {
        super(message);
    }
}
