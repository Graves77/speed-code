package ${pPackage}.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * @author ${author}
 * @version 1.0
 * @description ${table.name?cap_first}的实体类
 * @date ${.now?date}
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${table.name?cap_first} {  //?cap_first freemarker内置函数 首字母大写
<#list table.columnList as abc>
private ${abc.columnType} ${abc.columnName};
</#list>
<#list table.columnList as abc>
public ${abc.columnType} get${abc.columnName?cap_first}() {
        return this.${abc.columnName};
        }
public void set${abc.columnName?cap_first}(String ${abc.columnName}) {
        this.${abc.columnName} = ${abc.columnName};
        }
</#list>
        }
