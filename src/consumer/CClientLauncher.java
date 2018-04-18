package consumer;

import java.util.Scanner;

public class CClientLauncher {

    /**
     * @param args the first argument should be the name of the client
     *             <br> if none specified, the user will be prompted upon launch.
     */
    public static void main(String[] args) {
        String name;
        if (args.length == 0) {
            System.out.println("Enter a client name :");
            Scanner s = new Scanner(System.in);
            name = s.nextLine();
        }else{
            name = args[0];
        }
        new CClient().launch(2501, name);
    }
}
