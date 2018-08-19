package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.*;
import com.github.strogiyotec.lightbox.jdbc.rows.ResultSetRows;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@AllArgsConstructor
public final class InsertWithKeys implements Statement<Rows>{

    private final Query query;

    private final Session session;

    @Override
    public Result<Rows> result() throws Exception {
        try(final Connection connection = this.session.connection()){
            try(final PreparedStatement statement = this.query.prepared(connection)){
                statement.execute();
                try(final ResultSet resultSet = statement.getGeneratedKeys()){
                    final Rows maps = new ResultSetRows(resultSet);
                    return ()->maps;
                }
            }
        }
    }
}
