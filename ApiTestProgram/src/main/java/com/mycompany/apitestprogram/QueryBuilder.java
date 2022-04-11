package com.mycompany.apitestprogram;

/**
 * An abstract builder class for creating query to the DOHMH restaurant inspection database.
 * @author ndars
 */
public abstract class QueryBuilder {
    protected String name; // the restaurant's CAMIS
    protected Grade grade;
    protected String foodType;
    protected String address;
    protected Borough borough;
    protected String zipCode;
    
    public QueryBuilder setName(String name) {
        this.name = name;
        return this;
    }
    
    public QueryBuilder setGrade(Grade grade) {
        this.grade = grade;
        return this;
    }
    
    public QueryBuilder setFoodType(String foodType) {
        this.foodType = foodType;
        return this;
    }
    
    public QueryBuilder setAddress(String address) {
        this.address = address;
        return this;
    }
    
    public QueryBuilder setBorough(Borough borough) {
        this.borough = borough;
        return this;
    }
    
    public QueryBuilder setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }
    
    public abstract Query getQuery();
}
