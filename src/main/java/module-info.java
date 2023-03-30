module pl.dudios.librarymanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jbcrypt;
    exports pl.dudios.librarymanager.login;

    opens pl.dudios.librarymanager to javafx.fxml;
    opens pl.dudios.librarymanager.login to javafx.fxml;
    exports pl.dudios.librarymanager;
    exports pl.dudios.librarymanager.book.model.fx to javafx.fxml;
    exports pl.dudios.librarymanager.login.user.model.fx to javafx.fxml;
    exports pl.dudios.librarymanager.book.rentals.model.fx to javafx.fxml;

    opens pl.dudios.librarymanager.login.user.model to org.hibernate.orm.core;
    opens pl.dudios.librarymanager.book.model to org.hibernate.orm.core;
    opens pl.dudios.librarymanager.book.rentals.model to org.hibernate.orm.core;
    exports pl.dudios.librarymanager.main.admin;
    opens pl.dudios.librarymanager.main.admin to javafx.fxml;
    exports pl.dudios.librarymanager.main.user;
    opens pl.dudios.librarymanager.main.user to javafx.fxml;
    exports pl.dudios.librarymanager.main.admin.book;
    opens pl.dudios.librarymanager.main.admin.book to javafx.fxml;
}
