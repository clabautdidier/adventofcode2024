package common;

import java.util.Objects;

public record Tuple<X, Y>(X x, Y y) {

    @Override
    public String toString() {
        return "Tuple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tuple<?, ?> tuple)) return false;
        return Objects.equals(x, tuple.x) && Objects.equals(y, tuple.y);
    }

}
