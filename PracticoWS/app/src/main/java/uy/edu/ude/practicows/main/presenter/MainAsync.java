package uy.edu.ude.practicows.main.presenter;

import android.os.AsyncTask;
import uy.edu.ude.practicows.entity.Response;
import uy.edu.ude.practicows.main.interactor.MainInteractor;

public class MainAsync extends AsyncTask<String, Void, Response> {


  private final MainPresenter presenter;
  private final MainInteractor interactor;

  public MainAsync(MainPresenter presenter, MainInteractor interactor) {
    this.presenter = presenter;
    this.interactor = interactor;
  }

  @Override
  protected Response doInBackground(String... urls) {
    return interactor.call(urls[0]);
  }

}
