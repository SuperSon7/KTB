package com.vani.week1;

public class Car {
    String brand;
    int speed;
    public Car() {
        speed = 0;
        System.out.println("Car constructor");
    }
    void accelerate() {
        speed +=10;
        System.out.println("accelerate: " + speed + "km/h");
    }
}
