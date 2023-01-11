package edu.upc.eetac.dsa.minimodos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.eetac.dsa.minimodos.R;
import edu.upc.eetac.dsa.minimodos.domain.User;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolder> {
    private List<User> listUsers;
    final AdapterDatos.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(User user);
    }

    public AdapterDatos(List<User> listUsers,AdapterDatos.OnItemClickListener listener){
        this.listUsers=listUsers;
        this.listener=listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView avatar;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.name);
            avatar=itemView.findViewById(R.id.avatar);
        }
        void bindData(final User user){
            name.setText(user.getLogin());
            Picasso.get().load(user.getAvatar_url()).into(avatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user);
                }
            });
        }
    }
    @NonNull
    @Override
    public AdapterDatos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(listUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }
}
