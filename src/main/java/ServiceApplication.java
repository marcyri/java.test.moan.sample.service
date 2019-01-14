import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.Set;

/**
 * Rest Application root context. - root context's api
 */
@ApplicationPath("api")
public class ServiceApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Collections.emptySet();
    }
}
