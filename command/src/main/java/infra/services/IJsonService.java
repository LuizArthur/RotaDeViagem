package infra.services;

public interface IJsonService {
	public <T> String classToJson(T entity);	
}
