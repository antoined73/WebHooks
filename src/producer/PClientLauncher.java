package producer;

import java.io.IOException;

public class PClientLauncher {
    public static void main(String[] args) throws IOException, InterruptedException {
        PClient c = new PClient();
        c.launch(2501);
        for (; c.registerSubscriber() != 0; ) ;
        System.err.println("slt");
        c.readFile();
        System.err.println("slt2");
    }
}
