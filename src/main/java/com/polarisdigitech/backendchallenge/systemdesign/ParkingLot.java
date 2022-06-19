package com.polarisdigitech.backendchallenge.systemdesign;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Car> carSlotList;

    public ParkingLot(int size){
        this.carSlotList = new ArrayList<>(size);
    }

    public Boolean park(Car car){
        for (int i = 0; i < carSlotList.size(); i++){
            if (carSlotList.get(i) == null)
            {
                carSlotList.add(car);
                return true;
            }
        }
        return false;
    }

    public Boolean remove(Car car){
        for (int i = 0; i < carSlotList.size(); i++){
            if (carSlotList.get(i) != null){
                if (carSlotList.get(i) == car)
                {
                    carSlotList.set(i, null); //best way to also facilitate Garbage Collection
                    return true;
                }
            }
        }
        return false;
    }

    public List<Car> getSlots(){
        return this.carSlotList;
    }

    public int getSize(){
        return this.carSlotList.size();
    }

    public long getAvailableSlots(){
        return this.carSlotList.stream().filter(car -> car == null).count();
    }

    public Boolean isFull(){
        return this.getAvailableSlots() == 0;
    }
}

class Car {

}
