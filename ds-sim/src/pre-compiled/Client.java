import java.net.*;  
import java.io.*;  
class MyClient{  

public static void main(String args[])throws Exception{  
Socket s=new Socket("localhost",50000);
BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//DataInputStream din=new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
  
String str="",str2="";  
while(!str.equals("stop")){  
str=br.readUTF();  
dout.write(("HELO\n").getBytes());  
dout.flush();  
str2=din.readUTF();  
System.out.println("Server says: "+str2);  
}  