package cz.radovan.simpleormframework.sql;

import java.util.List;

public class SqlBuilder {


    public static String buildQuery(Long id, String tableName, String idColumnName, List<String> tableColumns) {
        StringBuilder sloupec = new StringBuilder();
        for(String s : tableColumns){
            sloupec.append(s).append(",");
        }
        String sloupecQuery = sloupec.toString().substring(0,sloupec.lastIndexOf(","));

        return String.format("SELECT %s FROM %s WHERE %s=%d",sloupecQuery, tableName, idColumnName,id);
    }
}
