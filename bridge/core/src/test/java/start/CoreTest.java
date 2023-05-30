package start;

import coreplugins.config.IConfiguration;
import coreplugins.control.IPublisher;
import coreplugins.control.IReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pluginmanager.IPlugin;
import pluginmanager.IPluginFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoreTest {

    @Mock
    private IConfiguration configuration;

    @Mock
    private IPluginFactory pluginFactory;

    @Mock
    private IPublisher publisher;

    @Mock
    private IReceiver receiver;

    private Core core;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        List<IPlugin> plugins = new ArrayList<>();
        core = new Core(plugins, pluginFactory, configuration,
                IAppState.ApplicationState.STARTING, publisher, receiver);
    }

    @Test
    void getConfiguration_ReturnsCorrectInstance() {
        IConfiguration returnedConfiguration = core.getConfiguration();
        assertEquals(configuration, returnedConfiguration);
    }

    @Test
    void run() {
        // Arrange
        CountDownLatch latch = new CountDownLatch(1);

        Thread thread = new Thread(() -> {
            core.run();
            latch.countDown();
        });
        thread.start();

        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () ->
        {
            core.setState(IAppState.ApplicationState.STOPPED);

            latch.await();
        });

        Assertions.assertEquals(IAppState.ApplicationState.STOPPED, core.getState());

    }

    @Test
    void setState_ChangesAppState() {
        core.setState(IAppState.ApplicationState.PAUSED);
        IAppState.ApplicationState currentState = core.getState();
        assertEquals(IAppState.ApplicationState.PAUSED, currentState);
    }

    @Test
    void getState() {
        IAppState.ApplicationState expectedState = IAppState.ApplicationState.RUNNING;
        core.setState(expectedState);
        IAppState.ApplicationState actualState = core.getState();
        assertEquals(expectedState, actualState);
    }

    @Test
    void getCore() {
        ICore expectedcore = core.getCore();
        assertEquals(expectedcore, core);
    }

    @Test
    void registerPlugin_CheckPluginList() {
        core.registerFeature();

    }

    @Test
    void unregisterPlugin() {
    }

    @Test
    void notifyPlugins() {
    }

}