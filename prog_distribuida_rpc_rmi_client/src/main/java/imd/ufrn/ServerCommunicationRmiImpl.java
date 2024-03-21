package imd.ufrn;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Consumer;

import imd.ufrn.interfaces.BaseCommunicationWithServerController;
import imd.ufrn.interfaces.RmiSendToClientRemoteInterface;
import imd.ufrn.interfaces.RmiSendToServerRemoteInterface;

public class ServerCommunicationRmiImpl extends BaseCommunicationWithServerController
        implements RmiSendToClientRemoteInterface {
    private Registry registry;
    private RmiSendToServerRemoteInterface server;

    public ServerCommunicationRmiImpl(Consumer<String> callbackFunctionMessageRecieved) {
        super(callbackFunctionMessageRecieved);
        initialize();
    }

    @Override
    public void run() {
    }

    @Override
    protected boolean initialize() {
        try {
            createStubAndBind();

            // registry = LocateRegistry.getRegistry();
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
            server.messageToServer("Client Message");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void messageRecieved(String message) {
        this.callbackFunctionMessageRecieved.accept(message);
    }

    @Override
    public void messageToClient(String message) throws RemoteException {
        messageRecieved(message);
    }

    public void createStubAndBind() throws RemoteException {
        RmiSendToClientRemoteInterface stub = (RmiSendToClientRemoteInterface) UnicastRemoteObject
                .exportObject((RmiSendToClientRemoteInterface) this, 0);
        registry = LocateRegistry.getRegistry();
        registry.rebind("RmiToClientRemoteInterface", stub);
    }

}
