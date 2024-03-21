package imd.ufrn;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("start");

        Runnable chatController = new ChatController();
        Thread chatContollerThread = new Thread(chatController);
        chatContollerThread.start();
    }
}
