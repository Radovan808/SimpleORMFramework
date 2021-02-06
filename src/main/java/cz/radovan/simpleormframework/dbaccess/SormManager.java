package cz.radovan.simpleormframework.dbaccess;

import cz.radovan.simpleormframework.sql.SqlBuilder;
import cz.radovan.simpleormframework.vyjimky.AnnotationMissingException;
import cz.radovan.simpleormframework.reflection.ObjectReflector;

import java.sql.ResultSet;
import java.util.List;

public class SormManager {

    public <T> T getEntityByid(Long id, Class<T> clazz) throws Exception {

        T o = null;
        if(id == null){
            throw new IllegalArgumentException("id nemuze byt prazdne");
        }

        if(!ObjectReflector.isTable(clazz)){
            throw new AnnotationMissingException("Objekt nemá anotaci Tabulka.");
        }else{
            System.out.println("is table "+clazz.getName());
        }


        DatabaseAccess databaseAccess = null;
        ResultSet rs = null;
        try{
            databaseAccess = new DatabaseAccess();
            //nacitame data z db
            rs = loadData(id,clazz,databaseAccess);
            //nasetujeme na objekt

            if(rs.next())
                o = ObjectReflector.getFilledData(rs,clazz);

            rs.close();
            databaseAccess.commit();
        }catch (Exception e){
            if(databaseAccess!=null){
                databaseAccess.rollBack();
            }
            throw new Exception(e);
        }finally {
            if(databaseAccess!=null) {
                databaseAccess.disconnect();
            }
        }

        return o;
    }

    private <T> ResultSet loadData(Long id, Class<T> clazz, DatabaseAccess databaseAccess) throws Exception {
        String tableName = ObjectReflector.getTableName(clazz);
        List<String> tableColumns = ObjectReflector.getColumnNames(clazz);
        String idColumnName = ObjectReflector.getIdColumnName(clazz);

        String query = SqlBuilder.buildQuery(id,tableName,idColumnName,tableColumns);
        System.out.println(query);
        return databaseAccess.executeQuery(query);
    }

    public <T> void insertEntity(T object) {
        Class<?> clazz = object.getClass();
        String tableName = ObjectReflector.getTableName(clazz);
        List<String> tableColumns = ObjectReflector.getColumnNames(clazz);

//        Map<String,Object> data = ObjectReflector.getObjectData(clazz);

//        String query = SqlBuilder.buildInsertQuery(tableName,tableColumns,data);
//        DatabaseAccess databaseAccess = new DatabaseAccess();
//        databaseAccess.insert(query);

//        System.out.println(query);
//        insert into MOVIE (REZISER,HLAVNI_ULOHA,ID) VALUES ('','',0);

    }
}
