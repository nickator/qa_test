package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "note")
    private String note;

    @Column(name = "user_source_id")
    private Long userSourceId;

    @Column(name = "user_target_id")
    private Long userTargetId;

    public Transactions() {
    }

    public Transactions(Double amount, LocalDateTime dateCreated, String note, Long userSourceId, Long userTargetId) {
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.note = note;
        this.userSourceId = userSourceId;
        this.userTargetId = userTargetId;
    }

    // Gettery a Settery

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getUserSourceId() {
        return userSourceId;
    }

    public void setUserSourceId(Long userSourceId) {
        this.userSourceId = userSourceId;
    }

    public Long getUserTargetId() {
        return userTargetId;
    }

    public void setUserTargetId(Long userTargetId) {
        this.userTargetId = userTargetId;
    }
}

