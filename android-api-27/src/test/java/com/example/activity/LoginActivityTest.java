package com.example.activity;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static android.os.Build.VERSION_CODES.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.annotation.Config.ALL_SDKS;

@RunWith(RobolectricTestRunner.class)
@Config(qualifiers = "small-port")
public class LoginActivityTest {
    private EditText emailView;
    private EditText passwordView;
    private Button button;
    private ImageView imageView;

    @Before
    public void setUp() {
        assertNotNull(shadowOf(RuntimeEnvironment.application));
        assertTrue(Robolectric.setupActivity(LoginActivity.class) != null);
        Activity activity = Robolectric.setupActivity(LoginActivity.class);
        button = (Button) activity.findViewById(R.id.email_sign_in_button);
        emailView = (EditText) activity.findViewById(R.id.email);
        imageView = (ImageView) activity.findViewById(R.id.image_logo);
        passwordView = (EditText) activity.findViewById(R.id.password);
    }

    @Test
    // @Config(sdk = ALL_SDKS)
    public void loginSuccess() {
        emailView.setText("foo@example.com");
        passwordView.setText("foo");
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity has started", application.getNextStartedActivity(), is(notNullValue()));
    }

    @Test
    @Config(sdk = {LOLLIPOP, M, O}, qualifiers = "land")
    public void loginWithEmptyUsernameAndPasswordOnSmall() {
        loginWithEmptyUsernameAndPassword();
        assertThat("", imageView, is(notNullValue()));
    }

    @Test
    @Config(qualifiers = "xlarge-port")
    public void loginWithEmptyUsernameAndPasswordOnXLarge() {
        loginWithEmptyUsernameAndPassword();
        assertThat("", imageView, is(nullValue()));
    }

    @Test
    @Config(qualifiers = "+land")
    // if '+' to a exist qualifier at the same type, the value is override for the new
    public void loginWithEmptyUsernameAndPasswordOnlarge() {
        loginWithEmptyUsernameAndPassword();
        assertThat("", imageView, is(notNullValue()));
    }

    @Test

    public void loginWithEmptyUsernameAndPasswordOnLandscape() {
        loginWithEmptyUsernameAndPassword();
    }

    private void loginWithEmptyUsernameAndPassword() {
        final String EXPECTED = "This field is required";
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity should not started", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", emailView.getError(), is(notNullValue()));
        assertThat("Show error for Password field ", passwordView.getError(), is(notNullValue()));
        assertThat("Show string error for Password field ", passwordView.getError().toString(), is(equalTo(EXPECTED)));
    }

    @Test
    public void loginFailure() {
        emailView.setText("invalid@email");
        passwordView.setText("invalidpassword");
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity should not started", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", emailView.getError(), is(notNullValue()));
        assertThat("Show error for Password field ", passwordView.getError(), is(notNullValue()));
    }
}
