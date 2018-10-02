package com.example.activity;

import android.app.Activity;
import android.view.Menu;
import com.example.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowToast;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Robolectric.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

  @Test
  public void onCreate_shouldInflateLayout() throws Exception {
    Activity activity = Robolectric.setupActivity(MainActivity.class);

    final Menu menu = shadowOf(activity).getOptionsMenu();
    assertThat(menu.findItem(R.id.item1).getTitle()).isEqualTo("Menu Item 1");
    assertThat(menu.findItem(R.id.item2).getTitle()).isEqualTo("Menu Item 2");
  }

  @Test
  public void clickMenuItem_shouldDelegateClickToFragment() {
    final MainActivity activity = setupActivity(MainActivity.class);

    shadowOf(activity).clickMenuItem(R.id.item4);
    assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("Clicked Item 4");
  }
}
