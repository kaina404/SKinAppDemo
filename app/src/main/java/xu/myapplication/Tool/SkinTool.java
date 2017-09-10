package xu.myapplication.Tool;

import android.os.Environment;

import xu.myapplication.skin.LayoutInflaterFactoryImpl;
import xu.myapplication.skin.SkinManager;

/**
 * Created by lovexujh on 2017/9/2
 */

public class SkinTool {

    private static final String TAG_SKIN = "skin";
    private static final String TAG_SKIN_COLOR_PRIMARY_DARK = "skin-colorprimarydark";

    /**
     * skin-colorPrimaryDark
     * skin-default
     *
     * @param skinName
     */

    public static void setSkin(String skinName) {
        Sp sp = new Sp();
        sp.setString(TAG_SKIN, skinName);
    }


    public static void loadSkinAndChangeTheme(LayoutInflaterFactoryImpl layoutInflaterFactory) {
        Sp sp = new Sp();
        boolean result = SkinManager.getInstance().loadSkin(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + sp.getString(TAG_SKIN, TAG_SKIN_COLOR_PRIMARY_DARK));
        if (result) {
            layoutInflaterFactory.changeTheme();
        }
    }

    /**
     * 获取当前对象保存的皮肤名称
     * @param obj
     * @return
     */
    public static String getObjectSkinName(Object obj){
        Sp sp = new Sp();
        return sp.getString(obj.getClass().getSimpleName(), "");
    }

    /**
     * 保存当前页面的皮肤名称
     * @param obj
     * @param skinName
     */
    public static void setObjectSkinName(Object obj, String skinName){
        Sp sp = new Sp();
        sp.setString(obj.getClass().getSimpleName(), skinName);
    }


    /**
     * 根据当前页面保存的皮肤名称与当前sp中保存的皮肤名称想比较
     * 如果不同，则换肤。
     * @param o
     * @param layoutInflaterFactory
     */
    public static void tryChangeTheme(Object o, LayoutInflaterFactoryImpl layoutInflaterFactory){
        Sp sp = new Sp();
        String curSkinName = sp.getString(TAG_SKIN, "");
        if(!curSkinName.equals(getObjectSkinName(o))){
            loadSkinAndChangeTheme(layoutInflaterFactory);
            setObjectSkinName(o, curSkinName);
        }
    }

}
