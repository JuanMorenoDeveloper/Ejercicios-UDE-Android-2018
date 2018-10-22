package uy.edu.ude.practicows.main.presenter;

import uy.edu.ude.practicows.entity.Response;
import uy.edu.ude.practicows.main.interactor.MainInteractor;
import uy.edu.ude.practicows.main.view.MainView;

public class DefaultMainPresenter implements MainPresenter {

  private MainInteractor interactor;
  private MainView view;

  public DefaultMainPresenter(MainInteractor interactor, MainView view) {
    this.interactor = interactor;
    this.view = view;
  }

  @Override
  public void call(String url) {
    new MainAsync(this, interactor).execute(url);
  }

  @Override
  public void process(Response response) {
    view.showResult(response);
  }

}
