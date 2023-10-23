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

        if(lvMatieres.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix de la matière");
            alert.setHeaderText("");
            alert.setContentText("Sélectionner une matière");
            alert.showAndWait();
        }
        else if(lvDevoirs.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix du devoir");
            alert.setHeaderText("");
            alert.setContentText("Sélectionner un devoir");
            alert.showAndWait();
        }
        else if(txtNomEtudiant.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix de l'étudiant");
            alert.setHeaderText("");
            alert.setContentText("Saisir un étudiant");
            alert.showAndWait();
        }
        else if(txtNote.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix de la note");
            alert.setHeaderText("");
            alert.setContentText("Saisir une noet");
            alert.showAndWait();
        }
        else

        {



            String matieres = lvMatieres.getSelectionModel().getSelectedItem().toString();
            String devoirs = lvDevoirs.getSelectionModel().getSelectedItem().toString();
            String trimestre = cboTrimestres.getSelectionModel().getSelectedItem().toString();
            String nomEtudiant = txtNomEtudiant.getText();
            int note = Integer.parseInt(txtNote.getText());

            Etudiant etudiant = new Etudiant(nomEtudiant, note);



            if (!lesBulletins.containsKey(matieres))
            {
                lesBulletins.put(matieres, new HashMap<>());
            }

            if (!lesBulletins.get(matieres).containsKey(devoirs))
            {
                lesBulletins.get(matieres).put(devoirs, new HashMap<>());
            }

            if (!lesBulletins.get(matieres).get(devoirs).containsKey(trimestre))
            {
                lesBulletins.get(matieres).get(devoirs).put(trimestre, new ArrayList<>());
            }

            lesBulletins.get(matieres).get(devoirs).get(trimestre).add(etudiant);


            if (!lvMatieresMoyenne.getItems().contains(matieres)) {
                lvMatieresMoyenne.getItems().add(matieres);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note affectée");
            alert.setHeaderText("");
            alert.setContentText("Note enregistrée");
            alert.showAndWait();

            txtNote.clear();
            txtNomEtudiant.clear();



        }

    }

    @FXML
    public void lvMatieresMoyenneClicked(Event event)
    {


        // A vous de jouer
        String matieres = lvMatieresMoyenne.getSelectionModel().getSelectedItem().toString();

        if (matieres != null) {
            TreeItem<String> rootDevoirs = new TreeItem<>("Par devoirs");

            if (lesBulletins.containsKey(matieres)) {
                HashMap<String, Integer> moyenneNotesParDevoirs = new HashMap<>();

                for (String devoirs : lesBulletins.get(matieres).keySet()) {
                    int nombreNote = 0;
                    for (String trimestre : lesBulletins.get(matieres).get(devoirs).keySet()) {
                        double moyenneDevoirs = 0;
                        for (Etudiant etudiant : lesBulletins.get(matieres).get(devoirs).get(trimestre)) {
                            nombreNote += 1;
                            moyenneDevoirs = ( moyenneDevoirs + etudiant.getNote() ) / nombreNote;

                        }
                        moyenneNotesParDevoirs.put(devoirs, (int) Math.round(moyenneDevoirs));

                    }
                }
                for (String devoirs : moyenneNotesParDevoirs.keySet()) {
                    String moyenneNotes = devoirs + " : " + moyenneNotesParDevoirs.get(devoirs);
                    TreeItem<String> noteItem = new TreeItem<>(moyenneNotes);

                    noteItem.setExpanded(true);

                    rootDevoirs.getChildren().add(noteItem);
                }


                tvMoyennesParDevoirs.setRoot(rootDevoirs);
                rootDevoirs.setExpanded(true);

            }
        }



    }
}