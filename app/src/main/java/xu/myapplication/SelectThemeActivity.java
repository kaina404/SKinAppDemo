package xu.myapplication;

import android.os.Bundle;
import android.view.View;

import xu.myapplication.Tool.SkinTool;
import xu.myapplication.skin.SkinActivity;

public class SelectThemeActivity extends SkinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);
    }


    public void onClick(View view){

        switch (view.getId()){
            case R.id.black:
                SkinTool.setSkin("skin-default");
                SkinTool.loadSkinAndChangeTheme(layoutInflaterFactory);
                break;
            case R.id.colorPrimaryDark:
                SkinTool.setSkin("skin-colorprimarydark");
                SkinTool.loadSkinAndChangeTheme(layoutInflaterFactory);
                break;

        }
    }
}
