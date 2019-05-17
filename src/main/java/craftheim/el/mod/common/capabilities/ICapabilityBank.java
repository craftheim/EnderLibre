package craftheim.el.mod.common.capabilities;

public interface ICapabilityBank {
    int get();

    void set(int qty);

    void take(int qty);

    void give(int qty);
}