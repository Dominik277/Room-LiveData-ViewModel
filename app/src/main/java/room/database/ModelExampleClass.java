package room.database;

public class ModelExampleClass {

    private String obavijest;
    private String autor;
    private String zaKoga;
    private String objavljeno;
    private String doKada;

    public ModelExampleClass(String obavijest, String autor, String zaKoga, String objavljeno, String doKada) {
        this.obavijest = obavijest;
        this.autor = autor;
        this.zaKoga = zaKoga;
        this.objavljeno = objavljeno;
        this.doKada = doKada;
    }

    public String getObavijest() {
        return obavijest;
    }

    public void setObavijest(String obavijest) {
        this.obavijest = obavijest;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getZaKoga() {
        return zaKoga;
    }

    public void setZaKoga(String zaKoga) {
        this.zaKoga = zaKoga;
    }

    public String getObjavljeno() {
        return objavljeno;
    }

    public void setObjavljeno(String objavljeno) {
        this.objavljeno = objavljeno;
    }

    public String getDoKada() {
        return doKada;
    }

    public void setDoKada(String doKada) {
        this.doKada = doKada;
    }
}
