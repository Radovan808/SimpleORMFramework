package cz.radovan.fiktivnyprogram.entity;

import cz.radovan.simpleormframework.anotace.Id;
import cz.radovan.simpleormframework.anotace.Sloupec;
import cz.radovan.simpleormframework.anotace.Tabulka;

@Tabulka("MOVIE")
public class Film {
    @Id
    @Sloupec("ID")
    private Long id;

    @Sloupec("NAZEV_FILMU")
    private String nazev;

    @Sloupec("REZISER")
    private String reziser;

    @Sloupec("HLAVNI_ULOHA")
    private String vHlavniUloze;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getReziser() {
        return reziser;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public String getvHlavniUloze() {
        return vHlavniUloze;
    }

    public void setvHlavniUloze(String vHlavniUloze) {
        this.vHlavniUloze = vHlavniUloze;
    }
}
