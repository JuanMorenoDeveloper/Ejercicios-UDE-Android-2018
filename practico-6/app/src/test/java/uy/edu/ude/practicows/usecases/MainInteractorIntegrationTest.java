package uy.edu.ude.practicows.usecases;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uy.edu.ude.practicows.entity.Response;
import uy.edu.ude.practicows.main.interactor.DefaultMainInteractor;
import uy.edu.ude.practicows.main.interactor.MainInteractor;
import uy.edu.ude.practicows.usecases.caller.Caller;
import uy.edu.ude.practicows.usecases.caller.OkHttpCaller;
import uy.edu.ude.practicows.usecases.caller.RetrofitHttpCaller;

@RunWith(RobolectricTestRunner.class)
public class MainInteractorIntegrationTest {


  @Test
  public void givenUrl_whenCall_thenGetResponseNotNull() {
    String url = "http://gturnquist-quoters.cfapps.io/api/1";
    Caller httpCaller = new OkHttpCaller();
    MainInteractor callServer = new DefaultMainInteractor(httpCaller);

    Response response = callServer.call(url);

    assertThat(response).isNotNull();
  }

  @Test
  public void givenUrl_whenCallWithRetrofit_thenGetResponseNotNull() {
    Caller httpCaller = new RetrofitHttpCaller();
    MainInteractor callServer = new DefaultMainInteractor(httpCaller);

    Response response = callServer.call("1");

    assertThat(response).isNotNull();
  }

}
