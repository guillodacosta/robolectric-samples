package com.example.activity;

import android.app.Activity;
import android.widget.TextView;

import com.example.BuildConfig;
import com.example.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, packageName = "com.example")
public class MainActivityTest {

  @Test
  public void shouldUseCorrectApplicatioName() throws Exception {
    Activity activity = Robolectric.setupActivity(MainActivity.class);

    final TextView text = (TextView) activity.findViewById(R.id.text);
    assertThat(text.getText().toString()).isEqualTo("Robolectric Flavor 1");
  }
}
