package cz.radovan.fiktivnyprogram;

import cz.radovan.fiktivnyprogram.entity.Film;
import cz.radovan.fiktivnyprogram.entity.Osoba;
import cz.radovan.simpleormframework.dbaccess.SormManager;

public class Main {

    public static void main(String[] args) throws Exception {
        SormManager manager = new SormManager();
        Osoba osoba = manager.getEntityByid(1L, Osoba.class);
//        Film f = manager.getEntityByid(0L, Film.class);
        System.out.println(osoba.getId());
        System.out.println(osoba.getJmeno());
        System.out.println(osoba.getPrijmeni());
        System.out.println(osoba.getVek());
        System.out.println(osoba.getAdresa());

        Film film = new Film();
        film.setNazev("Fireproof");
        film.setReziser("Alex Kendrick");
        film.setvHlavniUloze("Kirk Cameron, Erin Bethea");
        manager.insertEntity(film);


    }
}
