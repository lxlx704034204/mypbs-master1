package com.pbs.ams.common.plugin;

import com.pbs.ams.common.util.StringUtil;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created by TiAmo on 17/6/21.
 */
public class AddMethodToMappXmlPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 自动生成MapperXml时插入sql
     * @param document
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        String primaryKeyName = introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName();

        String insertText = "insert into "+ introspectedTable.getFullyQualifiedTableNameAtRuntime() +
                "_snaps SELECT CAST(DATE_FORMAT(CURRENT_TIMESTAMP(3),'%Y%m%d%H%i%S%f')/1000 AS UNSIGNED),tb.* from "+
                introspectedTable.getFullyQualifiedTableNameAtRuntime()+" as tb where " + primaryKeyName + "= #{" + primaryKeyName + "}";
        XmlElement insert = new XmlElement("insert");
        insert.addAttribute(new Attribute("id", "insertInto"+ StringUtil.lineToHump(introspectedTable.getFullyQualifiedTableNameAtRuntime())+"Snaps"));
        insert.addElement(new TextElement(insertText));



        XmlElement parentElement = document.getRootElement();
        parentElement.addElement(insert);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

//    @Override
//    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable){
//        RepositoryJavaGenerator repository = new RepositoryJavaGenerator();
//        repository.setContext(context);
//        repository.setIntrospectedTable(introspectedTable);
//        List units = repository.getCompilationUnits();
//        List generatedFile = new ArrayList();
//        GeneratedJavaFile gif;
//        for (Iterator iterator = units.iterator(); iterator.hasNext(); generatedFile.add(gif)) {
//            CompilationUnit unit = (CompilationUnit) iterator.next();
//            gif = new GeneratedJavaFile(unit,context.getJavaModelGeneratorConfiguration().getTargetProject(),
//                    context.getProperty("javaFileEncoding"),context.getJavaFormatter());
//        }
//        return generatedFile;
//    }

}
