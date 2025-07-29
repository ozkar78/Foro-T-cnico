package com.aluracursos.Proyecto.Foro.domain.topic;

import com.aluracursos.Proyecto.Foro.domain.user.User;
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
    
    @Column(columnDefinition = "varchar(255) default 'General'")
    private String category = "General";
    
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('OPEN','CLOSED','RESOLVED') default 'OPEN'")
    private TopicStatus status = TopicStatus.OPEN;
    
    private LocalDateTime creationDate;
    
    private LocalDateTime lastModified;
    
    @Column(columnDefinition = "integer default 0")
    private Integer views = 0;
    
    private Boolean active = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    public Topic(String title, String message, String category, User author) {
        this.title = title;
        this.message = message;
        this.category = category;
        this.author = author;
        this.creationDate = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.active = true;
        this.views = 0;
        this.status = TopicStatus.OPEN;
    }

    public void update(String title, String message, String category) {
        if (title != null) this.title = title;
        if (message != null) this.message = message;
        if (category != null) this.category = category;
        this.lastModified = LocalDateTime.now();
    }
    
    public void incrementViews() {
        this.views++;
    }
    
    public void updateStatus(TopicStatus status) {
        this.status = status;
        this.lastModified = LocalDateTime.now();
    }

    public void deactivate() {
        this.active = false;
    }
}