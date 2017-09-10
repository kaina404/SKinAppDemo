package xu.myapplication.Tool;

import android.content.Context;
import android.content.SharedPreferences;

import xu.myapplication.BaseApplication;

/**
 * Created by lovexujh on 2017/9/2
 */

public class Sp {

    private SharedPreferences sp;

    public Sp() {
        sp = BaseApplication.getInstance().getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
    }

    public String getString(String key, String defaultValue) {
        String value = sp.getString(key, defaultValue);
        return value;
    }

    public void setString(String key, String value){
        sp.edit().putString(key, value).commit();
    }
}
