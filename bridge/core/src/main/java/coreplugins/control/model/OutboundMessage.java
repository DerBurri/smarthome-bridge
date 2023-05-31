package coreplugins.control.model;

public class OutboundMessage extends Message {

    public final MessageType messageType;

    public OutboundMessage() {
        this.messageType = MessageType.OUTBOUND;
    }
}
