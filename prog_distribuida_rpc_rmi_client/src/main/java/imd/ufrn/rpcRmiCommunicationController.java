package imd.ufrn;

import java.util.function.Consumer;

import imd.ufrn.interfaces.ICommunicationWithServerController;

public class rpcRmiCommunicationController implements ICommunicationWithServerController {

    @Override
    public boolean initialize(Consumer<String> callbackFunctionMessageRecieved) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    @Override
    public void sendMessage(String mensagem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

}
