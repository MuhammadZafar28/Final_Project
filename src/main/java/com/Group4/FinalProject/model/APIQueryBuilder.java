/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group4.FinalProject.model;

/**
 * A builder class for creating API queries to the DOHMH restaurant inspection database.
 * @author ndars
 */
public class APIQueryBuilder extends QueryBuilder {
    @Override
    public Query getQuery() {
        return new APIQuery(dba, grade, foodType, address, borough, zipCode);
    }
}