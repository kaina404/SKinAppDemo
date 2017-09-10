package xu.myapplication.skin;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import xu.myapplication.Tool.Tools;
import xu.myapplication.skin.attr.ViewAttrs;

/**
 * Created by lovexujh on 2017/7/26
 */

public class LayoutInflaterFactoryImpl implements LayoutInflaterFactory {

    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."
    };
    private List<SkinView> skinViews = new ArrayList<>();//需要换肤的所有view的集合

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        View view = null;

        if (name.contains(".")) {//自定义控件
            view = createView(name, context, attrs);
        } else {//系统控件
            for (int i = 0; i < sClassPrefixList.length; i++) {
                view = createView(sClassPrefixList[i] + name, context, attrs);
                if (view != null) {//不为空说明系统中包含这个包名下的view
                    break;
                }
            }
        }
        if (view != null) {
            saveViewAttrs(view, context, attrs);
        }
        return view;
    }

    /**
     * 解析本地view的属性，并保存该view
     * 解析：view的属性名称；view的属性值；view的background；view的resId
     *
     * @param view
     * @param context
     * @param attrs
     */
    private void saveViewAttrs(View view, Context context, AttributeSet attrs) {
        //将view的每一种属性 以及对应的值放在list中
        ArrayList<ViewAttrs> viewAttrses = new ArrayList<>();
        boolean skinEnable = true;
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attributeName = attrs.getAttributeName(i);//background或者textColor
            String attributeValue = attrs.getAttributeValue(i);//拿到view的在R文件中的id。类似于@2131361811
            /*if(SkinConstant.BACKGROUND.equalsIgnoreCase(attributeName) || SkinConstant.TEXT_COLOR.equalsIgnoreCase(attributeName)){
                int resId = Integer.parseInt(attributeValue.substring(1));//截取@2131361811 ，拿到实际的在R文件中的值
                String resourceTypeName = context.getResources().getResourceTypeName(resId);//background的mipmap或者drawable或者color等
                String resourceEntryName = context.getResources().getResourceEntryName(resId); //mipmap、drawable、color对应的值
                ViewAttrs viewAttrs = new ViewAttrs(attributeName, resId, resourceEntryName, resourceTypeName);
                viewAttrses.add(viewAttrs);
            }*/
            if("skin".equalsIgnoreCase(attributeName)){
                //默认对所有控件换肤，但是如果属性中包含有[skin:skin=""],则表示不对该控件做换肤处理
                skinEnable = false;
                break;
            }
            if(!ViewAttrsFactory.contains(attributeName) || attributeValue.indexOf("@") < 0){
                continue;
            }

            int resId = Integer.parseInt(attributeValue.substring(1));//截取@2131361811 ，拿到实际的在R文件中的值
            String resourceTypeName = context.getResources().getResourceTypeName(resId);//background的mipmap或者drawable或者color等
            String resourceEntryName = context.getResources().getResourceEntryName(resId); //mipmap、drawable、color对应的值
            ViewAttrs viewAttrs = ViewAttrsFactory.createViewAttrs(attributeName, resId, resourceEntryName, resourceTypeName);
            if (viewAttrs != null) {
                viewAttrses.add(viewAttrs);

            }
        }
        if (skinEnable && viewAttrses.size() > 0) {
            //保存需要换肤的view以及对应的属性
            SkinView skinView = new SkinView(view, viewAttrses);
            skinViews.add(skinView);
            if(SkinManager.getInstance().isLoadSkinSuccess()){
                skinView.changeTheme();
            }
        }
    }

    public void changeTheme() {
        if(SkinManager.getInstance().getSkinRes() == null){
            Tools.showDebugToast("没有皮肤");
            return;
        }
        for (int i = 0; i < skinViews.size(); i++) {
            skinViews.get(i).changeTheme();
        }
    }


    /**
     * 拿到该view的实例
     *
     * @param name
     * @param context
     * @param attrs
     * @return
     */
    private View createView(String name, Context context, AttributeSet attrs) {

        try {
            Class<? extends View> aClass = (Class<? extends View>) context.getClassLoader().loadClass(name);
            Constructor<? extends View> constructor = aClass.getConstructor(new Class[]{Context.class, AttributeSet.class});
            return constructor.newInstance(context, attrs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



}
