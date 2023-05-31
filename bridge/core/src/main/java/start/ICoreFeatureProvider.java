package start;

import java.util.List;

public interface ICoreFeatureProvider {

    void registerFeature(ICoreFeature feature);

    void unregisterFeature(ICoreFeature feature);

    List<ICoreFeature> getCoreFeatures();
}
