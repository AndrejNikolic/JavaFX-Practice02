/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Andrej
 */
public class mainDZ03 extends Application {
    
    Label labelNo, labelPc, labelRes, labelMove, labelScoreBoard, labelScore, labelGame;
    TextField gamesNo;
    BorderPane board;
    GridPane infoNres;
    Button btnConf, btnBack, btnPlay, btnReset;
    ComboBox<String> moves;
    
    int rezPC, rezTi, ukupno, dozvoljeno, preostalo;
    
    Font labelFont = Font.font("Ubuntu", FontWeight.NORMAL, 14);
    
    @Override
    public void start(Stage primaryStage) {
        
        rezPC = 0;
        rezTi = 0;
        
        // 1 red - Odabir broja partija
        labelNo = new Label();
        labelNo.setText("Unesite broj partija");
        labelNo.setFont(labelFont);
        
        gamesNo = new TextField();
        gamesNo.setPromptText("broj pratija");
        gamesNo.setPrefWidth(50);
        gamesNo.setFont(labelFont);
        
        btnConf = new Button();
        btnConf.setText("Potvrdi");
        btnConf.setPadding(new Insets(5,25,5,25));
        btnConf.setStyle("-fx-color:#28B78D;-fx-text-fill:#fff");
        btnConf.setFont(labelFont);
        
        // 2 red - Odabir poteza i odigravanje
        moves = new ComboBox<>();
        moves.getItems().addAll("Kamen","Papir","Makaze");
        moves.setValue("Kamen");
        moves.setStyle("-fx-font: 14px \"Ubuntu\";");
        
        labelMove = new Label();
        labelMove.setText("Izaberite Vaš potez");
        labelMove.setFont(labelFont);
        
        btnPlay = new Button();
        btnPlay.setText("Odigraj");
        btnPlay.setDisable(true);
        btnPlay.setPadding(new Insets(5,25,5,25));
        btnPlay.setStyle("-fx-color:#28B78D;-fx-text-fill:#fff");
        btnPlay.setFont(labelFont);
        
        // 3 red - potez kompa i rezultat
        labelPc = new Label();
        labelPc.setFont(labelFont);
        //labelPc.setText("Komp je odigrao:" + Math.round(Math.random()*3));
        labelRes = new Label();
        labelRes.setFont(labelFont);
        labelScore = new Label();
        labelScore.setFont(labelFont);
        
        // 4 red - rezultat
        labelScoreBoard = new Label();
        labelScoreBoard.setFont(labelFont);
        labelGame = new Label();
        labelGame.setFont(labelFont);
        labelScoreBoard.setText("Rezultat: " + rezPC + " : " + rezTi);
        btnReset = new Button();
        btnReset.setText("Reset");
        btnReset.setPadding(new Insets(5,25,5,25));
        btnReset.setStyle("-fx-color:#a70532;-fx-text-fill:#fff");
        btnReset.setFont(labelFont);
        
        ukupno = 0;
        
        btnConf.setOnAction(e -> {
            dozvoljeno = Integer.parseInt(gamesNo.getText());
            preostalo = Integer.parseInt(gamesNo.getText());
            btnPlay.setDisable(false);
            btnConf.setDisable(true);
            labelGame.setText("Preostalo: " + preostalo);
        });
        
        
        
        btnPlay.setOnAction(e ->{
            recunajRez();
        });
        
        btnReset.setOnAction(e ->{
            btnConf.setDisable(false);
            btnPlay.setDisable(true);
            rezPC = 0;
            rezTi = 0;
            ukupno = 0;
            labelScoreBoard.setText("Rezultat: " + rezPC + " : " + rezTi);
            labelGame.setText("");
            labelPc.setText("");
            labelRes.setText("");
            labelScore.setText("");
        });
        
        
        
        infoNres = new GridPane();
        infoNres.add(labelNo, 0, 0);
        infoNres.add(gamesNo, 1, 0);
        infoNres.add(btnConf, 2, 0);
        infoNres.setHgap(10);
        infoNres.setAlignment(Pos.CENTER);
        infoNres.add(moves, 1, 1);
        infoNres.add(labelMove, 0, 1);
        infoNres.add(btnPlay, 2, 1);
        infoNres.setVgap(20);
        infoNres.add(labelPc, 0, 2);
        infoNres.add(labelRes, 1, 2);
        infoNres.add(labelScore, 2, 2);
        infoNres.add(labelScoreBoard, 1, 3);
        infoNres.add(labelGame, 0, 3);
        infoNres.add(btnReset, 2, 3);
        infoNres.setHalignment(labelPc, HPos.CENTER);
        infoNres.setHalignment(labelRes, HPos.CENTER);
        infoNres.setHalignment(labelScore, HPos.CENTER);
        infoNres.setHalignment(labelGame, HPos.CENTER);
        infoNres.setHalignment(labelScoreBoard, HPos.CENTER);
        infoNres.setHalignment(btnReset, HPos.CENTER);
        
        Scene scene = new Scene(infoNres, 500, 300);
        
        primaryStage.setTitle("Domaći zadatak 3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void recunajRez() {
        double randomNO = Math.round(Math.random()*2);
            String rezultatPc = "";
            if (randomNO == 0){
                rezultatPc = "Kamen";
            }
            else if (randomNO == 1){
                rezultatPc = "Papir";
            }
            else if (randomNO == 2){
                rezultatPc = "Makaze";
            };
            
            if (rezultatPc == moves.getValue()){
                labelScore.setText("Nerešeno");
            }
            else if (rezultatPc == "Kamen"){
                if (moves.getValue() == "Papir"){
                    labelScore.setText("Pobedio si");
                    rezTi = rezTi + 1;
                }
                else {
                    labelScore.setText("Izgubio si");
                    rezPC = rezPC + 1;
                }
            }
            else if (rezultatPc == "Papir"){
                if (moves.getValue() == "Makaze"){
                    labelScore.setText("Pobedio si");
                    rezTi = rezTi + 1;
                }
                else {
                    labelScore.setText("Izgubio si");
                    rezPC = rezPC + 1;
                }
            }
            else if (rezultatPc == "Makaze"){
                if (moves.getValue() == "Kamen"){
                    labelScore.setText("Pobedio si");
                    rezTi = rezTi + 1;
                }
                else {
                    labelScore.setText("Izgubio si");
                    rezPC = rezPC + 1;
                }
            }
            ukupno = ukupno + 1;
            preostalo = preostalo - 1;
            if(dozvoljeno == ukupno){
                btnPlay.setDisable(true);
                if (rezTi>rezPC){
                    labelScore.setText("Ti si pobednik!!!");
                }
                else {
                    labelScore.setText("Slabo to nešto...");
                }
            }
            labelPc.setText("Komp: " + rezultatPc);
            labelRes.setText("Ti: " + moves.getValue());
            labelScoreBoard.setText("Rezultat: " + rezPC + " : " + rezTi);
            labelGame.setText("Preostalo: " + preostalo);
    }
    
}
