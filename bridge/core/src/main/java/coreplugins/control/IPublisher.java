package coreplugins.control;

public interface IPublisher {

    void registerRecipient(IReceiver recipient);

    void unregisterRecipient(IReceiver recipient);

}
