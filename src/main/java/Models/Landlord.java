package Models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "landlords")
public class Landlord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name="";
    private String phone="";
    @OneToMany(mappedBy = "landlord")
    private List<Apartment> apartments;

    public Landlord(){}

    public Landlord(String name, String phone, List<Apartment> apartments) {
        this.name = name;
        this.phone = phone;
        this.apartments = apartments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }
}
