package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.*;
import com.github.strogiyotec.lightbox.jdbc.rows.ResultSetRows;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@AllArgsConstructor
public final class InsertWithKeys implements Statement<Rows>{

    private final Statement<Rows> origin;

    @Override
    public Result<Rows> result() throws Exception {
        return this.origin.result();
    }
}
