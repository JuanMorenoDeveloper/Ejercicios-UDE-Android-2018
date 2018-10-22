package uy.edu.ude.practicows.usecases;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import uy.edu.ude.practicows.main.interactor.MainInteractor;
import uy.edu.ude.practicows.usecases.caller.Caller;
import uy.edu.ude.practicows.entity.Response;
import uy.edu.ude.practicows.main.interactor.DefaultMainInteractor;
import uy.edu.ude.practicows.usecases.caller.HttpCaller;

public class MainInteractorUnitTest {

  @Test
  public void givenUrl_whenCall_thenGetResponseNotNull() {
    Caller httpCaller = mock(HttpCaller.class);
    MainInteractor callServer = new DefaultMainInteractor(httpCaller);
    String url = "http://gturnquist-quoters.cfapps.io/api/1";
    when(httpCaller.callHttpServer(url)).thenReturn(new Response(0, "", ""));
    Response response = callServer.call(url);

    assertThat(response).isNotNull();
  }
}
