package com.example.testavimas1.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Vartotojas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String telNr;
    private String email;
    private String address;
    private String password;



    public Vartotojas(String name, String telNr) {
        this.name = name;
        this.telNr = telNr;
    }

    public int compareTo(Vartotojas o) {
        return Long.compare(this.id, o.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vartotojas other = (Vartotojas) obj;
        if (this.id != other.id)
            return false;
        return true;
    }
}
