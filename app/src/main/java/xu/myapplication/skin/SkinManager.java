package xu.myapplication.skin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import xu.myapplication.Tool.Lo;
import xu.myapplication.Tool.Tools;

/**
 * Created by lovexujh on 2017/7/26
 */
public class SkinManager {

    private static SkinManager ourInstance = new SkinManager();
    private Context context;
    private Resources skinRes;
    private String skinPackageName;
    private boolean loadSkinResult;

    public static SkinManager getInstance() {
        return ourInstance;
    }

    private SkinManager() {
    }

    public void initContext(Context context) {
        this.context = context.getApplicationContext();
    }


    public boolean loadSkin(String skinPath) {
        loadSkinResult = false;
        //------------拿到skinPackageName----------
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES);
        if (packageArchiveInfo == null) {
            Tools.showDebugToast("没有" + skinPath + "皮肤");
        } else {
            skinPackageName = packageArchiveInfo.packageName;
            //----------拿到skin中的Resource对象----------
            AssetManager assets = null;
            try {
                assets = AssetManager.class.newInstance();
                Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
                addAssetPath.invoke(assets, skinPath);
                loadSkinResult = true;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            skinRes = new Resources(assets, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        }
        return loadSkinResult;
    }


    /**
     * @param resId
     * @return
     */
    public int getColor(int resId) {
        int defaultColor = context.getResources().getColor(resId);
        if (skinRes == null) {
            return defaultColor;
        }
        //通过本地APP中的resId拿到本app对应的资源名称，然后再skin apk中找到该资源名称， 在根据skin中的资源名称 拿到对应的资源值
        String resourceName = context.getResources().getResourceName(resId);
        //String name, String defType, String skinPackageName  拿到skin包中的resId
        int skinResId = skinRes.getIdentifier(resourceName.substring(resourceName.indexOf("/") + 1), SkinConstant.COLOR, skinPackageName);
        if (skinResId == 0) {//说明在skin皮肤中没有找到对应的resId，则返回原本的resId
            Lo.logD(this, "在skin皮肤中没有找到对应的resId，则返回原本的resId");
            return defaultColor;
        }
        return skinRes.getColor(skinResId);
    }

    public Drawable getDrawable(int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        if (skinRes == null) {
            return drawable;
        }

        String resourceName = context.getResources().getResourceName(resId);
        //String name, String defType, String skinPackageName  拿到skin包中的resId
        int skinResId = skinRes.getIdentifier(resourceName.substring(resourceName.indexOf("/") + 1), SkinConstant.DRAWABLE, skinPackageName);
        if (skinResId == 0) {//说明在skin皮肤中没有找到对应的resId，则返回原本的resId
            return drawable;
        }

        return skinRes.getDrawable(skinResId);
    }

    public Drawable getMipmap(int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        if (skinRes == null) {
            return drawable;
        }

        String resourceName = context.getResources().getResourceName(resId);
        //String name, String defType, String skinPackageName  拿到skin包中的resId
        int skinResId = skinRes.getIdentifier(resourceName.substring(resourceName.indexOf("/") + 1), SkinConstant.MIPMAP, skinPackageName);
        if (skinResId == 0) {//说明在skin皮肤中没有找到对应的resId，则返回原本的resId
            return drawable;
        }

        return skinRes.getDrawable(skinResId);
    }

    public Bitmap getBitmap(int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        if (skinRes == null) {
            return bitmap;
        }
        Bitmap skinBitmap = BitmapFactory.decodeResource(skinRes, resId);
        if (skinBitmap == null) {
            return bitmap;
        }
        return skinBitmap;
    }

    /**
     * may be null
     *
     * @return
     */
    public Resources getSkinRes() {
        return skinRes;
    }

    public int getSkinLayoutResId(int resId, String type) {
        if (skinRes == null) {
            return resId;
        }
        String resourceName = context.getResources().getResourceName(resId);
        int skinResId = skinRes.getIdentifier(resourceName.substring(resourceName.indexOf("/") + 1), type, skinPackageName);
        if (skinResId == 0) {//说明在skin皮肤中没有找到对应的resId，则返回原本的resId
            return resId;
        }
        return skinResId;
    }

    public Context getContext() {
        return context;
    }


    public boolean isLoadSkinSuccess() {
        return loadSkinResult;
    }
}
