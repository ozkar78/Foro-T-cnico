package com.aluracursos.Proyecto.Foro.domain.comment;

import com.aluracursos.Proyecto.Foro.domain.topic.Topic;
import com.aluracursos.Proyecto.Foro.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 1000)
    private String content;
    
    private LocalDateTime creationDate;
    
    private Boolean active = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    public Comment(String content, Topic topic, User author) {
        this.content = content;
        this.topic = topic;
        this.author = author;
        this.creationDate = LocalDateTime.now();
        this.active = true;
    }

    public void update(String content) {
        if (content != null) this.content = content;
    }

    public void deactivate() {
        this.active = false;
    }
}