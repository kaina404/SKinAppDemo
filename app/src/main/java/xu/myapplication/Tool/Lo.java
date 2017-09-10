package xu.myapplication.Tool;

import android.util.Log;

import xu.myapplication.AppConfig;

/**
 * Created by lovexujh on 2017/9/2
 */

public class Lo {


    public static void logD(Object obj, String log){
        if(AppConfig.DEBUG){
            Log.d(obj.getClass().getSimpleName(), log);
        }
    }

    public static void logE(Object obj, String log){
        if(AppConfig.DEBUG){
            Log.e(obj.getClass().getSimpleName(), log);
        }
    }

}
