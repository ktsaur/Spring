package ru.kpfu.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "travel")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String nameTravel;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(columnDefinition = "TEXT")
    private String transport;

    @Column(columnDefinition = "TEXT")
    private String listOfThings;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getListOfThings() {
        return listOfThings;
    }

    public void setListOfThings(String listOfThings) {
        this.listOfThings = listOfThings;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameTravel() {
        return nameTravel;
    }

    public void setNameTravel(String nameTravel) {
        this.nameTravel = nameTravel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travelId) {
        this.travelId = travelId;
    }

    @Column(columnDefinition = "TEXT")
    private String notes;

}

