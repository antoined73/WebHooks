package consumer;

import java.util.Scanner;

public class CClientLauncher {

    /**
     * @param args the first argument should be the name of the client
     *             <br> if none specified, the user will be prompted upon launch.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Enter a client name :");
            Scanner s = new Scanner(System.in);
            String name = s.nextLine();
            new CClient().launch(2501, name);
        } else {
            new CClient().launch(2501, args[0]);
        }
    }
}
