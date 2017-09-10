package xu.myapplication.skin;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import xu.myapplication.skin.attr.ViewAttrs;

/**
 * Created by lovexujh on 2017/7/26
 */
public class SkinView {

    private View view;

    public ArrayList<ViewAttrs> getViewAttrses() {
        return viewAttrses;
    }

    public void setViewAttrses(ArrayList<ViewAttrs> viewAttrses) {
        this.viewAttrses = viewAttrses;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private ArrayList<ViewAttrs> viewAttrses;

    public SkinView(View view, ArrayList<ViewAttrs> viewAttrses) {
        this.view = view;
        this.viewAttrses = viewAttrses;
    }

    //android:textColor = "@color/red_color"
    //android:background = "@mipmap/pic1"
    //android:background = "@drawable/selector"
    //android:background = "@color/blue_color"


    public void changeTheme() {

        for (ViewAttrs viewAttrs : viewAttrses) {
            viewAttrs.changeTheme(view);
        }
/*
        for (int i = 0; i < viewAttrses.size(); i++) {
            ViewAttrs viewAttrs = viewAttrses.get(i);
            if (SkinConstant.TEXT_COLOR.equalsIgnoreCase(viewAttrs.attributeName)) {
                if (view instanceof TextView) {
                    //替换textColor
                    if (SkinConstant.COLOR.equalsIgnoreCase(viewAttrs.resourceTypeName)) {
                        ((TextView) view).setTextColor(SkinManager.getInstance().getColor(viewAttrs.resId));
                    }
                }
            } else if (SkinConstant.BACKGROUND.equalsIgnoreCase(viewAttrs.attributeName)) {

                if (SkinConstant.DRAWABLE.equalsIgnoreCase(viewAttrs.resourceTypeName)) {

                    view.setBackgroundDrawable(SkinManager.getInstance().getDrawable(viewAttrs.resId));
                } else if (SkinConstant.COLOR.equalsIgnoreCase(viewAttrs.resourceTypeName)) {

                    view.setBackgroundColor(SkinManager.getInstance().getColor(viewAttrs.resId));
                } else if (SkinConstant.MIPMAP.equalsIgnoreCase(viewAttrs.resourceTypeName)) {

                }
            }
        }*/
    }
}
