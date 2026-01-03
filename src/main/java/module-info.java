module ec.edu.espoch.gestornotasfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espoch.gestornotasfx to javafx.fxml;
    opens ec.edu.espoch.gestornotasfx.controller to javafx.fxml;
    exports ec.edu.espoch.gestornotasfx;
    exports ec.edu.espoch.gestornotasfx.controller;
    exports ec.edu.espoch.gestornotasfx.model;
    exports ec.edu.espoch.gestornotasfx.model.asig_estu;
    exports ec.edu.espoch.gestornotasfx.model.asignatura;
    exports ec.edu.espoch.gestornotasfx.model.docentes;
    exports ec.edu.espoch.gestornotasfx.model.estudiantes;
    
}
