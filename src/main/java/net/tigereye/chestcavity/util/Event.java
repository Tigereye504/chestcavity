package net.tigereye.chestcavity.util;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
public final class Event<T> {
    private final Class<T> type;
    private final Function<T[],T> factory;
    private final List<T> listeners = new ArrayList<>();
    private T invoker;
    Event(Class<T> type, Function<T[],T> factory) { this.type=type; this.factory=factory; rebuild(); }
    @SuppressWarnings("unchecked") private void rebuild() { invoker=factory.apply(listeners.toArray(n -> (T[]) java.lang.reflect.Array.newInstance(type,n))); }
    public void register(T listener) { listeners.add(listener); rebuild(); }
    public T invoker() { return invoker; }
}
