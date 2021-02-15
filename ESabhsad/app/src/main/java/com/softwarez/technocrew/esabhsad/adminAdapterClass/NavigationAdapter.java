package com.softwarez.technocrew.esabhsad.adminAdapterClass;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.softwarez.technocrew.esabhsad.R;

public class NavigationAdapter extends ArrayAdapter {
    private Context context;
    private String[] titles;
    private int[] icon;

    public NavigationAdapter(@NonNull Context context, String[] titles, int[] icon) {
        super(context, R.layout.navigation_row,titles);
        this.context = context;
        this.titles = titles;
        this.icon = icon;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.navigation_row,parent,false);
        ImageView imageView=rowView.findViewById(R.id.icon);
        TextView textView=rowView.findViewById(R.id.name);
        imageView.setImageResource(icon[position]);
        textView.setText(titles[position]);
        return rowView;
    }
}

