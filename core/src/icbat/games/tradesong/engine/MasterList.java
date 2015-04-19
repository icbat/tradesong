package icbat.games.tradesong.engine;

import java.util.ArrayList;
import java.util.List;

public class MasterList<Type> {
    List<Type> list = new ArrayList<Type>();
    public void add(Type object) {
        list.add(object);
    }

    public List<Type> getList() {
        return new ArrayList<Type>(list);
    }
}
