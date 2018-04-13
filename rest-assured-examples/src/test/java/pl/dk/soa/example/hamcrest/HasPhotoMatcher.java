package pl.dk.soa.example.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.Map;

public class HasPhotoMatcher extends BaseMatcher {

    public static HasPhotoMatcher hasPhoto() {
        return new HasPhotoMatcher();
    }

    @Override
    public boolean matches(Object o) {
        Map<String, Object> candidate = (Map<String, Object>) o;
        return candidate.get("photoPath") != null;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("photoPath should not be null");
    }
}
