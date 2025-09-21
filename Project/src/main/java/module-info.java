module org.example.setgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires static lombok;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.xml;
    requires java.desktop;
    requires org.jboss.logging;
    requires java.sql;
    requires java.logging;
    requires jakarta.persistence;
    requires jakarta.inject;
    requires org.hibernate.commons.annotations;
    requires net.bytebuddy;



    requires jakarta.transaction;
    requires com.fasterxml.classmate;
    requires java.security.jgss;
    requires jakarta.xml.bind;

    requires java.management;

    requires java.security.sasl;
    requires jakarta.cdi;
    requires java.transaction.xa;
    requires java.smartcardio;
    requires javafx.media;

    opens org.example.setgame.ent to org.hibernate.orm.core;
    opens org.example.setgame to javafx.fxml;
    exports org.example.setgame;
    opens org.example.setgame.ent.enums to org.hibernate.orm.core;


}