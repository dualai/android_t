package son.rvview;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import son.dualai.R;
import son.dualai.adt.MainActivity;

/**
 * Created on 2019/3/8.
 */
public class RvviewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvview);

        RecyclerView recyclerView = findViewById(R.id.rvView);
        recyclerView.setAdapter(new MyAdapter(RvviewActivity.this, 30));
    }

    private final class MyAdapter implements RecyclerView.Adapter {

        LayoutInflater inflater;
        private int height;
        private int count;

        public MyAdapter(Context context, int count) {
            this.inflater = LayoutInflater.from(context);
            this.count = count;
            Resources resources = context.getResources();
            height = resources.getDimensionPixelOffset(R.dimen.table_height);
        }

        @Override
        public int getItemViewType(int row) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public View onCreateViewHolder(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.cell_table1, parent, false);
            TextView textView = convertView.findViewById(R.id.text1);
            textView.setText("第 " + position + "行");
            return convertView;
        }

        @Override
        public View onBinderViewHolder(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView)convertView.findViewById(R.id.text1);
            textView.setText("第 " + position+" 行");
            return convertView;
        }

        @Override
        public int getHeight(int index) {
            return height;
        }

        @Override
        public int getCount() {
            return count;
        }
    }
}
