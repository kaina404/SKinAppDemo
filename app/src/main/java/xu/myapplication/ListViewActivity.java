package xu.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import xu.myapplication.skin.SkinActivity;

public class ListViewActivity extends SkinActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(ListViewActivity.this).inflate(R.layout.list_view_item, null);
            }
            return convertView;
        }
    }

}
