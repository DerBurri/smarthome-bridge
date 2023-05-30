package coreplugins.control;

import coreplugins.control.model.IMessage;

public interface IReceiver {

    public void receive(IMessage message);
}
