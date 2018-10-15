package lightbox.jdbc.stmnt.select;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public final class DbUser implements User {

    private final Map<String, Object> values;

    @Override
    public int id() {
        return (int) this.values.get("id");
    }

    @Override
    public String name() {
        return (String) this.values.get("name");
    }
}
