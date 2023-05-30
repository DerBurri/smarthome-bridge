package start;

import java.util.List;

public interface ICoreFeatureProvider {

    void registerFeature();

    void unregisterFeature();

    List<ICoreFeature> getCoreFeatures();
}
