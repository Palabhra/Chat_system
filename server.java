import java.io.*;
import java.net.*;
public class server {
    BufferedReader br;
    PrintWriter writer;
    public server(){
        //53430
        try {
             ServerSocket server= new ServerSocket(4444);
            
            System.out.println("Expecting a connection");
             Socket socket = server.accept(); 
              this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
              this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            start_reading();
            start_writing();
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
        System.out.println("Server is started");
        server obj_1 = new server();
        

    }

    
}

 class thread1 extends Thread{
     int flag=0;
     BufferedReader scan;
     public thread1(BufferedReader data){
         this.scan= data;
         System.out.println("I'm constracctor...");
     }
public void run(){
    
    System.out.println("Reading...");
    while (true) {
        System.out.println("Enter your input- ");
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
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        System.out.println("Reading started...");
       
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

            try {
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
