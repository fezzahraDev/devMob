package nom.prenom.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EleveDetails extends AppCompatActivity {

    Eleve eleve;
    String nom;
    String telephone;
    String adresse;
    String linkedin_url;
    String photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_eleve);

        Intent intent = getIntent();

        if (getIntent().getExtras() != null){
            eleve = (Eleve) getIntent().getSerializableExtra("eleve");

            photo = eleve.getPhoto();
            ImageView image = (ImageView) findViewById(R.id.photoEleveD);
            Glide.with(this).load(photo).into(image);

            nom = eleve.getPrenom() + " " + eleve.getNom();
            TextView nomTV = (TextView)findViewById(R.id.nomEleveD);
            nomTV.setText(nom);

            telephone = eleve.getTelephone();
            TextView telephoneTV = (TextView)findViewById(R.id.telephoneEleveD);
            telephoneTV.setText(telephone);

            Button callB = (Button)findViewById(R.id.callBTN);
            callB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+telephone));
                    startActivity(intent);
                }
            });

            adresse = eleve.getAdresse();
            TextView adresseTV = (TextView)findViewById(R.id.adresseEleveD);
            adresseTV.setText(adresse);

            Button mapsB = (Button)findViewById(R.id.mapsBTN);
            mapsB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(adresse));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });

            linkedin_url = eleve.getLinkedin_url();
            TextView linkedinTV = (TextView)findViewById(R.id.linkedinEleveD);
            linkedinTV.setText("Profil linkedin");
            linkedinTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(linkedin_url));
                    startActivity(intent);
                }
            });

        }
    }
}
