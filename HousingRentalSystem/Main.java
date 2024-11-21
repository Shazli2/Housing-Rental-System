package HousingRentalSystem;

import java.util.ArrayList;

abstract class Housing {
    private int numberOfRooms;
    private double sizeInM2;
    private double pricePerDay;
    private String address;
    private String city;
    private boolean isRented;
    private double totalIncome;

    public Housing(int numberOfRooms, double sizeInM2, double pricePerDay, String address, String city) {
        this.numberOfRooms = numberOfRooms;
        this.sizeInM2 = sizeInM2;
        this.pricePerDay = pricePerDay;
        this.address = address;
        this.city = city;
        this.isRented = false;
        this.totalIncome = 0.0;
    }

    public boolean isRented() {
        return isRented;
    }

    public void rentHousing() {
        isRented = true;
    }

    public void returnHousing(int rentalDays) {
        isRented = false;
        totalIncome += rentalDays * pricePerDay;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public abstract String getType();
}

class Apartment extends Housing {
    public Apartment(int numberOfRooms, double sizeInM2, double pricePerDay, String address, String city) {
        super(numberOfRooms, sizeInM2, pricePerDay, address, city);
    }

    @Override
    public String getType() {
        return "Apartment";
    }
}

class Villa extends Housing {
    public Villa(int numberOfRooms, double sizeInM2, double pricePerDay, String address, String city) {
        super(numberOfRooms, sizeInM2, pricePerDay, address, city);
    }

    @Override
    public String getType() {
        return "Villa";
    }
}

class Customer {
    private String name;
    private String address;
    private int age;
    private ArrayList<Rental> rentals;

    public Customer(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.rentals = new ArrayList<>();
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
        if (rentals.size() > 5) {
            rentals.remove(0);
        }
    }

    public ArrayList<Rental> getLastFiveRentals() {
        return rentals;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Housing housing;
    private int rentalDays;
    private Customer customer;

    public Rental(Housing housing, int rentalDays, Customer customer) {
        this.housing = housing;
        this.rentalDays = rentalDays;
        this.customer = customer;
        housing.rentHousing();
    }

    public void endRental() {
        housing.returnHousing(rentalDays);
    }

    public Housing getHousing() {
        return housing;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public Customer getCustomer() {
        return customer;
    }
}

public class Main {
    public static void main(String[] args) {

        Housing apartment1 = new Apartment(3, 90, 100, "Entebbe", "Kampala");
        Housing villa1 = new Villa(5, 150, 200, "Omdurman", "Khartoum");

        Customer customer1 = new Customer("Musa", "Kampala", 30);

        Rental rental1 = new Rental(apartment1, 5, customer1);
        Rental rental2 = new Rental(villa1, 3, customer1);

        rental1.endRental();
        rental2.endRental();

        customer1.addRental(rental1);
        customer1.addRental(rental2);

        System.out.println("Total Income for " + apartment1.getType() + ": " + apartment1.getTotalIncome());
        System.out.println("Total Income for " + villa1.getType() + ": " + villa1.getTotalIncome());

        System.out.println("Last 5 Rentals for Customer " + customer1.getName() + ": ");
        for (Rental rental : customer1.getLastFiveRentals()) {
            System.out.println("Rented " + rental.getHousing().getType() + " for " + rental.getRentalDays() + " days.");
        }
    }
}

/*

You are tasked with designing a housing rental system.
 The system has to manage different types of housing (apartment, villa).
 It also needs to save information about customers who rent the housing.

House information:

    number of rooms
    size in m2
    price per day
    address
    city

Customer information:

    Name
    Address
    Age

When designing this system, have in mind the features that the users will need:

    check if the housing is currently free or rented out
    calculate total rental income per housing
    get a list of max last 5 rentals per customer

 */
