
public class Tuple<C, E> {

    private C capacity;
    private E edge;

    public Tuple(C capacity, E edge) {
        this.capacity = capacity;
        this.edge = edge;
    }

    public C getCapacity() {
        return capacity;
    }

    public E getEdge() {
        return edge;
    }

    public void setEdge(E edge) {
        this.edge = edge;
    }

    public void setCapacity(C capacity) {
        this.capacity = capacity;
    }
}
