package ehu.isad.model;

import ehu.isad.controller.ui.TxanponKud;

public class Txanpona {
    float price;
    float volume;

    public Txanpona(int id, float price, float volume) {
        this.price = price;
        this.volume = volume;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}
