package com.aluracursos.Proyecto.Foro.domain.topic;

public enum TopicStatus {
    OPEN("Abierto"),
    IN_PROGRESS("En Progreso"),
    RESOLVED("Resuelto"),
    CLOSED("Cerrado"),
    ARCHIVED("Archivado");

    private final String displayName;

    TopicStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}