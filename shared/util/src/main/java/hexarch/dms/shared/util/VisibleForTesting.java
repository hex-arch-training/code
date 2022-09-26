package hexarch.dms.shared.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation should be used to decorate any public element of a class that is made public only for sake of testing.
 * Other usage than in tests is prohibited.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface VisibleForTesting {
}
