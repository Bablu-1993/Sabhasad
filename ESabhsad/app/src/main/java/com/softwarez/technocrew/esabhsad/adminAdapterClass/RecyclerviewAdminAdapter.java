package com.softwarez.technocrew.esabhsad.adminAdapterClass;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarez.technocrew.esabhsad.R;
import com.softwarez.technocrew.esabhsad.adminActivityClass.NivaranActivity;
import com.softwarez.technocrew.esabhsad.adminActivityClass.SahyogiActivity;
import com.softwarez.technocrew.esabhsad.adminActivityClass.SamsyeActivity;
import com.softwarez.technocrew.esabhsad.model.AdminModel;

import java.util.List;

public class RecyclerviewAdminAdapter extends RecyclerView.Adapter<RecyclerviewAdminAdapter.ViewHolder> {
  private Context context;
  private List<AdminModel> adminModelList;

    public RecyclerviewAdminAdapter(Context context, List<AdminModel> adminModelList) {
        this.context = context;
        this.adminModelList = adminModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_admin_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdminModel model=adminModelList.get(position);
         holder.imageView.setImageResource(model.getImage());
         holder.rightArrow.setImageResource(model.getArrow());
         holder.txtTitle.setText(model.getTitle());
         holder.layoutCardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                 if (position==0){
                     Intent i1=new Intent(holder.itemView.getContext(), SamsyeActivity.class);
                     i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     holder.itemView.getContext().startActivity(i1);
                 } else if (position==1){
                     Intent i2=new Intent(holder.itemView.getContext(), NivaranActivity.class);
                     i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     holder.itemView.getContext().startActivity(i2);
                 } else if (position==2){
                     Intent i3=new Intent(holder.itemView.getContext(), SahyogiActivity.class);
                     i3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     holder.itemView.getContext().startActivity(i3);

                 }
             }
         });
    }

    @Override
    public int getItemCount() {
        return adminModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private TextView txtTitle;
       private ImageView imageView,rightArrow;
       LinearLayout layoutCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            imageView=itemView.findViewById(R.id.txtImageView);
            rightArrow=itemView.findViewById(R.id.imageView2);
            layoutCardView=itemView.findViewById(R.id.layoutCardView);

        }
    }
}
