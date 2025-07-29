package com.aluracursos.Proyecto.Foro.domain.topic;

public enum TopicCategory {
    JAVA("Java"),
    SPRING("Spring Framework"),
    DATABASE("Base de Datos"),
    FRONTEND("Frontend"),
    BACKEND("Backend"),
    DEVOPS("DevOps"),
    MOBILE("Desarrollo Móvil"),
    GENERAL("General"),
    HELP("Ayuda"),
    TUTORIAL("Tutorial");

    private final String displayName;

    TopicCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}