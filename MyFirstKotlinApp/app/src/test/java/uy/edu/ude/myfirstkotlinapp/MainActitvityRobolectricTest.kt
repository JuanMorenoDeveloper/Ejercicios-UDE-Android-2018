package uy.edu.ude.myfirstkotlinapp

import android.content.Intent
import android.widget.Button
import android.widget.TextView
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowToast


@RunWith(RobolectricTestRunner::class)
class MainActitvityRobolectricTest {

  @Test
  fun whenClickToastBtn_thenShowToast() {
    val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
    val btnToast = mainActivity.findViewById<Button>(R.id.btnToast)

    btnToast.performClick()

    assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("Hello Toast!")
  }

  @Test
  fun `when click in CountMeBtn then ShowInTv One`() {
    val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
    val btnCount = mainActivity.findViewById<Button>(R.id.btnCount)
    val tvCount = mainActivity.findViewById<TextView>(R.id.tvCount)

    btnCount.performClick()

    assertThat(tvCount.text.toString()).isEqualTo("1")
  }


  @Test
  fun `when click in RandomBtn then Navigate to RandomActivity`() {
    val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
    val btnRandom = mainActivity.findViewById<Button>(R.id.btnRandom)

    btnRandom.performClick()
    val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
    val expectedIntent = Intent(mainActivity, RandomActivity::class.java)

    assertThat(expectedIntent.component).isEqualTo(actual.component)
  }
}
