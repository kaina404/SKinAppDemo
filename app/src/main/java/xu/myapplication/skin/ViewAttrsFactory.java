package xu.myapplication.skin;

import java.util.HashMap;
import java.util.Map;

import xu.myapplication.skin.attr.BackgroundViewAttrs;
import xu.myapplication.skin.attr.NavigationMenuAttrs;
import xu.myapplication.skin.attr.TextColorViewAttrs;
import xu.myapplication.skin.attr.ViewAttrs;

/**
 * Created by lovexujh on 2017/7/27
 */

public class ViewAttrsFactory {

    public static Map<String, ViewAttrs> viewAttrsMap = new HashMap<>();

    static {//添加支持换肤的属性
        viewAttrsMap.put(SkinConstant.TEXT_COLOR, new TextColorViewAttrs());
        viewAttrsMap.put(SkinConstant.BACKGROUND, new BackgroundViewAttrs());
        viewAttrsMap.put(SkinConstant.SRC, new BackgroundViewAttrs());
        viewAttrsMap.put(SkinConstant.MENU, new NavigationMenuAttrs());
    }

    public static ViewAttrs createViewAttrs(String attributeName, int resId, String resourceEntryName, String resourceTypeName) {
        if (viewAttrsMap.get(attributeName) != null) {
            ViewAttrs viewAttrs;
            if ((viewAttrs = viewAttrsMap.get(attributeName).clone()) != null) {

                viewAttrs.attributeName = attributeName;
                viewAttrs.resId = resId;
                viewAttrs.resourceEntryName = resourceEntryName;
                viewAttrs.resourceTypeName = resourceTypeName;
                return viewAttrs;
            }
        }
        return null;
    }


    public static boolean contains(String attributeName) {
        return attributeName != null && viewAttrsMap.get(attributeName) != null;
    }

}
