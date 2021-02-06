package cz.radovan.fiktivnyprogram.entity;

import cz.radovan.simpleormframework.anotace.Id;
import cz.radovan.simpleormframework.anotace.Sloupec;
import cz.radovan.simpleormframework.anotace.Tabulka;

@Tabulka("OSOBA")
public class Osoba {
    @Id
    @Sloupec("ID")
    private Long id;

    @Sloupec("JMENO")
    private String jmeno;

    @Sloupec("PRIJMENI")
    private String prijmeni;

    private Integer vek;

    private String adresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public Integer getVek() {
        return vek;
    }

    public void setVek(Integer vek) {
        this.vek = vek;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}