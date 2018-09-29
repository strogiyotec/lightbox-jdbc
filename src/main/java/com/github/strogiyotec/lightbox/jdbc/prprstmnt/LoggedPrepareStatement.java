package com.github.strogiyotec.lightbox.jdbc.prprstmnt;

import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.log.LogStatements;
import com.github.strogiyotec.lightbox.jdbc.query.SqlQuery;
import org.slf4j.Logger;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time. Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public final class LoggedPrepareStatement extends PreparedStatementOf {

    private final Logger log;

    private final int id;

    private final LogStatements logStatements;

    private final List<Object> argc = new ArrayList<>();

    private final Query sql;

    public LoggedPrepareStatement(final PreparedStatement origin, final int id, final Logger log, final LogStatements jdbcLog, final Query sql) {
        super(origin);
        this.log = log;
        this.id = id;
        this.logStatements = jdbcLog;
        this.sql = sql;
    }

    @Override
    public void setNull(final int parameterIndex, final int sqlType) throws SQLException {
        super.setNull(parameterIndex, sqlType);
        this.setValue(null, parameterIndex);
    }

    @Override
    public void setBoolean(final int parameterIndex, final boolean x) throws SQLException {
        super.setBoolean(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setByte(final int parameterIndex, final byte x) throws SQLException {
        super.setByte(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setShort(final int parameterIndex, final short x) throws SQLException {
        super.setShort(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setInt(final int parameterIndex, final int x) throws SQLException {
        super.setInt(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setLong(final int parameterIndex, final long x) throws SQLException {
        super.setLong(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setFloat(final int parameterIndex, final float x) throws SQLException {
        super.setFloat(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setDouble(final int parameterIndex, final double x) throws SQLException {
        super.setDouble(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setBigDecimal(final int parameterIndex, final BigDecimal x) throws SQLException {
        super.setBigDecimal(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setString(final int parameterIndex, final String x) throws SQLException {
        super.setString(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setBytes(final int parameterIndex, final byte[] x) throws SQLException {
        super.setBytes(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setDate(final int parameterIndex, final Date x) throws SQLException {
        super.setDate(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setTime(final int parameterIndex, final Time x) throws SQLException {
        super.setTime(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setTimestamp(final int parameterIndex, final Timestamp x) throws SQLException {
        super.setTimestamp(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream x, final int length) throws SQLException {
        super.setAsciiStream(parameterIndex, x, length);
        this.setValue("AsciiStream", parameterIndex);
    }

    @Override
    public void setUnicodeStream(final int parameterIndex, final InputStream x, final int length) throws SQLException {
        super.setUnicodeStream(parameterIndex, x, length);
        this.setValue("UnicodeStream", parameterIndex);
    }

    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream x, final int length) throws SQLException {
        super.setBinaryStream(parameterIndex, x, length);
        this.setValue("BinaryStream", parameterIndex);
    }

    @Override
    public void setObject(final int parameterIndex, final Object x, final int targetSqlType) throws SQLException {
        super.setObject(parameterIndex, x, targetSqlType);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setObject(final int parameterIndex, final Object x) throws SQLException {
        super.setObject(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader, final int length) throws SQLException {
        super.setCharacterStream(parameterIndex, reader, length);
        this.setValue("Character stream", parameterIndex);
    }

    @Override
    public void setRef(final int parameterIndex, final Ref x) throws SQLException {
        super.setRef(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setBlob(final int parameterIndex, final Blob x) throws SQLException {
        super.setBlob(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setClob(final int parameterIndex, final Clob x) throws SQLException {
        super.setClob(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setArray(final int parameterIndex, final Array x) throws SQLException {
        super.setArray(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setDate(final int parameterIndex, final Date x, final Calendar cal) throws SQLException {
        super.setDate(parameterIndex, x, cal);
        this.setValue(cal, parameterIndex);
    }

    @Override
    public void setTime(final int parameterIndex, final Time x, final Calendar cal) throws SQLException {
        super.setTime(parameterIndex, x, cal);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setTimestamp(final int parameterIndex, final Timestamp x, final Calendar cal) throws SQLException {
        super.setTimestamp(parameterIndex, x, cal);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setNull(final int parameterIndex, final int sqlType, final String typeName) throws SQLException {
        super.setNull(parameterIndex, sqlType, typeName);
        this.setValue(typeName, parameterIndex);
    }

    @Override
    public void setURL(final int parameterIndex, final URL x) throws SQLException {
        super.setURL(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setRowId(final int parameterIndex, final RowId x) throws SQLException {
        super.setRowId(parameterIndex, x);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setNString(final int parameterIndex, final String value) throws SQLException {
        super.setNString(parameterIndex, value);
        this.setValue(value, parameterIndex);
    }

    @Override
    public void setNCharacterStream(final int parameterIndex, final Reader value, final long length) throws SQLException {
        super.setNCharacterStream(parameterIndex, value, length);
        this.setValue("NCharacterStream", parameterIndex);
    }

    @Override
    public void setNClob(final int parameterIndex, final NClob value) throws SQLException {
        super.setNClob(parameterIndex, value);
        this.setValue(value, parameterIndex);
    }

    @Override
    public void setClob(final int parameterIndex, final Reader reader, final long length) throws SQLException {
        super.setClob(parameterIndex, reader, length);
        this.setValue("Clob", parameterIndex);
    }

    @Override
    public void setBlob(final int parameterIndex, final InputStream inputStream, final long length) throws SQLException {
        super.setBlob(parameterIndex, inputStream, length);
        this.setValue("Blob", parameterIndex);
    }

    @Override
    public void setNClob(final int parameterIndex, final Reader reader, final long length) throws SQLException {
        super.setNClob(parameterIndex, reader, length);
        this.setValue("NClob", parameterIndex);
    }

    @Override
    public void setSQLXML(final int parameterIndex, final SQLXML xmlObject) throws SQLException {
        super.setSQLXML(parameterIndex, xmlObject);
        this.setValue(xmlObject, parameterIndex);
    }

    @Override
    public void setObject(final int parameterIndex, final Object x, final int targetSqlType, final int scaleOrLength) throws SQLException {
        super.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
        this.setValue(x, parameterIndex);
    }

    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream x, final long length) throws SQLException {
        super.setAsciiStream(parameterIndex, x, length);
        this.setValue("AsciiStream", parameterIndex);
    }

    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream x, final long length) throws SQLException {
        super.setBinaryStream(parameterIndex, x, length);
        this.setValue("BinaryStream", parameterIndex);
    }

    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader, final long length) throws SQLException {
        super.setCharacterStream(parameterIndex, reader, length);
        this.setValue("CharacterStream", parameterIndex);
    }

    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream x) throws SQLException {
        super.setAsciiStream(parameterIndex, x);
        this.setValue("AsciiStream", parameterIndex);
    }

    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream x) throws SQLException {
        super.setBinaryStream(parameterIndex, x);
        this.setValue("BinaryStream", parameterIndex);
    }

    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader) throws SQLException {
        super.setCharacterStream(parameterIndex, reader);
        this.setValue("CharacterStream", parameterIndex);
    }

    @Override
    public void setNCharacterStream(final int parameterIndex, final Reader value) throws SQLException {
        super.setNCharacterStream(parameterIndex, value);
        this.setValue("NCharacterStream", parameterIndex);
    }

    @Override
    public void setClob(final int parameterIndex, final Reader reader) throws SQLException {
        super.setClob(parameterIndex, reader);
        this.setValue("Clob", parameterIndex);
    }

    @Override
    public void setBlob(final int parameterIndex, final InputStream inputStream) throws SQLException {
        super.setBlob(parameterIndex, inputStream);
        this.setValue("Blob", parameterIndex);
    }

    @Override
    public void setNClob(final int parameterIndex, final Reader reader) throws SQLException {
        super.setNClob(parameterIndex, reader);
        this.setValue("NCLOB", parameterIndex);
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        final Instant now = Instant.now();
        final ResultSet resultSet = super.executeQuery();
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteQuery SQL: [{}] . Duration [{}] mls", new SqlQuery(this.argc, this.sql).asString(), between.toMillis());
        return resultSet;
    }

    @Override
    public int executeUpdate() throws SQLException {
        final Instant now = Instant.now();
        final int resultSet = super.executeUpdate();
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteUpdate SQL: [{}] . Duration [{}] mls", new SqlQuery(this.argc, this.sql).asString(), between.toMillis());
        return resultSet;
    }

    @Override
    public boolean execute() throws SQLException {
        final Instant now = Instant.now();
        final boolean resultSet = super.execute();
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("Execute SQL: [{}] . Duration [{}] mls", new SqlQuery(this.argc, this.sql).asString(), between.toMillis());
        return resultSet;
    }

    @Override
    public ResultSet executeQuery(final String sql) throws SQLException {
        final Instant now = Instant.now();
        final ResultSet resultSet = super.executeQuery(sql);
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteQuery SQL: [{}] R [{}] mls", new SqlQuery(Collections.<Object>emptyList(), sql), between.toMillis());
        return resultSet;
    }

    @Override
    public int executeUpdate(final String sql) throws SQLException {
        final Instant now = Instant.now();
        final int resultSet = super.executeUpdate(sql);
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteUpdate SQL: [{}] . Duration [{}] mls", new SqlQuery(Collections.<Object>emptyList(), sql), between.toMillis());
        return resultSet;
    }

    @Override
    public boolean execute(final String sql) throws SQLException {
        final Instant now = Instant.now();
        final boolean resultSet = super.execute(sql);
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("Execute SQL: [{}] . Duration [{}] mls", new SqlQuery(Collections.<Object>emptyList(), sql), between.toMillis());
        return resultSet;
    }

    @Override
    public int[] executeBatch() throws SQLException {
        final Instant now = Instant.now();
        final int[] resultSet = super.executeBatch();
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteBatch SQL: [{}] . Duration [{}] mls", new SqlQuery(Collections.<Object>emptyList(), this.sql), between.toMillis());
        return resultSet;
    }

    @Override
    public int executeUpdate(final String sql, final int autoGeneratedKeys) throws SQLException {
        final Instant now = Instant.now();
        final int resultSet = super.executeUpdate(sql, autoGeneratedKeys);
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteUpdateWithAutoGeneratedKeys SQL: [{}] . Duration [{}] mls", new SqlQuery(Collections.<Object>emptyList(), sql), between.toMillis());
        return resultSet;
    }

    @Override
    public int executeUpdate(final String sql, final int[] columnIndexes) throws SQLException {
        final Instant now = Instant.now();
        final int resultSet = super.executeUpdate(sql, columnIndexes);
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteUpdateWithColumnIndexes SQL: [{}] . Duration [{}] mls", new SqlQuery(Collections.<Object>emptyList(), sql), between.toMillis());
        return resultSet;
    }

    @Override
    public int executeUpdate(final String sql, final String[] columnNames) throws SQLException {
        final Instant now = Instant.now();
        final int resultSet = super.executeUpdate(sql, columnNames);
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteUpdateColumnNames SQL: [{}] . Duration [{}] mls", new SqlQuery(Collections.<Object>emptyList(), sql), between.toMillis());
        return resultSet;
    }

    @Override
    public boolean execute(final String sql, final int autoGeneratedKeys) throws SQLException {
        final Instant now = Instant.now();
        final boolean resultSet = super.execute(sql, autoGeneratedKeys);
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteWithAutoGeneratedKeys SQL: [{}] . Duration [{}] mls", new SqlQuery(Collections.<Object>emptyList(), sql), between.toMillis());
        return resultSet;
    }

    @Override
    public boolean execute(final String sql, final int[] columnIndexes) throws SQLException {
        final Instant now = Instant.now();
        final boolean resultSet = super.execute(sql, columnIndexes);
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteWithColumnIndexes SQL: [{}] . Duration [{}] mls", new SqlQuery(Collections.<Object>emptyList(), sql), between.toMillis());
        return resultSet;
    }

    @Override
    public boolean execute(final String sql, final String[] columnNames) throws SQLException {
        final Instant now = Instant.now();
        final boolean resultSet = super.execute(sql, columnNames);
        final Instant end = Instant.now();
        final Duration between = Duration.between(now, end);
        this.log.info("ExecuteWithColumnNames SQL: [{}] . Duration [{}] mls", new SqlQuery(Collections.<Object>emptyList(), sql), between.toMillis());
        return resultSet;
    }

    private void setValue(final Object value, final int position) {
        synchronized (this.argc) {
            while (position >= this.argc.size()) {
                this.argc.set(this.argc.size(), null);
            }
            this.argc.set(position, value);
        }
    }
}
