package control;

import model.Device;

import java.util.List;
import java.util.Observer;
import java.util.concurrent.Flow;

public class EventPublisher implements Flow.Publisher {

    List<Device> registeredDevices;

    @Override
    public void subscribe(Flow.Subscriber subscriber) {

        }
    }

