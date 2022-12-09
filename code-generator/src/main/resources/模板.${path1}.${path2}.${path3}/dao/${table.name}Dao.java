package ${pPackage}.dao;

import java.util.List;
import java.util.Map;
public interface ${table.name?cap_first}Dao {
    /**
     * 查询
     */
    List<Map> get${table.name?cap_first}List();
    /**
     * 添加
     */
    int add${table.name?cap_first}(Map map);
    /**
     * 待修改
     */
    Map toEdit${table.name?cap_first}(${table.columnList[0].columnType} ${table.columnList[0].columnName});
    /**
     * 修改
     */
    int update${table.name?cap_first}(Map map);
    /**
     * 删除
     */
    int delete${table.name?cap_first}(${table.columnList[0].columnType} ${table.columnList[0].columnName});
}
