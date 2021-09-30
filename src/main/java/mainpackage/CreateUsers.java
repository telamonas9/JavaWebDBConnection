package mainpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CreateUsers {
	
public static void main(String[] args) {
	
	try {
	File file = new File("C:\\Users\\gianniskz\\OneDrive\\Desktop\\fl.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    String line = null;
    while( (line = br.readLine())!= null )
       {
       
        String [] tokens = line.split("\\s+");
        String u = tokens[0];
        String p = tokens[1];
        String n = tokens[2];
        String s = tokens[3];
        String i = tokens[4];
        int am=Integer.parseInt(i);
        Patient pat1=new Patient(u,p,n,s,am);
        
    	System.out.println(pat1.getUsername());
    	System.out.println(pat1.getPassword());
    	System.out.println(pat1.getName());
    	System.out.println(pat1.getSurname());
    	System.out.println(pat1.getAMKA());     
       }
	 }
    catch(IOException e)
    {
        e.printStackTrace();
    }
	
	System.out.println("Give your username ! ");
	Scanner input = new Scanner(System.in);
	String u = input.nextLine();
	
	System.out.println("Give your name ! ");
	Scanner input2 = new Scanner(System.in);
	String n = input2.nextLine();
	
	System.out.println("Give your surname ! ");
	Scanner input3 = new Scanner(System.in);
	String s = input3.nextLine();
	
	System.out.println("Give your specialty ! ");
	Scanner input5 = new Scanner(System.in);
	String sp = input5.nextLine();
	
	
	System.out.println("Give your AMKA ! ");
	Scanner input4 = new Scanner(System.in);
	int am = input4.nextInt();
	
	
	System.out.println("Give your password ! ");
	Scanner input1 = new Scanner(System.in);
	String p = input1.nextLine();
	
	
	
	
	Doctor doc1= new Doctor(u,p,n,s,sp);
	Patient pat1=new Patient(u,p,n,s,am);
	
	System.out.println(doc1.getUsername());
	System.out.println(doc1.getName());
	System.out.println(doc1.getPassword());
	System.out.println(doc1.getSurname());
	System.out.println(doc1.getspecialty());
	
	System.out.println(pat1.getUsername());
	System.out.println(pat1.getName());
	System.out.println(pat1.getPassword());
	System.out.println(pat1.getSurname());
	System.out.println(pat1.getAMKA());
	
	
		
	
	
		Doctor doc= new Doctor("kaoz","123","gian","kaz","pil");
		Patient pat= new Patient("kz","321","ioan","kazas",2706);
		Admin adm=new Admin("gkz", "213","ion","zk");
		
		System.out.println(doc.getUsername());
		System.out.println(pat.getAMKA());
		System.out.println(adm.getSurname());
		
		doc.availableappointments();
		pat.findappointment();
		//adm.deleteuser();
		
		
	}


	
	
	

}
		


	
	
	
	

	



