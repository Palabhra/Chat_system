import java.io.*;
import java.net.*;
public class client {
    BufferedReader br;
    PrintWriter writer;
    Socket socket;
    public client(){
        //53430
        try {
            // System.out.println("Sending request...");

            //  ServerSocket server= new ServerSocket(4444);
            
             socket = new Socket("127.0.0.1",4444);
            // System.out.println("Line no. 14 is running");
              this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
              this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
             if (!socket.isClosed()) {
                start_reading();
            start_writing();
                System.out.println("clossing server...");
                //server.close();
           
            }
            else{
                socket.close();
            }
            // System.out.println("i'm able to make connection...");
        } catch (Exception e) {
            //TODO: handle exception
        }
        

    }
    public void start_reading(){
           thread1 reading = new thread1(br);
            reading.start();
    }
    public void start_writing(){
        thread2 writing = new thread2(writer);
        writing.start();
    }
    public static void main(String[] args) {
        System.out.println("client1 is online...");
        client obj_2 = new client();
        

    }

    
}

 class thread1 extends Thread{
     int flag=0;
     BufferedReader scan;
     public thread1(BufferedReader data){
         this.scan= data;
        //  System.out.println("I'm constracctor...");
     }
public void run(){
    
    // System.out.println("Reading...");
    while (true) {
        // System.out.println("Enter your input- ");
    String input;
    try {
        input = scan.readLine();
        switch (input) {
            case "exit":
                this.flag=1;                
                break;
        
            default:
                break;
        }
        System.out.println(input);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        // System.out.println("Reading started...");
       
        if (this.flag ==1) {
            break;
        }
    }
}
}

 class thread2 extends Thread{
     PrintWriter write;
     public thread2(PrintWriter wrote){
        this.write = wrote;
     }
    public void run(){
        while (true) {

            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
// System.out.println("out of try");
            try {
                // System.out.println("A1..");
                
                String data= br1.readLine();
          
                write.println(data);
                write.flush();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
}
