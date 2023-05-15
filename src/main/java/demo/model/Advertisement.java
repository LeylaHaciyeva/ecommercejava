package demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Objects;

@Entity(name="advertisement")
@Document(indexName = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private Long id;
    @Field(type = FieldType.Keyword)
    private String title;
    @Field(type = FieldType.Keyword)
    private String description;
    private Double price;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date lastModifiedDate;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date creationDate;
    private Long userId;




    public Advertisement(Long id, String title, String description, Double price, Date lastModifiedDate, Date creationDate, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.lastModifiedDate = lastModifiedDate;
        this.creationDate = creationDate;
        this.userId = userId;
    }

    public Advertisement() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(creationDate, that.creationDate) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, lastModifiedDate, creationDate, userId);
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", lastModifiedDate=" + lastModifiedDate +
                ", creationDate=" + creationDate +
                ", userId=" + userId +
                '}';
    }
}
