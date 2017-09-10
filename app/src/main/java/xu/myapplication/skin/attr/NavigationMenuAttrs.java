package xu.myapplication.skin.attr;

import android.content.res.ColorStateList;
import android.support.design.widget.NavigationView;
import android.view.View;

import xu.myapplication.R;
import xu.myapplication.skin.SkinManager;

/**
 * Created by lovexujh on 2017/7/31
 */

public class NavigationMenuAttrs extends ViewAttrs {

    @Override
    public void changeTheme(View view) {
        if(view instanceof NavigationView){

            NavigationView nv = (NavigationView) view;
            int selectColor = SkinManager.getInstance().getColor(R.color.nav_menu_select_color);
            int unSelectColor = SkinManager.getInstance().getColor(R.color.nav_menu_un_select_color);
            nv.setItemTextColor(createSelector(selectColor, unSelectColor));
            nv.setItemIconTintList(createSelector(selectColor, unSelectColor));
        }
    }

    private ColorStateList createSelector(int selectColor, int unSelector) {
        int statePressed = android.R.attr.state_checked;
        int stateChecked = android.R.attr.state_checked;
        int[][] state = {{statePressed}, {-statePressed}, {stateChecked}, {-stateChecked}};
        int[] colors = {selectColor, unSelector, selectColor, unSelector};
        ColorStateList colorStateList = new ColorStateList(state, colors);
        return colorStateList;
    }


}
