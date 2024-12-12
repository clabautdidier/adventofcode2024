package common;

public class Chained <T> {
    private T value;
    private Chained<T> next;
    private Chained<T> previous;

    public Chained(T value) {
        this.value = value;
    }

    public Chained<T> insertRight(T value) {
        Chained<T> next = new Chained<>(value);
        next.previous = this;
        next.next = this.next;
        this.next = next;
        if (next.next != null) next.next.previous = next;
        return next;
    }

    public Chained<T> insertLeft(T value) {
        Chained<T> previous = new Chained<>(value);
        previous.next = this;
        previous.previous = this.previous;
        this.previous = previous;
        if (previous.previous != null) previous.previous.next = previous;
        return previous;
    }

    public T getValue() {
        return value;
    }

    public Chained<T> getNext() {
        return next;
    }

    public Chained<T> getPrevious() {
        return previous;
    }

    public boolean hasPrevious() {
        return previous != null;
    }


    public void setValue(T newValue) {
        this.value = newValue;
    }

    public boolean hasNext() {
        return next != null;
    }
}
