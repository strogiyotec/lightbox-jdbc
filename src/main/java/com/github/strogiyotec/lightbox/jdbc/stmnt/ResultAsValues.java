package com.github.strogiyotec.lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Query;
import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.Statement;
import com.github.strogiyotec.lightbox.jdbc.rows.ResultSetRows;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@AllArgsConstructor
public final class ResultAsValues<T> implements Statement<List<T>>{

    private final Session session;

    private final Query query;

    @Override
    public Result<List<T>> result() throws Exception {
        try(final Connection connection = this.session.connection()){
            try(final PreparedStatement statement = this.query.prepared(connection)){
                statement.execute();
                try(final ResultSet set = statement.getResultSet()){
                    final ResultSetRows maps = new ResultSetRows(set);
                    return ()->this.valuesFromIterator(maps.iterator());
                }
            }
        }
    }

    private List<T> valuesFromIterator(final Iterator<Map<String, Object>> iterator){
        final List<T> values = new LinkedList<>();
        while(iterator.hasNext()){
            final Map<String, Object> next = iterator.next();
            final List<String> keys = new ArrayList<>(next.keySet());
            if(!keys.isEmpty()){
                values.add((T) next.get(keys.get(0)));
            }
        }
        return values;
    }
}
