package producer;

import java.io.IOException;
import java.util.Scanner;

public class PClientLauncher {
    public static void main(String[] args) throws IOException, InterruptedException {
        PClient c = new PClient();
        c.launch(2501);
        System.out.println("Press a character and enter to begin the streaming :");
        Scanner s = new Scanner(System.in);
        s.next();
        System.out.println("Stream started\n");
        c.readFile();
    }
}
