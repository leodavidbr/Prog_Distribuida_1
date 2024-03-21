package imd.ufrn;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.function.Consumer;

import imd.ufrn.interfaces.BaseCommunicationWithServerController;
import imd.ufrn.interfaces.RmiRemoteInterface;

public class ServerCommunicationRmiImpl extends BaseCommunicationWithServerController {
    private Registry registry;
    private RmiRemoteInterface server;

    public ServerCommunicationRmiImpl(Consumer<String> callbackFunctionMessageRecieved) {
        super(callbackFunctionMessageRecieved);

    }

    @Override
    public void run() {
    }

    @Override
    protected boolean initialize() {
        try {
            registry = LocateRegistry.getRegistry();
            server = (RmiRemoteInterface) registry
                    .lookup("RmiRemoteInterface");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void sendMessage(String mensagem) {
        try {
            String responseMessage = server.getMessage("Client Message");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
