package xu.myapplication;

import android.app.Application;

/**
 * Created by lovexujh on 2017/9/2
 */

public class BaseApplication extends Application {

    private static  BaseApplication instance ;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApplication getInstance(){
        return instance;
    }
}
