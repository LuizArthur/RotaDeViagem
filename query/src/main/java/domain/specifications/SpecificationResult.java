package domain.specifications;

public class SpecificationResult {
	private final boolean _isValid;
	private final String _message;
	private static SpecificationResult _success = new SpecificationResult(true, "");
	
	public static SpecificationResult success () {
		return _success;
	}
	
	public SpecificationResult(boolean isValid, String message) {
		_isValid = isValid;
		_message = message;
	}

	public boolean isValid() {
		return _isValid;
	}

	public String getMessage() {
		return _message;
	}
}
