package mIoTServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;


class Sender{

    String programName = "";
    Sender (String programName){
        this.programName = programName;
    }

    String generateData (int sizeInBytes) {

        int numberofBytes = sizeInBytes;

        char [] _data = new char[sizeInBytes+1];

        for(int i = 0; i< numberofBytes; i++) {
            _data [i]= 'N';
        }
        return new String(_data);
    }

    void sendData(String serverIP, int serverPort, int size) throws InterruptedException {

        String payload = generateData(size);
        Thread t = new Thread(new SenderWorker(serverIP, serverPort,payload));
    }

    class SenderWorker  implements Runnable  {

        String serverAddress = null;
        int serverPort = 0;
        String payload = null;

        SenderWorker(String serverAddress, int serverPort, String payload){
            this.serverAddress = serverAddress;
            this.serverPort = serverPort;
            this.payload = payload;
        }

        @Override
        public void run() {
            try {
                long startTime = System.nanoTime();

                Socket socket = new Socket(this.serverAddress, this.serverPort);
                //System.out.println("Sending data to " + socket.getInetAddress().getHostAddress() +":" + socket.getPort() );
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println(this.payload);
                String response = in.readLine();

                long estimatedTime = System.nanoTime() - startTime;

                System.out.println("Response from Server: " + response +"; Time to receive: " + estimatedTime + " ns");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}