package imd.ufrn;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.function.Consumer;

import imd.ufrn.interfaces.BaseCommunicationWithServerController;
import imd.ufrn.interfaces.RmiSendToServerRemoteInterface;

public class ServerCommunicationRmiImpl extends BaseCommunicationWithServerController {
    private Registry registry;
    private RmiSendToServerRemoteInterface server;

    public ServerCommunicationRmiImpl(Consumer<String> callbackFunctionMessageRecieved) {
        super(callbackFunctionMessageRecieved);
        initialize();
    }

    @Override
    protected boolean initialize() {
        try {

            registry = LocateRegistry.getRegistry(1099);
            server = (RmiSendToServerRemoteInterface) registry
                    .lookup("RmiToServerRemoteInterface");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void sendMessage(String mensagem) {
        try {
            String serverResponse = server.messageToServer("Client Message");
            messageRecieved(serverResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void messageRecieved(String message) {
        this.callbackFunctionMessageRecieved.accept(message);
    }
}
