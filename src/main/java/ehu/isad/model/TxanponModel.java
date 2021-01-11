package ehu.isad.model;

import javafx.scene.image.Image;

public class TxanponModel {

    private Integer id;
    private String txanpona;
    private String noiz;
    private Float zenbat;
    private Float bolumena;
    private Image portaera;

    public TxanponModel(int id, String txanpona, String noiz, float zenbat, float bolumena, String imagepath) {
        this.id = id;
        this.txanpona = txanpona;
        this.noiz = noiz;
        this.zenbat = zenbat;
        this.bolumena = bolumena;
        this.portaera = new Image(imagepath);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTxanpona() {
        return txanpona;
    }

    public void setTxanpona(String txanpona) {
        this.txanpona = txanpona;
    }

    public String getNoiz() {
        return noiz;
    }

    public void setNoiz(String noiz) {
        this.noiz = noiz;
    }

    public Float getZenbat() {
        return zenbat;
    }

    public void setZenbat(Float zenbat) {
        this.zenbat = zenbat;
    }

    public Float getBolumena() {
        return bolumena;
    }

    public void setBolumena(Float bolumena) {
        this.bolumena = bolumena;
    }

    public Image getPortaera() {
        return portaera;
    }

    public void setPortaera(String portaera) {
        this.portaera = new Image(portaera);
    }
}
