package com.example.activity;

import android.app.Activity;
import android.app.Application;
import android.app.MediaRouteButton;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.BuildConfig;
import com.example.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowLog;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.core.Is.is;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoginActivityTest {
    private EditText textView;
    private EditText passwordView;
    private Button button;
    private Activity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(LoginActivity.class);
        button = (Button)activity.findViewById(R.id.email_sign_in_button);
        textView = (EditText) activity.findViewById(R.id.email);
        passwordView = (EditText)activity.findViewById(R.id.password);
    }
    @Test
    public void loginSuccess(){
        textView.setText("foo@example.com");
        passwordView.setText("foo");
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertNotNull(application.getNextStartedActivity());
    }

    @Test
    public void loginWithEmptyUsernameAndPassword(){
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertNull(application.getNextStartedActivity());
        assertNotNull(textView.getError());
        assertNotNull(passwordView.getError());
    }

    @Test
    public void loginFailure(){
        textView.setText("invalid@email");
        passwordView.setText("invalidpassword");
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertNull(application.getNextStartedActivity());
        assertNotNull(textView.getError());
        assertNotNull(passwordView.getError());
        View progressView = activity.findViewById(R.id.login_progress);
        assertTrue(progressView.getVisibility() == View.GONE);
    }
}
