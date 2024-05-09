package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private String name, country, club, position ;
    private int age, number ;
    private double height, weekly_salary ;
    private int price;

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Player() {

    }

    public Player(String name, String country, String club, String position, int age, int number, double height, double weekly_salary) {
        this.name = name;
        this.country = country;
        this.club = club;
        this.position = position;
        this.age = age;
        this.number = number;
        this.height = height;
        this.weekly_salary = weekly_salary;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getClub() {
        return club;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setWeekly_salary(double weekly_salary) {
        this.weekly_salary = weekly_salary;
    }

    public double getWeekly_salary() {
        return weekly_salary;
    }

    public String toString(){
        return "Name: " + name + "\n" + "Country: " + country + "\n" + "Age: " + age + "\n" + "Height: " + height + "\n" + "Club: " + club + "\n" + "Position: " + position + "\n" + "Number: " + number + "\n" + "Weekly Salary: " + weekly_salary + "\n\n" ;
    }


}

