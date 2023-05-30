import java.io.*;
import java.net.*;

public class Client3 {

    public static void main(String args[])throws Exception {
     
            
            
            Socket s = new Socket("localhost", 50000);
            DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String serverMessage = "";
            String clientMessage = "";
            int Servers = 0; // number of servers
            int job = 0; // 
            String serverInfo[]; // server messages stored in an array
           
            clientMessage = "HELO\n";
            outputStream.write(clientMessage.getBytes());
            outputStream.flush();
            serverMessage = inputStream.readLine();
         
            String username = System.getProperty("user.name");
            clientMessage = "AUTH " + username + "\n";
            outputStream.write(clientMessage.getBytes());
            outputStream.flush();
            serverMessage = inputStream.readLine();
            

            clientMessage = "REDY\n";
            outputStream.write(clientMessage.getBytes());
            outputStream.flush();
            serverMessage = inputStream.readLine();
            


            while (!serverMessage.equals("NONE")) { // if the server has one or more jobs

                // Finding the largest server and its type
                if (serverMessage.contains("JOBN")) { 


                    
                        String responseInfo[] = serverMessage.split(" ", -1); //Split information string into seperate parts e.g. (id, number, size)
                        // Sending GETS Avail message to get available servers
                        clientMessage = "GETS Avail " + responseInfo[4] + " " + responseInfo[5] + " " + responseInfo[6] + "\n";
                        outputStream.write(clientMessage.getBytes());
                        outputStream.flush();
                        serverMessage = inputStream.readLine();
                        
                         serverInfo = serverMessage.split(" ", -1);

                        if (Integer.parseInt(serverInfo[1]) != 0) { // Convert split string into integers (server information))
                            Servers = Integer.parseInt(serverInfo[1]);
                            serverInfo = new String[Servers];

                            // Sending OK message to indicate readiness for receiving server records
                            clientMessage = "OK\n";
                            outputStream.write(clientMessage.getBytes());
                            outputStream.flush();
                            
                                // Reading and storing the server records
                                for (int i = 0; i < Servers; i++) {
                                    serverInfo[i] = inputStream.readLine();
                             
                                 }

                            // Sending OK message to indicate readiness for scheduling the job
                            clientMessage = "OK\n";
                            outputStream.write(clientMessage.getBytes());
                            outputStream.flush();
                            serverMessage = inputStream.readLine();

                            String availServer[] = serverInfo[0].split(" ", -1);

                            job = Integer.parseInt(responseInfo[2]);
                            // Sending SCHD message to schedule the job on the first server
                            clientMessage = "SCHD" + job + availServer[0] + " " + availServer[1] + "\n";
                            outputStream.write(clientMessage.getBytes());
                            outputStream.flush();
                            serverMessage = inputStream.readLine();
                          

                        } else {                           
                            // Sending OK message to indicate readiness for receiving server records
                            clientMessage = "OK\n";
                            outputStream.write(clientMessage.getBytes());
                            outputStream.flush();
                            serverMessage = inputStream.readLine();
                            

                            // Sending GETS Capable message to get capable servers
                            clientMessage = "GETS Capable " + responseInfo[4] + " " + responseInfo[5] + " " + responseInfo[6] + "\n";
                            outputStream.write(clientMessage.getBytes());
                            outputStream.flush();
                            serverMessage = inputStream.readLine();
                          

                           
                            serverInfo = serverMessage.split(" ", -1);

                            Servers = Integer.parseInt(serverInfo[1]);
                            serverInfo = new String[Servers];

                            // Sending OK message to indicate readiness for receiving server records
                            clientMessage = "OK\n";
                            outputStream.write(clientMessage.getBytes());
                            outputStream.flush();

                            for (int i = 0; i < Servers; i++) {
                                // Reading and storing the server records
                                serverInfo[i] = inputStream.readLine();
                                
                            }

                            // Sending OK message to indicate readiness for scheduling the job
                            clientMessage = "OK\n";
                            outputStream.write(clientMessage.getBytes());
                            outputStream.flush();
                            serverMessage = inputStream.readLine();

                            String availServer[] = serverInfo[0].split(" ", -1);

                            job = Integer.parseInt(responseInfo[2]);
                            // Sending SCHD message to schedule the job on the first server
                            clientMessage = "SCHD" + job + " " + availServer[0] + " " + availServer[1] + "\n";
                            outputStream.write(clientMessage.getBytes());
                            outputStream.flush();
                            serverMessage = inputStream.readLine();                           

                        }
                     
                    

            
            }
            clientMessage = "REDY\n";
                            outputStream.write(clientMessage.getBytes());
                            outputStream.flush();
                            serverMessage = inputStream.readLine();
            
            
    }
    clientMessage = "QUIT\n";
            outputStream.write(clientMessage.getBytes());
            outputStream.flush();
            serverMessage = inputStream.readLine();

            outputStream.close();
            s.close();
        
}
}
