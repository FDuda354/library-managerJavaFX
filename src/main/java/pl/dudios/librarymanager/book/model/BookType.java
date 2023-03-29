package pl.dudios.librarymanager.book.model;

import java.util.List;

public enum BookType {
    FICTION("Fikcja"),
    NON_FICTION("Literatura faktu"),
    HORROR("Horror"),
    THRILLER("Thriller"),
    MYSTERY("Kryminał"),
    ROMANCE("Romans"),
    FANTASY("Fantasy"),
    BIOGRAPHY("Biografia"),
    DRAMA("Dramat");

    private final String value;

    BookType(String value) {
        this.value = value;
    }

    public static List<String> getValueList() {
        return List.of(
                FICTION.getValue(),
                NON_FICTION.getValue(),
                HORROR.getValue(),
                THRILLER.getValue(),
                MYSTERY.getValue(),
                ROMANCE.getValue(),
                FANTASY.getValue(),
                BIOGRAPHY.getValue(),
                DRAMA.getValue()
        );
    }

    public String getValue() {
        return value;
    }


}
