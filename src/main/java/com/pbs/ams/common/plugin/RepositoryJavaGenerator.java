package com.pbs.ams.common.plugin;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TiAmo on 17/6/22.
 */
public class RepositoryJavaGenerator extends AbstractJavaGenerator {
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<CompilationUnit> getCompilationUnits() {
        Set s = new HashSet<>();
        s.add(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        s.add(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
        s.add(new FullyQualifiedJavaType("com.pbs.ams.common.base.BaseMapper"));
        String aPackage = context.getProperty("package");
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                new StringBuffer(aPackage).append(".").
                        append(introspectedTable.getFullyQualifiedTable().getDomainObjectName()).
                        append("Mapper").toString());
        Interface inter = new Interface(type);
        inter.setVisibility(JavaVisibility.PUBLIC);
        inter.addImportedTypes(s);
        inter.addSuperInterface(new FullyQualifiedJavaType(
                new StringBuffer("BaseMapper<")
                        .append(introspectedTable.getFullyQualifiedTable().getDomainObjectName())
                        .append(",")
                        .append(introspectedTable.getFullyQualifiedTable().getDomainObjectName()+"Example")
                        .append(">")
                        .toString()
        ));
        List answer = new ArrayList();
        answer.add(inter);
        return answer;
    }
}
