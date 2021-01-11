package ehu.isad.controller.db;

import ehu.isad.controller.ui.TxanponKud;
import ehu.isad.model.TxanponModel;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TxanponDBKud {

    private static final TxanponDBKud instance = new TxanponDBKud();
    public static TxanponDBKud getInstance() {
        return instance;
    }

    private TxanponDBKud() {
    }

    public List<TxanponModel> txanponakLortu() {
        String query = "SELECT * FROM txanponak";
        DBKudeatzailea dbKudeatzailea = DBKudeatzailea.getInstance();
        ResultSet rs = dbKudeatzailea.execSQL(query);
        List<TxanponModel> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String data = rs.getString("data");
                float balioa = rs.getFloat("balioa");
                String mota = rs.getString("mota");
                float bolumena = rs.getFloat("bolumena");
                String portaera = portaeraLortu(balioa, mota, id, emaitza);
                TxanponModel t = new TxanponModel(id, mota, data, balioa, bolumena, "irudiak/" + portaera + ".png");
                emaitza.add(t);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return emaitza;
    }
    public String portaeraLortu(float balioa, String mota, int id, List<TxanponModel> txanponLista) {
        int i = txanponLista.size()-1;
        boolean handiago = true;
        String emaitza = "equal";
        while ( i>0 && handiago) {
            if (mota.equals(txanponLista.get(i).getTxanpona()) && txanponLista.get(i).getId()<id) {
                float aurrekoBal = txanponLista.get(i).getZenbat();
                if (aurrekoBal < balioa) {
                    emaitza = "up";
                } else {
                    if (aurrekoBal == balioa) {
                        emaitza = "equal";
                    } else {
                        emaitza = "down";
                    }
                }
                handiago = false;
            }
            i--;
        }
        return emaitza;
    }

    public void datuBaseanSartu(List<TxanponModel> lista) {
        String query = "SELECT * FROM txanponak ORDER BY id DESC LIMIT 1;";
        DBKudeatzailea dbKudeatzailea = DBKudeatzailea.getInstance();
        ResultSet rs = dbKudeatzailea.execSQL(query);
        try {
            if (rs.next()) {int id = rs.getInt("id");
                String data = rs.getString("data");
                float balioa = rs.getFloat("balioa");
                String mota = rs.getString("mota");
                float bolumena = rs.getFloat("bolumena");
                String portaera = portaeraLortu(balioa, mota, id, lista);
                TxanponModel t = new TxanponModel(id, mota, data, balioa, bolumena, "irudiak/" + portaera + ".png");
                for (int i=lista.indexOf(t)+1; i<lista.size(); i++) {
                    TxanponModel txanpona = lista.get(i);
                    String query2 = "INSERT INTO txanponak (id, data, balioa, mota, bolumena) VALUES (" + txanpona.getId() + ", '" + txanpona.getNoiz() + "', " + txanpona.getZenbat() + ", '" + txanpona.getTxanpona() + "', " + txanpona.getBolumena() + ")";
                    dbKudeatzailea. execSQL(query2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
