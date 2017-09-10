package xu.myapplication.skin.attr;

import android.view.View;
import android.widget.TextView;

import xu.myapplication.skin.SkinConstant;
import xu.myapplication.skin.SkinManager;

/**
 * Created by lovexujh on 2017/7/27
 */
public class TextColorViewAttrs extends ViewAttrs {
    @Override
    public void changeTheme(View view) {
        if (view instanceof TextView) {
            //替换textColor
            if (SkinConstant.COLOR.equalsIgnoreCase(resourceTypeName)){
                ((TextView) view).setTextColor(SkinManager.getInstance().getColor(resId));
            }
        }
    }
}
