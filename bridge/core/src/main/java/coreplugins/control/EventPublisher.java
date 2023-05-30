package coreplugins.control;

import start.ICoreFeature;

import java.util.List;

public class EventPublisher implements IPublisher, ICoreFeature {

    List<IReceiver> recipients;

    @Override
    public void registerRecipient(IReceiver recipient) {

    }

    @Override
    public void unregisterRecipient(IReceiver recipient) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void execute() {

    }
}

