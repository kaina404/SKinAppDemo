package xu.myapplication.skin;

import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lovexujh on 2017/7/26
 */

public  class SkinActivity extends AppCompatActivity {

    protected LayoutInflaterFactoryImpl layoutInflaterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutInflaterFactory = new LayoutInflaterFactoryImpl();
        LayoutInflaterCompat.setFactory(getLayoutInflater(), layoutInflaterFactory);
        super.onCreate(savedInstanceState);
    }

}
