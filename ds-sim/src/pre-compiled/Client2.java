import java.net.*;  
import java.io.*;  
class MyClient{  

public static void main(String args[])throws Exception{  
    //specifies connection to port
Socket s=new Socket("localhost",50000);
//reads external message on the same port (from server)
BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//Outputs onto the network to the server 
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
  
// Allows for client and server to communicate and input commands
String str="",str2="";  
while(!str.equals("stop")){  
str=br.readLine();  
dout.write((str+"\n").getBytes());  
dout.flush();  
str2=in.readLine();  
System.out.println("Server says: "+str2);   
        }  
    }
}