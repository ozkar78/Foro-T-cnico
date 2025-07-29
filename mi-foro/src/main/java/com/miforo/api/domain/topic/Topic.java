package com.miforo.api.domain.topic;

import com.miforo.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @Column(length = 1000)
    private String message;
    
    private LocalDateTime creationDate;
    
    private Boolean active = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    public Topic(String title, String message, User author) {
        this.title = title;
        this.message = message;
        this.author = author;
        this.creationDate = LocalDateTime.now();
        this.active = true;
    }

    public void update(String title, String message) {
        if (title != null) this.title = title;
        if (message != null) this.message = message;
    }

    public void deactivate() {
        this.active = false;
    }
}