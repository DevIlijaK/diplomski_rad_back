package com.diplomski.obavestavanje.nastavnika.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class Person {
    private String email;
    private String name;
    private String surname;
}
