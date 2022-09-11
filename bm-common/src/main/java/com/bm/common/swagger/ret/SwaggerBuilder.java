//package com.bm.common.swagger.ret;
//
//
//import com.bm.common.swagger.SwaggerEnum;
//import com.bm.common.swagger.SwaggerObject;
//import com.fasterxml.classmate.ResolvedType;
//import com.fasterxml.classmate.TypeResolver;
//import com.fasterxml.classmate.members.RawField;
//import com.google.common.base.Optional;
//import io.swagger.annotations.ApiModelProperty;
//import jdk.internal.org.objectweb.asm.*;
//import org.apache.commons.lang.RandomStringUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.ResponseMessage;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.OperationBuilderPlugin;
//import springfox.documentation.spi.service.contexts.OperationContext;
//import springfox.documentation.swagger.common.SwaggerPluginSupport;
//
//import java.lang.annotation.Annotation;
//import java.util.*;
//
//import static jdk.internal.org.objectweb.asm.Opcodes.*;
//
///**
// * @Order一定要大一点 plugin加载顺序，默认是最后加载
// * @author hex
// * @date 2022/3/20
// */
//@Configuration
//@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 3)
//public class SwaggerBuilder extends ClassLoader implements OperationBuilderPlugin {
//
//    @Autowired
//    private TypeResolver typeResolver;
//
//    @Override
//    public void apply(OperationContext operationContext) {
//        //String typeName = operationContext.getReturnType().getTypeName();
//        String retrunClassName = "ResData";
//        List<RawField> rawFields = operationContext.getReturnType().getMemberFields();
//        ApiReturnProperty[] apiReturnProperty = new ApiReturnProperty[rawFields.size()];
//
//        Class<?> retrunClass = null;
//        Optional<ApiReturnObjects> optionals = operationContext.findAnnotation(ApiReturnObjects.class);
//
//        if (optionals.isPresent()) {
//            Map<String, Class<?>> map = new HashMap<>();
//            String name = optionals.get().name();
//            String objsDescription = optionals.get().description();
//            ApiReturnObject[] apiReturnObjects = optionals.get().value();
//            SwaggerEnum select = optionals.get().select();
//            List<SwaggerObject> swaggerObectList = new ArrayList<>();
//            for (ApiReturnObject apiReturnObject : apiReturnObjects) {
//                String key = apiReturnObject.key();
//                String description = apiReturnObject.description();
//                ApiReturnProperty[] properties = apiReturnObject.value();
//                Class<?> oClass = addClass(key, properties);
//                SwaggerObject swaggerObject = new SwaggerObject(key, description, oClass);
//                swaggerObectList.add(swaggerObject);
//            }
//            if (select == SwaggerEnum.Key) {
//                for (int i = 0; i < rawFields.size(); i++) {
//                    RawField rawField = rawFields.get(i);
//                    String fieldName = rawField.getName();
//                    ApiReturnProperty property = null;
//                    Annotation[] annotations = rawField.getAnnotations();
//                    for (SwaggerObject obj : swaggerObectList) {
//                        if (fieldName.equals(obj.getKey())) {
//                            property = new A(obj.getKey(), null, obj.getDataType(), obj.getDescription(), null);
//                            for (Annotation annotation : annotations) {
//                                ApiModelProperty apiModelProperty = annotation.annotationType().getAnnotation(ApiModelProperty.class);
//                                if (apiModelProperty != null) {
//                                    if (StringUtils.isEmpty(obj.getDescription())) {
//                                        ((A) property).setDescription(apiModelProperty.value());
//                                    }
//                                    ((A) property).setExample(apiModelProperty.example());
//                                }
//                            }
//                            break;
//                        }
//                    }
//                    if (property == null) {
//                        property = new A(fieldName, null, rawField.getRawMember().getType(), null, null);
//                        for (Annotation annotation : annotations) {
//                            ApiModelProperty apiModelProperty = annotation.annotationType().getAnnotation(ApiModelProperty.class);
//                            if (apiModelProperty != null) {
//                                ((A) property).setDescription(apiModelProperty.value());
//                                ((A) property).setExample(apiModelProperty.example());
//                            }
//                        }
//
//                    }
//                    apiReturnProperty[i] = property;
//                }
//                map.put(retrunClassName, addClass(retrunClassName, apiReturnProperty));
//            } else {
//                ApiReturnProperty[] apiReturnObjectProperty = new ApiReturnProperty[apiReturnObjects.length];
//                for (int i = 0; i < apiReturnObjects.length; i++) {
//                    String key = apiReturnObjects[i].key();
//                    String description = apiReturnObjects[i].description();
//                    apiReturnObjectProperty[i] = new A(key, null, swaggerObectList.get(i).getDataType(), description, null);
//                }
//                if (select == SwaggerEnum.None) {
//                    map.put(retrunClassName, addClass(retrunClassName, apiReturnObjectProperty));
//                }
//
//                if (select == SwaggerEnum.Name) {
//                    Class<?> aClass = addClass(name, apiReturnObjectProperty);
//                    for (int i = 0; i < rawFields.size(); i++) {
//                        RawField rawField = rawFields.get(i);
//                        String fieldName = rawField.getName();
//                        ApiReturnProperty property = null;
//                        Annotation[] annotations = rawField.getAnnotations();
//                        if (fieldName.equals(name)) {
//                            property = new A(name, null, aClass, objsDescription, null);
//                            for (Annotation annotation : annotations) {
//                                ApiModelProperty apiModelProperty = annotation.annotationType().getAnnotation(ApiModelProperty.class);
//                                if (apiModelProperty != null) {
//                                    if (StringUtils.isEmpty(objsDescription)) {
//                                        ((A) property).setDescription(apiModelProperty.value());
//                                    }
//                                    ((A) property).setExample(apiModelProperty.example());
//                                }
//                            }
//                        } else {
//                            property = new A(fieldName, null, rawField.getRawMember().getType(), null, null);
//                            for (Annotation annotation : annotations) {
//                                ApiModelProperty apiModelProperty = annotation.annotationType().getAnnotation(ApiModelProperty.class);
//                                if (apiModelProperty != null) {
//                                    ((A) property).setDescription(apiModelProperty.value());
//                                    ((A) property).setExample(apiModelProperty.example());
//                                }
//                            }
//                        }
//
//                        apiReturnProperty[i] = property;
//                    }
//                    map.put(retrunClassName, addClass(retrunClassName, apiReturnProperty));
//                }
//
//            }
//            retrunClass = map.get(retrunClassName);
//            ResolvedType rt = typeResolver.resolve(retrunClass);
//            // 像documentContext的Models中添加我们新生成的Class
//            operationContext.getDocumentationContext().getAdditionalModels().add(rt);
//            //
//            Set<ResponseMessage> set = new HashSet<ResponseMessage>();
//            ModelRef mr = new ModelRef(retrunClass.getName());
//            set.add(new ResponseMessage(200, null, mr, null, null));
//            operationContext.operationBuilder().responseMessages(set);
//        } else {
//            //根据参数上的ApiReturnObject注解中的参数动态生成Class
//            Optional<ApiReturnObject> optional = operationContext.findAnnotation(ApiReturnObject.class);
//            if (optional.isPresent()) {
//                String key = optional.get().key();
//                ApiReturnProperty[] properties = optional.get().value();
//                Map<String, Class<?>> map = new HashMap<>();
//                map.put(key,addClass("Map", properties));
//                if (StringUtils.isNotEmpty(key)) {
//                    for (int i = 0; i < rawFields.size(); i++) {
//                        RawField rawField = rawFields.get(i);
//                        String fieldName = rawField.getName();
//                        ApiReturnProperty property;
//                        Annotation[] annotations = rawField.getAnnotations();
//                        if (fieldName.equals(key)) {
//                            property = new A(key, null, map.get(key), null, null);
//                            for (Annotation annotation : annotations) {
//                                ApiModelProperty apiModelProperty = annotation.annotationType().getAnnotation(ApiModelProperty.class);
//                                if (apiModelProperty != null) {
//                                    ((A) property).setDescription(apiModelProperty.value());
//                                    ((A) property).setExample(apiModelProperty.example());
//                                }
//                            }
//                        } else {
//                            property = new A(fieldName, null, rawField.getRawMember().getType(), null, null);
//                            for (Annotation annotation : annotations) {
//                                ApiModelProperty apiModelProperty = annotation.annotationType().getAnnotation(ApiModelProperty.class);
//                                if (apiModelProperty != null) {
//                                    ((A) property).setDescription(apiModelProperty.value());
//                                    ((A) property).setExample(apiModelProperty.example());
//                                }
//                            }
//                        }
//                        apiReturnProperty[i] = property;
//                    }
//                    map.put(retrunClassName,addClass(retrunClassName, apiReturnProperty));
//                }
//                retrunClass = map.get(retrunClassName);
//                ResolvedType rt = typeResolver.resolve(retrunClass);
//                // 像documentContext的Models中添加我们新生成的Class
//                operationContext.getDocumentationContext().getAdditionalModels().add(rt);
//                //
//                Set<ResponseMessage> set = new HashSet<ResponseMessage>();
//                ModelRef mr = new ModelRef(retrunClass.getName());
//                set.add(new ResponseMessage(200, null, mr, null, null));
//                operationContext.operationBuilder().responseMessages(set);
//            }
//
//        }
//
//
//    }
//
//    private Class<?> addClass(String className, ApiReturnProperty[] properties) {
//        className = className + "$" + RandomStringUtils.random(10, true, false);
//        byte[] cs = createRefModel(properties, className);
//        this.defineClass(className, cs, 0, cs.length);
//        Class<?> aClass = null;
//        try {
//            aClass = this.loadClass(className);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return aClass;
//
//    }
//
//
//    public class A implements ApiReturnProperty {
//        private String key;
//        private String example;
//        private Class<?> dataType;
//        private String description;
//        private Class<? extends Annotation> annotationType;
//
//        public A(String key, String example, Class<?> dataType, String description, Class<? extends Annotation> annotationType) {
//            this.key = key;
//            this.example = example;
//            this.dataType = dataType;
//            this.description = description;
//            this.annotationType = annotationType;
//        }
//
//        @Override
//        public String key() {
//            return this.key;
//        }
//
//        @Override
//        public String example() {
//            return this.example;
//        }
//
//        @Override
//        public Class<?> dataType() {
//            return this.dataType;
//        }
//
//        @Override
//        public String description() {
//            return this.description;
//        }
//
//        @Override
//        public Class<? extends Annotation> annotationType() {
//            if (annotationType == null) {
//                return this.getClass();
//            }
//            return annotationType;
//        }
//
//        public String getKey() {
//            return key;
//        }
//
//        public void setKey(String key) {
//            this.key = key;
//        }
//
//        public String getExample() {
//            return example;
//        }
//
//        public void setExample(String example) {
//            this.example = example;
//        }
//
//        public Class<?> getDataType() {
//            return dataType;
//        }
//
//        public void setDataType(Class<?> dataType) {
//            this.dataType = dataType;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public Class<? extends Annotation> getAnnotationType() {
//            return annotationType;
//        }
//
//        public void setAnnotationType(Class<? extends Annotation> annotationType) {
//            this.annotationType = annotationType;
//        }
//    }
//
//    @Override
//    public boolean supports(DocumentationType documentationType) {
//        return true;
//    }
//
//    public static byte[] createRefModel(ApiReturnProperty[] propertys, String className) {
//        try {
//            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//            //创建类
//            createClazz(cw, className);
//
//            //创建构造方法
//            createConstructor(cw);
//
//            //循环处理 getter 和 setter 方法 创建字段和注解
//            doParseFieldAndMethod(cw, propertys, className);
//
//            cw.visitEnd();
//
//            byte[] code = cw.toByteArray();
//
//            return code;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private static void createClazz(ClassWriter cw, String className) {
//        cw.visit(V1_8, ACC_PUBLIC, className, null, "java/lang/Object", null);
//    }
//
//
//    private static void createConstructor(ClassWriter cw) {
//        MethodVisitor methodVisitor = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
//        methodVisitor.visitCode();
//        //0 表示当前对象
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
//        methodVisitor.visitInsn(Opcodes.RETURN);
//        methodVisitor.visitMaxs(0, 0);
//        methodVisitor.visitEnd();
//    }
//
//
//    private static void doParseFieldAndMethod(ClassWriter cw, ApiReturnProperty[] propertys, String className) {
//        for (ApiReturnProperty property : propertys) {
//
//            String typeof = "";
//            if (property.dataType() != null) {
//                typeof = Type.getType(property.dataType()).getDescriptor();
//            }
//            int[] loadAndReturnOf = loadAndReturnOf(typeof);
//
//            // 创建 字段 和 注释
//            createFieldAndAnno(cw, property, typeof);
//
//            // 创建字段getter 方法
//            createFieldGetterMethod(cw, property, className, typeof, getOrSetOffer(typeof, true), loadAndReturnOf);
//
//            // 创建字段setter 方法
//            createFieldSetterMethod(cw, property, className, typeof, getOrSetOffer(typeof, false), loadAndReturnOf);
//
//        }
//    }
//
//    private static void createFieldAndAnno(ClassWriter cw, ApiReturnProperty property, String typeof) {
//        FieldVisitor fv = cw.visitField(ACC_PUBLIC, property.key(), typeof, null, new String(property.example() == null ? "" : property.example().toString()));
//        fv.visitEnd();
//
//        AnnotationVisitor av = fv.visitAnnotation("Lio/swagger/annotations/ApiModelProperty;", true);
//        //注释参数
//        av.visit("value", property.description() == null ? "" : property.description());
//        av.visit("example", property.example() == null ? "" : property.example());
//        av.visitEnd();
//    }
//
//
//    private static void createFieldGetterMethod(ClassWriter cw, ApiReturnProperty property, String className, String typeof, String typeoffer, int[] loadAndReturnOf) {
//        String getterName = getterAndSetterName(property.key(), true);
//        MethodVisitor m_getName = cw.visitMethod(ACC_PUBLIC, getterName, typeoffer, null, null);
//        m_getName.visitVarInsn(ALOAD, 0);
//        m_getName.visitFieldInsn(GETFIELD, className, property.key(), typeof);
//        m_getName.visitInsn(loadAndReturnOf[1]);
//        m_getName.visitMaxs(2, 1);
//        m_getName.visitEnd();
//    }
//
//
//    private static void createFieldSetterMethod(ClassWriter cw, ApiReturnProperty property, String className, String typeof, String typeoffer, int[] loadAndReturnOf) {
//        String setterName = getterAndSetterName(property.key(), false);
//        MethodVisitor m_setName = cw.visitMethod(ACC_PUBLIC, setterName, typeoffer, null, null);
//        m_setName.visitVarInsn(ALOAD, 0);
//        m_setName.visitVarInsn(loadAndReturnOf[0], 1);
//        m_setName.visitFieldInsn(PUTFIELD, className, property.key(), typeof);
//        m_setName.visitInsn(RETURN);
//        m_setName.visitMaxs(3, 3);
//        m_setName.visitEnd();
//    }
//
//    private static String getterAndSetterName(String name, Boolean isGetter) {
//        if (name.length() > 1) {
//            name = StringUtils.capitalize(name);
//            if (isGetter) {
//                return "get" + name;
//            } else {
//                return "set" + name;
//            }
//        }
//        return name;
//    }
//
//    private static String getOrSetOffer(String typeof, boolean isGet) {
//        if (isGet) {
//            return "()" + typeof;
//        }
//        return "(" + typeof + ")V";
//    }
//
//    private static int[] loadAndReturnOf(String typeof) {
//        if ("I".equals(typeof) || "Z".equals(typeof)) {
//            return new int[]{ILOAD, IRETURN};
//        } else if ("J".equals(typeof)) {
//            return new int[]{LLOAD, LRETURN};
//        } else if ("D".equals(typeof)) {
//            return new int[]{DLOAD, DRETURN};
//        } else if ("F".equals(typeof)) {
//            return new int[]{FLOAD, FRETURN};
//        } else {
//            return new int[]{ALOAD, ARETURN};
//        }
//    }
//}
