package JavaCollections.HashMap;

import JavaCollections.ObjectCar.Car;

import javax.security.auth.callback.TextInputCallback;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarHashMap implements CarMap{

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public void put(CarOwner key, Car value) {
        if(size >= (array.length * LOAD_FACTOR)){
            increaseArray();
        }
        boolean put = put(key, value, array);
        if(put){
            size++;
        }
    }

    private boolean put(CarOwner key, Car value, Entry[] dst){
        int position = getElementPosition(key, dst.length);
        Entry existedElement = dst[position];
        if(existedElement == null){
            Entry entry = new Entry(key, value, null);
            dst[position] = entry;
            return true;
        }else{
            while(true){
                if(existedElement.key.equals(key)){
                    existedElement.value = value;
                    return false;
                }
                if(existedElement.next == null){
                    existedElement.next = new Entry(key, value, null);
                    return true;
                }
                existedElement = existedElement.next;
            }
        }
    }

    @Override
    public Car get(CarOwner key) {
        int position = getElementPosition(key, array.length);
        Entry existedElemnt = array[position];
        while (existedElemnt != null){
            if(existedElemnt.key.equals(key)){
                return existedElemnt.value;
            }
            existedElemnt = existedElemnt.next;
        }
        return null;
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> result = new HashSet<>();
        for(Entry entry : array){
            Entry existedElement = entry;
            while(existedElement != null){
                result.add(existedElement.key);
                existedElement = existedElement.next;
            }
        }
        return result;
    }

    @Override
    public List<Car> values() {
        List<Car> result = new ArrayList<>();
        for(Entry entry : array){
            Entry existedElement = entry;
            while(existedElement != null){
                result.add(existedElement.value);
                existedElement = existedElement.next;
            }
        }
        return result;
    }

    @Override
    public boolean remove(CarOwner key) {
        int position = getElementPosition(key, array.length);
        Entry existedElemnt = array[position];
        if(existedElemnt != null && existedElemnt.key.equals(key)){
            array[position] = existedElemnt.next;
            size--;
            return true;
        }else{
            while (existedElemnt != null){
                Entry nextElement = existedElemnt.next;
                if(nextElement == null){
                    return false;
                }
                if(nextElement.key.equals(key)){
                    existedElemnt.next = nextElement.next;
                    size--;
                    return true;
                }
                existedElemnt = existedElemnt.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private int getElementPosition(CarOwner carOwner, int arrayLenght){
        return Math.abs(carOwner.hashCode() % arrayLenght);
    }

    private void increaseArray(){
        Entry[] newArray = new Entry[array.length * 2];
        for(Entry entry : array){
            Entry existedElement = entry;
            while(existedElement != null){
                put(existedElement.key, existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    private static class Entry{
        private CarOwner key;
        private Car value;
        private Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
