package uy.edu.ude.practicows.usecases.caller;

import android.util.Log;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONException;
import org.json.JSONObject;
import uy.edu.ude.practicows.entity.Response;

public class OkHttpCaller implements Caller {

  private final String TAG = OkHttpCaller.class.getSimpleName();

  @Override
  public Response callHttpServer(String url) {
    try {
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder().url(url).build();
      okhttp3.Response response = client.newCall(request).execute();
      JSONObject jsonObject = new JSONObject(response.body().string());
      return toResponse(jsonObject);
    } catch (IOException | JSONException e) {
      Log.e(TAG, String.format("Error llamando al servidor %s", url), e);
    }
    return null;
  }

  private Response toResponse(JSONObject jsonObject) throws JSONException {
    String type = jsonObject.getString("type");
    JSONObject value = jsonObject.getJSONObject("value");
    int id = value.getInt("id");
    String quote = value.getString("quote");
    return new Response(id, type, quote);
  }
}
