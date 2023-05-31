import pluginmanager.IPlugin;
import start.ICore;

public class MinecraftAdapter implements IPlugin {
    @Override
    public void load(ICore core) {
        System.out.println("Minecraft Adapter starting");
    }

    @Override
    public void unload() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDependencies() {
        return null;
    }
}
