package xu.myapplication.skin.attr;

import android.view.View;
import android.widget.ImageView;

import xu.myapplication.skin.SkinConstant;
import xu.myapplication.skin.SkinManager;

/**
 * Created by lovexujh on 2017/7/27
 */
public class BackgroundViewAttrs extends ViewAttrs {
    @Override
    public void changeTheme(View view) {
        if (SkinConstant.DRAWABLE.equalsIgnoreCase(resourceTypeName)) {

            view.setBackgroundDrawable(SkinManager.getInstance().getDrawable(resId));

        } else if (SkinConstant.COLOR.equalsIgnoreCase(resourceTypeName)) {

            view.setBackgroundColor(SkinManager.getInstance().getColor(resId));
        } else if (SkinConstant.MIPMAP.equalsIgnoreCase(resourceTypeName)) {
            if(view instanceof ImageView){
                ((ImageView)view).setImageBitmap(SkinManager.getInstance().getBitmap(resId));
            }
        }
    }
}
