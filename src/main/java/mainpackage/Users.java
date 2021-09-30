package mainpackage;


public class Users {
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private String userType;
	
	
	public static int usersCounter=0;
	
	public Users(String username,String password, String name, String surname) 
	{
		this.username=username;
		this.password=password;
		this.name=name;
		this.surname=surname;	
		usersCounter++;
		
	}
	public void setUserType(String type) {
		userType = type;
	}
	public String getUserType() {
		return userType;
	}
	
	/*
	 * public void login() {
	 * 
	 * System.out.println(name + "Successfull login");
	 * 
	 * 
	 * } public void logout() {
	 * 
	 * System.out.println(name + "Syccessfull Logout"); }
	 */
	
	public String getUsername() 
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public int getCounter()
	{
		return usersCounter;
	}
	
	public void setUsername(String newUsername)
	{
		this.username = newUsername;
	}
	
	public void setPassword(String newPassword)
	{
		this.password = newPassword;
	}
	
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	public void setSurname(String newSurname)
	{
		this.surname = newSurname;
	}
	
	
	
	
	
	
	
	 
	
	
	
	
	

}
