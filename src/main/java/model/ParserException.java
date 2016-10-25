package model;

public class ParserException extends RuntimeException {

	private static final long serialVersionUID = 2804740549723047963L;
	
	public String message = "Try --help, -h for help.";
	
	public ParserException(String message){
		this.message += "\n" + message; 
	}
	
	public ParserException(Exception ex){
		this.message += "\n" + ex.getMessage();
	}
	
	@Override
    public String getMessage(){
        return message;
    }
}
