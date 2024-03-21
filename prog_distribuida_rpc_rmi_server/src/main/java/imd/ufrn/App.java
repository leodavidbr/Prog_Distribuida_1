package imd.ufrn;

import java.io.IOException;

public class App {
    public static final int PORT = 9999;

    public static void main(String[] args) throws IOException {

        Runnable chatController = new ChatController();
        Thread chatContollerThread = new Thread(chatController);
        chatContollerThread.start();

    }
}
