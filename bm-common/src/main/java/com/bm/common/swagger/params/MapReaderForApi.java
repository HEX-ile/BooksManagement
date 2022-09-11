package com.bm.common.swagger.params;

/**
 * @author hex
 * @date 2022/3/20
 */


import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.AnnotationsAttribute;
import org.apache.ibatis.javassist.bytecode.ConstPool;
import org.apache.ibatis.javassist.bytecode.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @version 1.0
 * @Title: MapReaderForApi.java
 * @Description: 将map入参匹配到swagger文档的工具类
 * @Order plugin加载顺序，默认是最后加载
 */
@Component
@Order
public class MapReaderForApi implements ParameterBuilderPlugin {
    @Autowired
    private TypeResolver typeResolver;

    // 动态生成的虚拟DTO Class的包路径
    private static String basePackage = null;

    @Override
    public void apply(ParameterContext parameterContext) {
        ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
        //判断是否需要修改对象ModelRef,这里我判断的是Map类型和String类型需要重新修改ModelRef对象
        if (methodParameter.getParameterType().canCreateSubtype(Map.class) || methodParameter.getParameterType().canCreateSubtype(String.class)) {
            Optional<ApiParamObject> optional = methodParameter.findAnnotation(ApiParamObject.class);
            //根据参数上的ApiParamObject注解中的参数动态生成Class
            if (!optional.isPresent()) {
                optional = parameterContext.getOperationContext().findAnnotation(ApiParamObject.class);
            }
            if (optional.isPresent()) {
                //model 名称
                String name = optional.get().name() + "$" + RandomStringUtils.random(10, true, false);
                ApiParamProperty[] properties = optional.get().value();
                //像documentContext的Models中添加我们新生成的Class
                parameterContext.getDocumentationContext().getAdditionalModels().add(typeResolver.resolve(createRefModel(properties, name)));
                //修改Map参数的ModelRef为我们动态生成的class
                parameterContext.parameterBuilder()
                        .parameterType("body")
                        .modelRef(new ModelRef(name))
                        .name(name);
            }
        }

    }


    /**
     * 根据propertys中的值动态生成含有Swagger注解的javaBeen
     */
    private Class<?> createRefModel(ApiParamProperty[] propertys, String name) {
        ClassPool pool = ClassPool.getDefault();
        if (basePackage == null) {
            basePackage = this.getClass().getPackage().getName() + ".";
        }
        CtClass ctClass = pool.makeClass(basePackage + name);

        try {
            for (ApiParamProperty property : propertys) {
                ctClass.addField(createField(property, ctClass));
            }
            return ctClass.toClass();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据property的值生成含有swagger apiModelProperty注解的属性
     */
    private CtField createField(ApiParamProperty property, CtClass ctClass) throws NotFoundException, CannotCompileException {
        CtField ctField = new CtField(getFieldType(property.type()), property.key(), ctClass);
        ctField.setModifiers(Modifier.PUBLIC);

        ConstPool constPool = ctClass.getClassFile().getConstPool();

        AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation ann = new Annotation("io.swagger.annotations.ApiModelProperty", constPool);
        ann.addMemberValue("value", new StringMemberValue(property.description(), constPool));
        if (ctField.getType().subclassOf(ClassPool.getDefault().get(String.class.getName()))) {
            ann.addMemberValue("example", new StringMemberValue(property.example(), constPool));
        }
        if (ctField.getType().subclassOf(ClassPool.getDefault().get(Integer.class.getName()))) {
            ann.addMemberValue("example", new IntegerMemberValue(constPool, Integer.parseInt(property.example())));
        }
        if (ctField.getType().subclassOf(ClassPool.getDefault().get(Double.class.getName()))) {
            ann.addMemberValue("example", new DoubleMemberValue(Double.parseDouble(property.example()), constPool));
        }
        if (ctField.getType().subclassOf(ClassPool.getDefault().get(Boolean.class.getName()))) {
            ann.addMemberValue("required", new BooleanMemberValue(property.required(), constPool));
        }
        attr.addAnnotation(ann);
        ctField.getFieldInfo().addAttribute(attr);

        return ctField;
    }

    private CtClass getFieldType(String type) throws NotFoundException {
        CtClass fileType = null;
        switch (type) {
            case "string":
                fileType = ClassPool.getDefault().get(String.class.getName());
                break;
            case "int":
                fileType = ClassPool.getDefault().get(Integer.class.getName());
                break;
            case "double":
                fileType = ClassPool.getDefault().get(Double.class.getName());
                break;
            case "boolean":
                fileType = ClassPool.getDefault().get(Boolean.class.getName());
                break;
            default:
                fileType = ClassPool.getDefault().get(String.class.getName());
                break;

        }
        return fileType;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }


}

