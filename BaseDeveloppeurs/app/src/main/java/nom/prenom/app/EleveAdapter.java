package nom.prenom.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class EleveAdapter extends RecyclerView.Adapter<EleveAdapter.ViewHolder>{
    private Context context;
    public static final String BASE_URL = "http://android.busin.fr/eilco.json";
    private final List<Eleve> eleves;

    public EleveAdapter(List<Eleve> lEleves){
        eleves = lEleves;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View eleveview = inflater.inflate(R.layout.eleve_item,parent, false);
        return new ViewHolder(eleveview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Eleve eleve = eleves.get(position);
        ImageView image = holder.image;
        Glide.with(holder.itemView).load(eleve.getPhoto()).into(image);

        TextView nom = holder.nom;
        nom.setText(eleve.getNom() + " " + eleve.getPrenom());

        TextView email = holder.email;
        email.setText(eleve.getEmail());

        TextView telephone = holder.telephone;
        telephone.setText(eleve.getTelephone());

        ImageView nextB = holder.nextBtn;
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EleveDetails.class);
                intent.putExtra("eleve",eleve);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eleves.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView nom;
        public TextView email;
        public TextView telephone;
        public ImageView nextBtn;

        public ViewHolder(View itemView){
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.eleveImage);
            nom = (TextView) itemView.findViewById(R.id.nomEleve);
            email = (TextView) itemView.findViewById(R.id.emailEleve);
            telephone = (TextView) itemView.findViewById(R.id.telephoneEleve);
            nextBtn = itemView.findViewById(R.id.nextImage);
        }
    }
}

