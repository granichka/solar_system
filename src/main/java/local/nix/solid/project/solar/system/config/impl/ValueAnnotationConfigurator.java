package local.nix.solid.project.solar.system.config.impl;

import local.nix.solid.project.solar.system.annotation.Value;
import local.nix.solid.project.solar.system.config.AnnotationConfigurator;
import local.nix.solid.project.solar.system.util.ResourceUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ValueAnnotationConfigurator implements AnnotationConfigurator {

    private Map<String, String> map;

    public ValueAnnotationConfigurator() {
        this.map = ResourceUtil.getResource("application.properties");
    }


    public <T> void configure(Class<T> type, T t) {
        String nameOfClass = t.getClass().getSimpleName();
        Map<String, String> properties = new HashMap<>();
        for(String key: map.keySet()) {
            if(nameOfClass.equals(key)) {
                String planetFields = map.get(key);
                String[] fields = planetFields.split(",");
                for(String string: fields) {
                    String[] property = string.split("#");
                    properties.put(property[0], property[1]);
                }
            }
        }

        for (Field field : type.getDeclaredFields()) {
            Value annotation = field.getAnnotation(Value.class);
            if (annotation != null) {
                field.setAccessible(true);
                if("name".equals(field.getName())) {
                    String value = annotation.value().isEmpty() ? properties.get(field.getName()) : annotation.value();
                    try {
                        field.set(t, value);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Возникли проблемы с инициализацией филда: " + e.getMessage());
                    }
                } else {
                    double value = annotation.value().isEmpty() ? Double.valueOf(properties.get(field.getName())) : Double.valueOf(annotation.value());
                    try {
                        field.set(t, value);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Возникли проблемы с инициализацией филда: " + e.getMessage());
                    }
                }

            }
        }


    }
}
