package imd.ufrn;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Consumer;

import imd.ufrn.interfaces.BaseCommunicationWithClientController;
import imd.ufrn.interfaces.RmiSendToClientRemoteInterface;
import imd.ufrn.interfaces.RmiSendToServerRemoteInterface;

public class ClientCommunicationRmiImpl extends BaseCommunicationWithClientController
        implements RmiSendToServerRemoteInterface {
    private Registry registry;
    private RmiSendToClientRemoteInterface server;

    public ClientCommunicationRmiImpl(Consumer<String> callbackFunctionMessageReceived) throws RemoteException {
        super(callbackFunctionMessageReceived);
        initialize();
        System.out.println("communication initialized");
    }

    @Override
    public void run() {
    }

    @Override
    protected boolean initialize() {
        try {
            // System.setProperty("java.rmi.server.hostname", "192.168.1.2");
            createStubAndBind();
            // registry = LocateRegistry.getRegistry();
            // server = (RmiSendToClientRemoteInterface) registry
            // .lookup("RmiToClientRemoteInterface");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void sendMessage(String mensagem) {
        try {
            server.messageToClient(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void messageToServer(String message) throws RemoteException {
        // this.callbackFunctionMessageReceived.accept(message);
        System.out.println("client said: " + message);
    }

    public void createStubAndBind() throws RemoteException {

        RmiSendToServerRemoteInterface stub = (RmiSendToServerRemoteInterface) UnicastRemoteObject
                .exportObject((RmiSendToServerRemoteInterface) this, 0);
        System.out.println("run");
        registry = LocateRegistry.createRegistry(1099);
        registry.rebind("RmiToServerRemoteInterface", stub);
    }
}
