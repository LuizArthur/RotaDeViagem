package webapp.dto;

public class InsertRotasDto {
	private String body;
	private Integer status;
	
	
	public InsertRotasDto (String body) {		
		setBody(body);
        setStatus((Integer) 200);
    }
	
	public InsertRotasDto (Exception e) {
		setBody(e.getMessage());
        setStatus((Integer) 500);
    }

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
