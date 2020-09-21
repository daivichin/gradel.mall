package com.gradel.common.shared.plugin;

import com.gradel.parent.common.util.util.StringUtil;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: sdeven.chen.dongwei@gmail.com
 * Date: 2016/3/30
 * Description:
 * @see org.mybatis.generator.internal.DefaultCommentGenerator
 */
public class MyCommentGenerator implements CommentGenerator {

    public MyCommentGenerator() {
    }


    @Override
    public void addConfigurationProperties(Properties properties) {

    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");
        field.addJavaDocLine("* " + StringUtil.defaultIfBlank(introspectedColumn.getRemarks(), ""));
        field.addJavaDocLine("*/");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        innerClass.addJavaDocLine("/**"); //$NON-NLS-1$
        innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

        innerClass.addJavaDocLine("/**"); //$NON-NLS-1$
        innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {

    }

    @Override
    public void addComment(XmlElement xmlElement) {

    }

    @Override
    public void addRootComment(XmlElement rootElement) {

    }
}
