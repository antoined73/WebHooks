package producer;

import java.io.IOException;

public class PClientLauncher {

    public static void main(String[] args) throws IOException, InterruptedException {
        PClient c = new PClient();
        c.launch(2501);
        System.err.println("# Streaming has started.");
        c.readFile();
    }
}
