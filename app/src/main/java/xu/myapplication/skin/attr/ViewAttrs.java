package xu.myapplication.skin.attr;

import android.view.View;

/**
 * Created by lovexujh on 2017/7/26
 */
public abstract class ViewAttrs implements Cloneable{


    public String attributeName, resourceEntryName, resourceTypeName;
    public int resId;

    public ViewAttrs(){

    }

    public abstract void changeTheme(View view);

    @Override
    public ViewAttrs clone() {
        try {
            return (ViewAttrs)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
