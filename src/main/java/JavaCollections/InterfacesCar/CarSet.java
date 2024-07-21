package JavaCollections.InterfacesCar;

import JavaCollections.ObjectCar.Car;

public interface CarSet extends CarCollection {
    boolean add(Car car);

    boolean remove(Car car);

    int size();

    void clear();
}
