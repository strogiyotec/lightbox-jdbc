package lightbox.jdbc.session;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.session.ConfigurableSession;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import org.junit.Assert;
import org.junit.Test;

import javax.json.Json;
import java.sql.Connection;

import static org.hamcrest.CoreMatchers.is;

public final class ConfigurableSessionTest extends Assert {

    @Test
    public void testConfiguration() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final Connection connection = new ConfigurableSession(
                postgres,
                Json.createObjectBuilder()
                        .add("autoCommit", true)
                        .add("readOnly", true)
                        .build()
        ).connection();

        assertThat(connection.getAutoCommit(), is(true));
        assertThat(connection.isReadOnly(), is(true));

    }
}
