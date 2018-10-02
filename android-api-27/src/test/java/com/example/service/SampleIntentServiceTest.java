package com.example.service;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SampleIntentServiceTest {

    @Test
    public void addsDataToSharedPreference() {
        assertNotNull(shadowOf(RuntimeEnvironment.application));
        Application application = RuntimeEnvironment.application;
//        ShadowApplication application = ShadowApplication.getInstance();
        SharedPreferences preferences = application.getSharedPreferences("example", Context.MODE_PRIVATE);
        Intent intent =  new Intent(application, SampleIntentService.class);
        SampleIntentService registrationService = new SampleIntentService();

        registrationService.onHandleIntent(intent);

        assertNotSame("", preferences.getString("SAMPLE_DATA", ""), "");
    }
}
