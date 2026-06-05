package dev.hybridlabs.albom.platform;

import dev.hybridlabs.albom.platform.services.ClientPlatformHelper;
import static dev.hybridlabs.albom.platform.Services.load;

public class ClientServices {
    public static final ClientPlatformHelper PLATFORM = load(ClientPlatformHelper.class);
}
