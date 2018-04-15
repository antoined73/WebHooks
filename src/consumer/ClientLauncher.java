package consumer;

import java.util.Scanner;

public class ClientLauncher {
    public static void main(String[] args) {
        System.out.println("Enter a client name :");
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();
        new Client().launch(2501,name);
    }
}
