package net.tigereye.chestcavity.util;
import java.util.function.Function;
public final class EventFactory {
    public static <T> Event<T> createArrayBacked(Class<T> type, Function<T[],T> factory) { return new Event<>(type,factory); }
}
