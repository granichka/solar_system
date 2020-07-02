package local.nix.solid.project.solar.system.config;

public interface AnnotationConfigurator {

    <T> void configure(Class<T> type, T t);
}
