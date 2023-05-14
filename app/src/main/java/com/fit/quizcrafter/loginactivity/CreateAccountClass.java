package com.fit.quizcrafter.loginactivity;

public class CreateAccountClass {
    public String accountName;
    private String datePicker;
    private String AddressOne;
    private String AddressTwo;
    private String Cityinput;
    private String stateinput;
    private String zipinput;
    private String countryinput;
    private String spinnerforgender;
    public String emailAddress;
    private String password;


    public CreateAccountClass(){

    }

    public CreateAccountClass(String accountName, String datePicker, String addressOne, String addressTwo, String cityinput, String stateinput, String zipinput, String countryinput,String spinnerforgender,String emailAddress,String password) {
        this.accountName = accountName;
        this.datePicker = datePicker;
        this.AddressOne = addressOne;
        this.AddressTwo = addressTwo;
        this.Cityinput = cityinput;
        this.stateinput = stateinput;
        this.zipinput = zipinput;
        this.countryinput = countryinput;
        this.spinnerforgender = spinnerforgender;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getDatePicker() {
        return datePicker;
    }

    public String getAddressOne() {
        return AddressOne;
    }

    public String getAddressTwo() {
        return AddressTwo;
    }

    public String getCityinput() {
        return Cityinput;
    }

    public String getStateinput() {
        return stateinput;
    }

    public String getZipinput() {
        return zipinput;
    }

    public String getCountryinput() {
        return countryinput;
    }

    public String getSpinnerforgender() {
        return spinnerforgender;
    }

    public String getemailAddress() {
        return emailAddress;
    }

    public String getpassword() {
        return password;
    }
}
