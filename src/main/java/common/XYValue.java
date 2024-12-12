package common;

import java.awt.*;
import java.util.Objects;

public class XYValue<T> extends Point {
    public T value;

    public XYValue(int x, int y, T value) {
        super(x, y);
        this.value = value;
    }

    public XYValue(Point point, T value) {
        super(point);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof XYValue that)) return false;
        if (!super.equals(o)) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "XYValue {" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }
}
