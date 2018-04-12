package consumer.client;

public class ClientLauncher {
    public static void main(String[] args) {
        int numPort = (args.length>0? Integer.parseInt(args[0]) : 2500);
        new Client().launch(numPort);
    }
}
