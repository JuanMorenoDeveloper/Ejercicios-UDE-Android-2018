package uy.edu.ude.practicows.usecases.caller;

import uy.edu.ude.practicows.entity.Response;

public interface Caller {

  Response callHttpServer(String url);
}
