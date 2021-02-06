package cz.radovan.simpleormframework.reflection;

import cz.radovan.simpleormframework.anotace.Id;
import cz.radovan.simpleormframework.anotace.Sloupec;
import cz.radovan.simpleormframework.anotace.Tabulka;
import cz.radovan.simpleormframework.vyjimky.MissingIdException;
import cz.radovan.simpleormframework.vyjimky.MissingStlpecAnnotationException;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ObjectReflector {

    public static <T> boolean isTable(Class<T> clazz) {
        return clazz.isAnnotationPresent(Tabulka.class);
    }

    public static <T> String getTableName(Class<T> clazz) {
        return clazz.getAnnotation(Tabulka.class).value();
    }

    public static <T> List<String> getColumnNames(Class<T> clazz) {
        List<String> sloupec = new ArrayList<>();
        for(Field f : clazz.getDeclaredFields()){
            if(f.isAnnotationPresent(Sloupec.class)){
                Sloupec stlpec = f.getAnnotation(Sloupec.class);
                sloupec.add(stlpec.value());
                System.out.println("Sloupec: "+stlpec.value());
            }
        }

        return sloupec;
    }

    public static <T> String getIdColumnName(Class<T> clazz) {
        String idColumnName = null;
        for(Field f : clazz.getDeclaredFields()){
            if(f.isAnnotationPresent(Id.class)){
                if(f.isAnnotationPresent(Sloupec.class)){
                    idColumnName = f.getAnnotation(Sloupec.class).value();
                }else {
                    throw new MissingStlpecAnnotationException("Pri hledani ID sa nenasel sloupec anotovany jako Sloupec.");
                }
            }
        }
        if(idColumnName==null)
            throw new MissingIdException("Chyba anotace Id v entite "+clazz.getName());

        System.out.println("Sloupec s ID: "+idColumnName);
        return idColumnName;
    }

    public static <T> T getFilledData(ResultSet result, Class<T> clazz) throws Exception {
        T object = null;
        object = clazz.newInstance();
        for(Field f : object.getClass().getDeclaredFields()){
            f.setAccessible(true);
            if(f.isAnnotationPresent(Sloupec.class)){
                String typElementu = f.getType().getName();
                String nazevSloupce = f.getAnnotation(Sloupec.class).value();
                if(typElementu.equals(String.class.getName())){
                    f.set(object, result.getString(nazevSloupce));
                }else if(typElementu.equals(Long.class.getName())){
                    f.set(object,result.getLong(nazevSloupce));
                }else if(typElementu.equals(Integer.class.getName())){
                    f.set(object,result.getInt(nazevSloupce));
                }
            }
        }
        return object;
    }
}
