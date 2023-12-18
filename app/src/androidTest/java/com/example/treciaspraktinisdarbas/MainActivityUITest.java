import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.treciaspraktinisdarbas.MainActivity;
import com.example.treciaspraktinisdarbas.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void testButtonClick() {
        Espresso.onView(ViewMatchers.withId(R.id.button_one)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.solution)).check(matches(withText("1")));
    }

    @Test
    public void testCalculation() {
        Espresso.onView(withId(R.id.button_two)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.button_plus)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.button_three)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.button_equals)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.result)).check(matches(withText("5")));
    }
}
