package xu.myapplication.Tool;

import android.widget.Toast;

import xu.myapplication.AppConfig;
import xu.myapplication.BaseApplication;

/**
 * Created by lovexujh on 2017/9/2
 */

public class Tools {

    public static void showDebugToast(String msg) {
        if (AppConfig.DEBUG) {
            Toast.makeText(BaseApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

}
