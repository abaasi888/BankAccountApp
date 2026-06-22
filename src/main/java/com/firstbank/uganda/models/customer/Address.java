package com.firstbank.uganda.models.customer;
public class Address {
    private String street, city, district, postalCode, country;
    public Address(String street, String city, String district) {
        this.street = street; this.city = city; this.district = district;
        this.country = "Uganda";
    }
    public String getFullAddress() { return street + ", " + city + ", " + district + ", " + country; }
    public boolean isUgandanAddress() { return country != null && country.equalsIgnoreCase("Uganda"); }
    // Getters and Setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
