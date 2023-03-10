package ${pPackage}.${daoName};

import ${pPackage}.${pojoName}.${table.name?cap_first};
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/**
 * @author ${author}
 * @version 1.0
 * @description ${table.name?cap_first}的Mapper类
 * @date ${.now?date}
 */

@Mapper
public interface ${table.name?cap_first}Mapper {
    /**
     * @return 以列表形式返回${table.name?cap_first}实体类
     * @description 查询所有数据
     * @author ${author}
     * @data ${.now?date}
     */
    @Select("select * from ${table.name}")
            List<${table.name?cap_first}> list${table.name?cap_first}();

    /**
     * @param ${table.key?lower_case} 主键id
     * @return 返回${table.name?cap_first}实体类
     * @description 根据id获取单条数据（备注：这里的*换成对应想要获取的数据）
     * @author ${author}
     * @data ${.now?date}
     */
    @Select("select * from ${table.name} where ${table.key}=${r"#{"}${table.key?lower_case}}")
            ${table.name?cap_first} get${table.name?cap_first}By${table.key?cap_first}(${keyType} ${table.key?lower_case});

    /**
     * @param first 查询结果的索引值（默认从0开始）
     * @param second 查询结果返回的数量
     * @return 以列表形式返回${table.name?cap_first}实体类
     * @description 分页查询数据（备注：这里的*换成对应想要获取的数据）
     * @author ${author}
     * @data ${.now?date}
     */
    @Select("select * from ${table.name} limit <#noparse>#{first},#{second}</#noparse>;")
            List<${table.name?cap_first}> list${table.name?cap_first}ByPage(int first, int second);

    /**
     * @param ${table.name?lower_case} 插入的实体类
     * @return 新增数据的ID
     * @description 插入数据
     * @author ${author}
     * @data ${.now?date}
     */
    @Insert("insert into ${table.name}(<#list table.columnList as t><#if t.columnName!=table.key>${t.columnName}<#if t_has_next >,</#if></#if></#list>) values(<#list table.columnList as t><#if t.columnName!=table.key><#noparse>#</#noparse>{${t.columnName}}<#if t_has_next >,</#if></#if></#list>)")
    int insert${table.name?cap_first}(${table.name?cap_first} ${table.name?lower_case});

    /**
     * @param ${table.name?lower_case} 要修改的实体类
     * @return 修改数据的条数
     * @description 根据id修改数据（备注：这里要修改的内容要根据实际改一下）
     * @author ${author}
     * @data ${.now?date}
     */
    @Update("update ${table.name} set ${table.columnList[1].columnName} = ${table.columnList[1].columnName} where ${table.key}=${r"#{"}${table.key?lower_case}}")
    int update${table.name?cap_first}By${table.key?cap_first}(${table.name?cap_first} ${table.name?lower_case});

    /**
     * @param ${table.key?lower_case} 主键id
     * @return 删除数据的条数
     * @description 根据id删除数据
     * @author ${author}
     * @since ${.now?date}
     */
    @Delete("delete from ${table.name} where ${table.key}=${r"#{"}${table.key?lower_case}}")
    int delete${table.name?cap_first}By${table.key?cap_first}(${keyType} ${table.key?lower_case});

}