package sio.bulletin;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sio.bulletin.Model.Etudiant;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class BulletinController implements Initializable
{
    DecimalFormat df;
    HashMap<String,HashMap<String, HashMap<String,ArrayList<Etudiant>>>> lesBulletins;
    @FXML
    private AnchorPane apBulletin;
    @FXML
    private ListView lvMatieres;
    @FXML
    private ListView lvDevoirs;
    @FXML
    private ComboBox cboTrimestres;
    @FXML
    private TextField txtNomEtudiant;
    @FXML
    private TextField txtNote;
    @FXML
    private Button btnValider;
    @FXML
    private AnchorPane apMoyenne;
    @FXML
    private Button btnMenuBulletin;
    @FXML
    private Button btnMenuMoyenne;
    @FXML
    private ListView lvMatieresMoyenne;
    @FXML
    private TreeView tvMoyennesParDevoirs;
    @FXML
    private TextField txtMajor;
    @FXML
    private TextField txtNoteMaxi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        apBulletin.toFront();
        df = new DecimalFormat("#.##");
        lesBulletins = new HashMap<>();
        lvMatieres.getItems().addAll("Maths","Anglais","Economie");
        lvDevoirs.getItems().addAll("Devoir n°1","Devoir n°2","Devoir n°3","Devoir n°4");
        cboTrimestres.getItems().addAll("Trim 1","Trim 2","Trim 3");
        cboTrimestres.getSelectionModel().selectFirst();
    }

    @FXML
    public void btnMenuClicked(Event event)
    {
        if(event.getSource()==btnMenuBulletin)
        {
            apBulletin.toFront();
        }
        else if(event.getSource()==btnMenuMoyenne)
        {
            apMoyenne.toFront();
        }
    }

    @FXML
    public void btnValiderClicked(Event event)
    {
        // A vous de jouer

    }

    @FXML
    public void lvMatieresMoyenneClicked(Event event)
    {
        // A vous de jouer

    }
}