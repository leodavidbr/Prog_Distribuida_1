package imd.ufrn;

import java.util.function.Consumer;

import imd.ufrn.interfaces.BaseCommunicationWithServerController;

public class ServerCommunicationRmiImpl extends BaseCommunicationWithServerController {

    public ServerCommunicationRmiImpl(Consumer<String> callbackFunctionMessageRecieved) {
        super(callbackFunctionMessageRecieved);
    }

    @Override
    public void run() {
    }

    @Override
    protected boolean initialize() {

        return true;
    }

    @Override
    public void sendMessage(String mensagem) {
    }

}
