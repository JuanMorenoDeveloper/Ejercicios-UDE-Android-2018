package uy.edu.ude.practicows.usecases.caller;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import uy.edu.ude.practicows.entity.Response;

public class RetrofitHttpCaller implements Caller {

  private static final String TAG = RetrofitHttpCaller.class.getSimpleName();

  public interface ServiceApi {

    @GET("api/{id}")
    Call<Response> queryApi(@Path("id") String id);
  }

  @Override
  public Response callHttpServer(String id) {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(Response.class, new ApiDeserializer())
            .create();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://gturnquist-quoters.cfapps.io/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
    ServiceApi service = retrofit.create(ServiceApi.class);
    Call<Response> responseService = service.queryApi(id);
    try {
      return responseService.execute().body();
    } catch (IOException e) {
      Log.e(TAG, String.format("Error consultado al servicio con id=%s", id), e);
    }
    return null;
  }

  class ApiDeserializer implements JsonDeserializer<Response> {

    @Override
    public Response deserialize(JsonElement element, Type typeClass,
        JsonDeserializationContext context)
        throws JsonParseException {
      JsonObject jsonObject = element.getAsJsonObject();
      JsonObject value = jsonObject.getAsJsonObject("value");
      String type = jsonObject.get("type").getAsString();
      int id = value.get("id").getAsInt();
      String quote = value.get("quote").getAsString();
      return new Response(id, type, quote);
    }
  }
}
