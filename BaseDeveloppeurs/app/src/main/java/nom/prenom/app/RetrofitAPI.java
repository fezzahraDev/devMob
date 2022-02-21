package nom.prenom.app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {

    public static final String ENDPOINT = "http://android.busin.fr/eilco.json";

    @GET("Eleves")
    Call<List<Eleve>> getListEleves();

    @GET("EleveByName")
    Call<List<Eleve>> getEleveByName();

}

