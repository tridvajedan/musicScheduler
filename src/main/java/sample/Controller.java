package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Controller {
    @FXML
    public TextField sat11;
    @FXML
    public TextField sat12;
    @FXML
    public TextField sat13;
    @FXML
    public TextField sat14;
    @FXML
    public TextField sat15;
    @FXML
    public TextField sat16;
    @FXML
    public TextField sat21;
    @FXML
    public TextField sat22;
    @FXML
    public TextField sat23;
    @FXML
    public TextField sat24;
    @FXML
    public TextField sat25;
    @FXML
    public TextField sat26;
    @FXML
    public TextField minut11;
    @FXML
    public TextField minut12;
    @FXML
    public TextField minut13;
    @FXML
    public TextField minut14;
    @FXML
    public TextField minut15;
    @FXML
    public TextField minut16;
    @FXML
    public TextField minut21;
    @FXML
    public TextField minut22;
    @FXML
    public TextField minut23;
    @FXML
    public TextField minut24;
    @FXML
    public TextField minut25;
    @FXML
    public TextField minut26;
    @FXML
    public TextField  vreme11;
    @FXML
    public TextField  vreme12;
    @FXML
    public TextField  vreme13;
    @FXML
    public TextField  vreme14;
    @FXML
    public TextField  vreme15;
    @FXML
    public TextField vreme16;
    @FXML
    public TextField vreme21;
    @FXML
    public TextField vreme22;
    @FXML
    public TextField vreme23;
    @FXML
    public TextField  vreme24;
    @FXML
    public TextField  vreme25;
    @FXML
    public TextField  vreme26;
    @FXML
    public ListView playlist;
    @FXML
    public Button updateButton;
    @FXML
    public Label status;
    @FXML
    public Button minimizeButton;
    @FXML
    public Label nowSong;
    @FXML
    public TextField  vreme27;
    @FXML
    public TextField  minut27;
    @FXML
    public TextField  minut17;
    @FXML
    public TextField  vreme17;
    @FXML
    public TextField  sat17;
    @FXML
    public TextField  sat27;


    public MediaPlayer mediaPlayer;
    public int songCounter = 0;
    public boolean isRunning = false;

    public TextField[] times = {vreme11, vreme12, vreme13, vreme14, vreme15, vreme16, vreme21, vreme22, vreme23, vreme24, vreme25, vreme26,vreme17,vreme27};
    public TextField[] hours = {sat11, sat12, sat13, sat14, sat15, sat16, sat21, sat22, sat23, sat24, sat25, sat26,sat27,sat17};
    public TextField[] minutes = {minut11, minut12, minut13, minut14, minut15, minut16, minut21, minut22, minut23, minut24, minut25, minut26,minut17,minut27};
    public ArrayList<String> songs = new ArrayList<>();
    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true) {
                for (int i = 0; i < times.length; i++) {
                    if (!hours[i].getText().equals("") && !minutes[i].getText().equals("")) {
                        Date d = new Date();
                        d.setHours(Integer.parseInt(hours[i].getText().trim()));
                        d.setMinutes(Integer.parseInt(minutes[i].getText().trim()));
                        if (d.equals(new Date())) {
                            try {
                                System.out.println(Long.parseLong(times[i].getText()) * 60000);
                                playMusic();
                                Thread.sleep((Long.parseLong(times[i].getText())) * 60000);
                                stopMusic();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                }
        }
    });

    public Thread playlistThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true)
            {
                Media hit = new Media(new File(songs.get(songCounter)).toURI().toString());
                mediaPlayer = new MediaPlayer(hit);
                mediaPlayer.play();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println((long)mediaPlayer.getMedia().getDuration().toMillis());
                    Thread.sleep((long)mediaPlayer.getMedia().getDuration().toMillis()-1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                songCounter++;
                if(songCounter >= songs.size())
                {
                    songCounter = 0;
                }
                String[] splits = songs.get(songCounter).split("\\\\");
                nowSong.setText("Trenutno: " + splits[splits.length-1]);
            }
        }
    });


    public void update()
    {
         TextField[] times = {vreme11, vreme12, vreme13, vreme14, vreme15, vreme16, vreme21, vreme22, vreme23, vreme24, vreme25, vreme26,vreme17,vreme27};
         TextField[] hours = {sat11, sat12, sat13, sat14, sat15, sat16, sat21, sat22, sat23, sat24, sat25, sat26,sat17,sat27};
         TextField[] minutes = {minut11, minut12, minut13, minut14, minut15, minut16, minut21, minut22, minut23, minut24, minut25, minut26,minut17,minut27};
        if(!thread.isAlive())
        {
            log("thread","Thread started");
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                            for (int i = 0; i < times.length; i++) {
                                if (!hours[i].getText().equals("") && !minutes[i].getText().equals("")) {
                                    Date d = new Date();
                                    d.setHours(Integer.parseInt(hours[i].getText().trim()));
                                    d.setMinutes(Integer.parseInt(minutes[i].getText().trim()));
                                    if (d.equals(new Date())) {
                                        try {
                                            System.out.println(Long.parseLong(times[i].getText()) * 60000);
                                            playMusic();
                                            Thread.sleep(Long.parseLong(times[i].getText()) * 60000);
                                            stopMusic();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                    }
                }
            });
            thread.start();
        }
        isRunning = !isRunning;
        if(isRunning)
        {
            status.setText("Status: Upaljen");
        }
        else
        {
            status.setText("Status: Pauziran");
            thread.stop();
            stopMusic();
        }
    }

    public void exit()
    {
        System.exit(44);
    }

    public void shuffle()
    {
        Collections.shuffle(songs);
        playlist.setItems(FXCollections.observableArrayList(songs));
    }

    public void minimize()
    {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    public void saveConfig()
    {
         TextField[] times = {vreme11, vreme12, vreme13, vreme14, vreme15, vreme16, vreme21, vreme22, vreme23, vreme24, vreme25, vreme26,vreme17,vreme27};
         TextField[] hours = {sat11, sat12, sat13, sat14, sat15, sat16, sat21, sat22, sat23, sat24, sat25, sat26,sat27,sat17};
         TextField[] minutes = {minut11, minut12, minut13, minut14, minut15, minut16, minut21, minut22, minut23, minut24, minut25, minut26,minut17,minut27};
            String s = "";
            for(int i = 0; i < hours.length;i++) {
                s += times[i].getText() + "," + hours[i].getText() + "," + minutes[i].getText();
                s += "\n";
            }
        try {
            FileChooser chooser = new FileChooser();chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)","*.csv"));
            PrintWriter out = new PrintWriter(chooser.showSaveDialog(null));
            out.print(s);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig()
    {
         TextField[] times = {vreme11, vreme12, vreme13, vreme14, vreme15, vreme16, vreme21, vreme22, vreme23, vreme24, vreme25, vreme26,vreme17,vreme27};
         TextField[] hours = {sat11, sat12, sat13, sat14, sat15, sat16, sat21, sat22, sat23, sat24, sat25, sat26,sat27,sat17};
         TextField[] minutes = {minut11, minut12, minut13, minut14, minut15, minut16, minut21, minut22, minut23, minut24, minut25, minut26,minut17,minut27};
        try {

            FileChooser chooser = new FileChooser();chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)","*.csv"));
            Scanner sc = new Scanner(chooser.showOpenDialog(null));
            for(int i = 0; i < times.length;i++)
            {
                String[] splits = sc.nextLine().split(",");
                times[i].setText(splits[0]);hours[i].setText(splits[1]);minutes[i].setText(splits[2]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void playMusic()
    {
        playlistThread.start();
    }

    public void stopMusic() {
            playlistThread.stop();
            if(mediaPlayer != null) {
                mediaPlayer.pause();
            }
            playlistThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Media hit = new Media(new File(songs.get(songCounter)).toURI().toString());
                        mediaPlayer = new MediaPlayer(hit);
                        mediaPlayer.play();

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            System.out.println((long) mediaPlayer.getMedia().getDuration().toMillis());
                            Thread.sleep((long) mediaPlayer.getMedia().getDuration().toMillis() - 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        songCounter++;
                        if (songCounter >= songs.size()) {
                            songCounter = 0;
                        }
                    }
                }
            });
    }
    public void addSong()
    {
        FileChooser chooser = new FileChooser();chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 files (*.mp3)","*.mp3"));

        File f = chooser.showOpenDialog(null);
        if(f !=null)
        {
        songs.add(f.getAbsolutePath());
        playlist.setItems(FXCollections.observableArrayList(songs));
        playlist.setStyle("-fx-control-inner-background:  #5b5959;");
        String[] splits = songs.get(songCounter).split("\\\\");
            nowSong.setText("Trenutno: " + splits[splits.length-1]);
        }
    }


    public void removeSong()
    {
        songs.remove(playlist.getSelectionModel().getSelectedIndex());
        playlist.setItems(FXCollections.observableArrayList(songs));
    }
    public void setSong()
    {
        if(playlist.getSelectionModel()!= null) {
            songCounter = playlist.getSelectionModel().getSelectedIndex();
            String[] splits = songs.get(songCounter).split("\\\\");
            nowSong.setText("Trenutno: " + splits[splits.length-1]);
        }
    }


    public void log(String tag,String text)
    {
        System.out.println("["+tag.toUpperCase(Locale.ROOT)+"] " + text);
    }
}
