package mIoTServer;

import java.io.*;
import java.util.Scanner;

public class MainSender {

	public static void main(String[] args) {
		
		Scanner s = null;

        try {
            s = new Scanner(System.in);
	        while (true) {
	            System.out.println("Enter IP address");
	            String serverIP = s.nextLine();
	            System.out.println(serverIP);
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
	}

}
