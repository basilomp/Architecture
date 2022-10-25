package system_patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListValue<T> {

    private final List<T> list;

    public ListValue(List<T> list) {
        this.list = Collections.unmodifiableList(list);
//        this.list = list;
    }

    public List<T> getList() {
//        return new ArrayList<>(list);
//        return Collections.unmodifiableList(list);
        return list;
    }
}
