package nom.prenom.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rvEleves;
    private EleveAdapter adapter;
    private View view;
    private Parcelable recyclerViewState;
    private int page = 1;

    private void retro() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://android.busin.fr/eilco.json")
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<List<Eleve>> call = retrofitAPI.getListEleves();
        call.enqueue(new Callback<List<Eleve>>() {
            @Override
            public void onResponse(Call<List<Eleve>> call, Response<List<Eleve>> response) {
                if (response.isSuccessful()) {
                    adapter = new EleveAdapter(response.body());
                    rvEleves.setAdapter(adapter);
                    rvEleves.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<List<Eleve>> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.search_toolbar).setVisibility(View.INVISIBLE);
        //rvEleves = (RecyclerView) view.findViewById(R.id.rvEleves);
        //retro();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Android:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Application développée par");
                alertDialog.setMessage("FatimaEzzahra CHOUAI");

                alertDialog.show();
                return true;

            case R.id.Search:

                if (findViewById(R.id.search_toolbar).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.search_toolbar).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.search_toolbar).setVisibility(View.VISIBLE);
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    
}