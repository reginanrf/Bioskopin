package models;

/**
 * @author Bioskopin Team
 */
public class Studio {

    // PROPERTI (Berkorelasi dengan kolom di tabel `studios`)

    private int    idStudio;
    private String namaStudio;
    private int    jumlahBaris;
    private int    jumlahKolom;

    // CONSTRUCTOR
   
    public Studio() {}

    public Studio(int idStudio, String namaStudio, int jumlahBaris, int jumlahKolom) {
        this.idStudio    = idStudio;
        this.namaStudio  = namaStudio;
        this.jumlahBaris = jumlahBaris;
        this.jumlahKolom = jumlahKolom;
    }

    public Studio(String namaStudio, int jumlahBaris, int jumlahKolom) {
        this.namaStudio  = namaStudio;
        this.jumlahBaris = jumlahBaris;
        this.jumlahKolom = jumlahKolom;
    }

    // GETTER & SETTER
  
    public int getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(int idStudio) {
        this.idStudio = idStudio;
    }

    public String getNamaStudio() {
        return namaStudio;
    }

    public void setNamaStudio(String namaStudio) {
        this.namaStudio = namaStudio;
    }

    public int getJumlahBaris() {
        return jumlahBaris;
    }

    public void setJumlahBaris(int jumlahBaris) {
        this.jumlahBaris = jumlahBaris;
    }

    public int getJumlahKolom() {
        return jumlahKolom;
    }

    public void setJumlahKolom(int jumlahKolom) {
        this.jumlahKolom = jumlahKolom;
    }

    public int getKapasitas() {
        return jumlahBaris * jumlahKolom;
    }

    @Override
    public String toString() {
        return namaStudio;
    }
}
