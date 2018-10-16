package lightbox.jdbc.stmnt.select;

import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.Iterator;

@AllArgsConstructor
public final class DbUsers implements Users<DbUser> {

    private final Rows rows;

    @Override
    public Iterator<DbUser> iterator() {
        return Lists.newArrayList(this.rows.iterator()).stream().map(DbUser::new).iterator();
    }
}
