package producer;

import java.io.IOException;
import java.util.Scanner;

public class PClientLauncher {
    public static void main(String[] args) throws IOException, InterruptedException {
        PClient c = new PClient();
        c.launch(2501);
        Scanner s = new Scanner(System.in);
        s.next();
        for (; c.registerSubscriber() != 0; ) ;
        c.readFile();
    }
}
