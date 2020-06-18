package infra.data;

public class DataBaseFactory implements IDataBaseFactory{
	final private String mode = "DEV"; 
	
	
	@Override
	public String getDataSource() {
		if(mode.equals("DEV")) {
			return "/home/luizarthur/reposLuiz/RotaDeViagem/input-file.txt";
		}
		
		return null;
	}
	
}
