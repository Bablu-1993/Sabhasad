package com.softwarez.technocrew.esabhsad.adminAdapterClass;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softwarez.technocrew.esabhsad.R;
import com.softwarez.technocrew.esabhsad.model.DashboardModel;
import java.util.List;

public class RecyclerviewAdminDashAdapter extends RecyclerView.Adapter<RecyclerviewAdminDashAdapter.ViewHolder> {
    List<DashboardModel> modelList;
    Context context;

    public RecyclerviewAdminDashAdapter(List<DashboardModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_dashboard_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DashboardModel model=modelList.get(position);
       holder.txtImageView.setImageResource(model.getImages());
      holder.txtName.setText(model.getTitle());
      holder.txtNumber.setText(model.getNumber());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView txtImageView;
        TextView txtName, txtNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtImageView=itemView.findViewById(R.id.txtImageView);
            txtName=itemView.findViewById(R.id.txtdash_name);
            txtNumber=itemView.findViewById(R.id.txtNumber);
        }
    }
}
