package pluginmanager;

public class PluginFactory {
    public static Plugin createPlugin(String pluginName) {
        Plugin plugin = null;
        try {
            plugin = (Plugin) Class.forName(pluginName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plugin;
    }
}

