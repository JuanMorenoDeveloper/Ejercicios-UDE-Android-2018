package uy.edu.ude.practicows.main.presenter;

import uy.edu.ude.practicows.entity.Response;

public interface MainPresenter {

  //Llamar interactor
  void call(String url);

  //Recibo la respuesta
  void process(Response response);
}
