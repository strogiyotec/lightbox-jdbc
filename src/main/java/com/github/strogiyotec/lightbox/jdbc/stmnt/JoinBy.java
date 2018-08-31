package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class JoinBy implements Statement<Rows>{

    @Override
    public Result<Rows> result() throws Exception {
        return null;
    }

    private final Statement<Rows> rows;
}
