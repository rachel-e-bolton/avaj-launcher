package wethinkcode.simulator;

import wethinkcode.interfaces.Flyable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Tower {
    private List<Flyable> _observers = new CopyOnWriteArrayList<>();

    public void register(Flyable flyable) {
        if (_observers.contains(flyable)) {
            return;
        }
        _observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        _observers.remove(flyable);
    }

    protected void conditionsChanged() {
        for (Flyable aircraft : _observers) {
            aircraft.updateConditions();
        }
    }
}
