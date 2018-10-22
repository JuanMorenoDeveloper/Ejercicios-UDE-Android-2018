package uy.edu.ude.practicows.main.interactor;

import uy.edu.ude.practicows.entity.Response;
import uy.edu.ude.practicows.usecases.caller.Caller;

public class DefaultMainInteractor implements MainInteractor {

  private final Caller caller;

  public DefaultMainInteractor(Caller caller) {
    this.caller = caller;
  }

  @Override
  public Response call(String url) {
    return caller.callHttpServer(url);
  }
}
