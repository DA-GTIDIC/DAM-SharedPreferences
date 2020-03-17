package cat.udl.tidic.amd.dam_sharedpreferences;

import android.app.Application;

import cat.udl.tidic.amd.dam_sharedpreferences.preferences.PreferencesProvider;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesProvider.init(this);
    }
}
