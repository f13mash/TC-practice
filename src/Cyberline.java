import java.util.*;
public class Cyberline 
{
	public static void main(String[] args){
		String test="@@@@";
		System.out.println("ans is : "+lastCyberword(test));
	}
	public static String lastCyberword(String cyberline) 
	{
		String[]w=cyberline.replaceAll("-","")
				.replaceAll("[^a-zA-Z0-9@]"," ")
				.split(" ") ;
		return w[w.length-1];
}
}