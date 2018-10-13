package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Statement;

public final class Delete implements Statement<Integer> {

    private final Statement<Integer> origin;

    public Delete(final Update origin) {
        this.origin = origin;
    }

    @Override
    public Result<Integer> result() throws Exception {
        return this.origin.result();
    }
}
