package consumer;

import java.util.Scanner;

public class CClientLauncher {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Enter a client name :");
            Scanner s = new Scanner(System.in);
            String name = s.nextLine();
            new CClient().launch(2501,name);
        } else {
            new CClient().launch(2501,args[0]);
        }
    }
}
