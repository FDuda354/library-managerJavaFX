package pl.dudios.librarymanager.book.model;

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

    public String getValue() {
        return value;
    }
}
