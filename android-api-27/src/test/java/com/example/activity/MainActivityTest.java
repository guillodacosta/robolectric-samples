package com.example.activity;

import android.app.Activity;
import android.view.Menu;


import com.example.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

  @Test
  public void onCreateShouldInflateTheMenu() {
    Activity activity = Robolectric.setupActivity(MainActivity.class);

    final Menu menu = shadowOf(activity).getOptionsMenu();
    assertThat(menu.findItem(R.id.item1).getTitle()).isEqualTo("First menu item");
    assertThat(menu.findItem(R.id.item2).getTitle()).isEqualTo("Second menu item");
  }
}
