package uy.edu.ude.practicows.usecases.caller;

import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
import uy.edu.ude.practicows.entity.Response;

public class HttpCaller implements Caller {

  private final String TAG = HttpCaller.class.getSimpleName();
  @Override
  public Response callHttpServer(String url) {
    try {
      URL urlServer = new URL(url);
      HttpURLConnection httpURLConnection = (HttpURLConnection) urlServer.openConnection();
      httpURLConnection.setRequestMethod("GET");
      Scanner in = new Scanner(httpURLConnection.getInputStream());
      StringBuffer buffer = new StringBuffer();
      while (in.hasNext()) {
        buffer.append(in.nextLine());
      }
      in.close();
      JSONObject jsonObject = new JSONObject(buffer.toString());
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
//  @Override
//  public Response callHttpServer(String url) {
//    try {
//      URL urlServer = new URL(url);
//      HttpURLConnection httpURLConnection = (HttpURLConnection) urlServer.openConnection();
//      httpURLConnection.setRequestMethod("GET");
//      BufferedReader in = new BufferedReader(new
//          InputStreamReader(httpURLConnection.getInputStream()));
//      StringBuffer buffer = new StringBuffer();
//      String inputLine;
//      while (null != (inputLine = in.readLine())) {
//        buffer.append(inputLine);
//      }
//      in.close();
//      JSONObject jsonObject = new JSONObject(buffer.toString());
//      return toResponse(jsonObject);
//    } catch (IOException | JSONException e) {
//      Log.e(TAG, String.format("Error llamando al servidor %s", url), e);
//    }
//    return null;
//  }
}
