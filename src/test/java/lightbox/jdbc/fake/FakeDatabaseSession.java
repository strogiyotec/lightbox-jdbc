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
public final class FakeDatabaseSession implements Session, AutoCloseable {

    /**
     * Fake source
     */
    private final static DataSource source;

    private final static EmbeddedPostgres postgres;

    static {
        try {
            postgres = EmbeddedPostgres.start();
            source = postgres.getPostgresDatabase();
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Connection connection() throws Exception {
        return FakeDatabaseSession.source.getConnection();
    }

    @Override
    public void close() throws Exception {
        postgres.close();
    }
}
