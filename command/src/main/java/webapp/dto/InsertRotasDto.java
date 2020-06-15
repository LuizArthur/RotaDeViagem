package webapp.dto;

public class InsertRotasDto {
	private String message;
	
	public InsertRotasDto (String message) {
        setMessage(message);
    }

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
