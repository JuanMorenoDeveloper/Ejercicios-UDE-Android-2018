package uy.edu.ude.practicows.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import uy.edu.ude.practicows.R;
import uy.edu.ude.practicows.entity.Response;
import uy.edu.ude.practicows.main.interactor.DefaultMainInteractor;
import uy.edu.ude.practicows.main.interactor.MainInteractor;
import uy.edu.ude.practicows.usecases.caller.Caller;
import uy.edu.ude.practicows.usecases.caller.HttpCaller;
import uy.edu.ude.practicows.main.presenter.DefaultMainPresenter;
import uy.edu.ude.practicows.main.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView, OnClickListener {

  private TextView tvType;
  private TextView tvId;
  private TextView tvQuote;
  private Button btnLlamar;
  private MainPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tvId = findViewById(R.id.tv_id);
    tvQuote = findViewById(R.id.tv_quote);
    tvType = findViewById(R.id.tv_type);
    btnLlamar = findViewById(R.id.btn_llamar);
    btnLlamar.setOnClickListener(this);
    Caller caller = new HttpCaller();
    MainInteractor interactor = new DefaultMainInteractor(caller);
    presenter = new DefaultMainPresenter(interactor, this);
  }

  @Override
  public void showResult(Response response) {
    tvId.setText(String.valueOf(response.getId()));
    tvQuote.setText(response.getQuote());
    tvType.setText(response.getType());
  }

  @Override
  public void onClick(View v) {
    presenter.call("http://gturnquist-quoters.cfapps.io/api/2");
  }

}
