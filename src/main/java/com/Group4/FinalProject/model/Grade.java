/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group4.FinalProject.model;

/**
 *
 * @author ndars
 */
public enum Grade {
    A,
    B,
    C,
    NOT_YET_GRADED,
    GRADE_PENDING,
    GRADE_PENDING_ISSUED_ON_REOPENING;
    
    @Override
    public String toString() {
        switch(this) {
            case A: return "A";
            case B: return "B";
            case C: return "C";
            case NOT_YET_GRADED: return "Not yet graded";
            case GRADE_PENDING: return "Grade pending";
            case GRADE_PENDING_ISSUED_ON_REOPENING: return "Grade pending issued on reopening";
            default: throw new IllegalArgumentException();
        }
    }
    
    public String inQueryFormat() {
        switch(this) {
            case A: return "A";
            case B: return "B";
            case C: return "C";
            case NOT_YET_GRADED: return "N";
            case GRADE_PENDING: return "Z";
            case GRADE_PENDING_ISSUED_ON_REOPENING: return "P";
            default: throw new IllegalArgumentException();
        }
    }
}
