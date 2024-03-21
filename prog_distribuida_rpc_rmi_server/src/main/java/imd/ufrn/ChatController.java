package imd.ufrn;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;

import imd.ufrn.interfaces.BaseCommunicationWithClientController;

public class ChatController implements Runnable {
    private static final boolean TRACE_MODE = false;
    private BaseCommunicationWithClientController clientCommunicationController;
    Chat chatSession;

    public ChatController() {
        try {
            clientCommunicationController = new ClientCommunicationRmiImpl(
                    clientMessage -> handleMessageReceivedFromClient(clientMessage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        String resourcesPath = getResourcesPath();
        MagicBooleans.trace_mode = TRACE_MODE;
        Bot bot = new Bot("super", resourcesPath);
        chatSession = new Chat(bot);
        bot.brain.nodeStats();
    }

    @Override
    public void run() {
        this.initialize();

        clientCommunicationController.run();
    }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }

    public void handleSendMessageFromServer(String serverMessage) {
        clientCommunicationController.sendMessage(serverMessage);
    }

    public String handleMessageReceivedFromClient(String clientMessage) {
        String response = "";
        if ((clientMessage == null) || (clientMessage.length() < 1))
            clientMessage = MagicStrings.null_input;
        if (clientMessage.equals("::EXIT")) {
            System.exit(0);
        } else {
            String request = clientMessage;
            if (MagicBooleans.trace_mode)
                System.out.println(
                        "STATE=" + request + ":THAT=" + ((History<?>) chatSession.thatHistory.get(0)).get(0)
                                + ":TOPIC=" + chatSession.predicates.get("topic"));
            response = chatSession.multisentenceRespond(request);
            while (response.contains("&lt;"))
                response = response.replace("&lt;", "<");
            while (response.contains("&gt;"))
                response = response.replace("&gt;", ">");

        }
        return response;
    }
}
