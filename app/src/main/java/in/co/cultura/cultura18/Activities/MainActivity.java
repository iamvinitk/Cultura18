package in.co.cultura.cultura18.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import in.co.cultura.cultura18.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);

        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 21) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        RecyclerView recyclerView = findViewById(R.id.am_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        CategoryAdapter adapter = new CategoryAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context context;
        private String[] categories = new String[]{
                "Events", "Sponsors", "Schedule", "Location", "About"
        };

        CategoryAdapter(Context context) {
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_home, parent, false));
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.textView.setText(categories[position]);
        }

        @Override
        public int getItemCount() {
            return categories.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private ImageView imageView;

            public ViewHolder(View v) {
                super(v);
                textView = v.findViewById(R.id.rh_home_name);
                imageView = v.findViewById(R.id.rh_home_image);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (getAdapterPosition()) {
                            case 3:
                                startActivity(new Intent(context, Location.class));
                                break;
                            case 4:
                                startActivity(new Intent(context, About.class));
                                break;
                        }

                    }
                });
            }
        }
    }

}
