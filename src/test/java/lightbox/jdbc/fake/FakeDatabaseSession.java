package lightbox.jdbc.fake;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.sql.Connection;

/**
 * Factory of fake connections
 */
public final class FakeDatabaseSession implements Session {

    /**
     * Fake postgres
     */
    private final static DataSource postgres;

    static {
        try {
            postgres = EmbeddedPostgres.start().getPostgresDatabase();
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Connection connection() throws Exception {
        return FakeDatabaseSession.postgres.getConnection();
    }
}
