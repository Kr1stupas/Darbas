import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.penktaspraktinisdarbas.MainActivity;
import com.example.penktaspraktinisdarbas.R;

import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testFilterEditText() {
        // Wait for the activity to be launched
        Espresso.onView(ViewMatchers.withId(R.id.filterEditText))
                .perform(ViewActions.typeText("Eur"));

        // Check if the item is displayed
        Espresso.onView(ViewMatchers.withText("1 USD = 0.91588757 EUR - 1 U.S. Dollar = 0.91588757 Euro"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
