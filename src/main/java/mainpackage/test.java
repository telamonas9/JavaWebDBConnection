package mainpackage;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import dbConnectionPackage.PatientConnection;




public class test {
	 
	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
			
	String data = "Hello World";
	String algorithm = "MD5";
	byte[] salt = createSalt();
	System.out.println(generateHash(data,algorithm));
		
	}
	
	

	private static byte[] createSalt() {
		// TODO Auto-generated method stub
		return null;
	}

	private static String generateHash(String data, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest mg = MessageDigest.getInstance(algorithm);
		mg.reset();
		byte[] hash = mg.digest(data.getBytes());
		return s;
	}

}
