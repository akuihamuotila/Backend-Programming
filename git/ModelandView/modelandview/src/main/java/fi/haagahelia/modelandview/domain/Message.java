package fi.haagahelia.modelandview.domain;

public class Message {
    private String otsikko;
    private String sisalto;

    // Konstruktori
    public Message(String otsikko, String sisalto) {
        this.otsikko = otsikko;
        this.sisalto = sisalto;
    }

    // Getterit ja setterit
    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public String getSisalto() {
        return sisalto;
    }

    public void setSisalto(String sisalto) {
        this.sisalto = sisalto;
    }
}