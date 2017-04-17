package com.masaref.bahez.masaref.MasarefModel;

/**
 * Created by Sherdill Noori on 2017-02-11.
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String dateOfBirth;
    private String country;
    private String organization;
    private String occupation;
    private String mobileNumber;
    private String firstQuestion;
    private String firstQuestionAnswer;
    private String secondQuestion;
    private String getSecondQuestionAnswer;

    public User(int id, String firstName, String lastName, String userName, String password,
                String email, String dateOfBirth, String country, String organization,
                String occupation, String mobileNumber, String firstQuestion, String firstQuestionAnswer,
                String secondQuestion, String getSecondQuestionAnswer) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.organization = organization;
        this.occupation = occupation;
        this.mobileNumber = mobileNumber;
        this.firstQuestion = firstQuestion;
        this.firstQuestionAnswer = firstQuestionAnswer;
        this.secondQuestion = secondQuestion;
        this.getSecondQuestionAnswer = getSecondQuestionAnswer;
    }

    public User(String firstName, String lastName, String userName, String password,
                String email, String dateOfBirth, String country, String organization, String occupation,
                String mobileNumber, String firstQuestion, String firstQuestionAnswer, String secondQuestion,
                String getSecondQuestionAnswer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.organization = organization;
        this.occupation = occupation;
        this.mobileNumber = mobileNumber;
        this.firstQuestion = firstQuestion;
        this.firstQuestionAnswer = firstQuestionAnswer;
        this.secondQuestion = secondQuestion;
        this.getSecondQuestionAnswer = getSecondQuestionAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstQuestion() {
        return firstQuestion;
    }

    public void setFirstQuestion(String firstQuestion) {
        this.firstQuestion = firstQuestion;
    }

    public String getFirstQuestionAnswer() {
        return firstQuestionAnswer;
    }

    public void setFirstQuestionAnswer(String firstQuestionAnswer) {
        this.firstQuestionAnswer = firstQuestionAnswer;
    }

    public String getSecondQuestion() {
        return secondQuestion;
    }

    public void setSecondQuestion(String secondQuestion) {
        this.secondQuestion = secondQuestion;
    }

    public String getGetSecondQuestionAnswer() {
        return getSecondQuestionAnswer;
    }

    public void setGetSecondQuestionAnswer(String getSecondQuestionAnswer) {
        this.getSecondQuestionAnswer = getSecondQuestionAnswer;
    }
}
