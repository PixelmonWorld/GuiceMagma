package world.pixelmon.inject;

import com.google.inject.*;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.*;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public interface ForwardingBinder extends Binder {
	
    Binder forwardedBinder();

    default void bindInterceptor(Matcher<? super Class<?>> classMatcher, Matcher<? super Method> methodMatcher, MethodInterceptor... interceptors) {
        this.forwardedBinder().bindInterceptor(classMatcher, methodMatcher, interceptors);
    }

    default void bindScope(Class<? extends Annotation> annotationType, Scope scope) {
        this.forwardedBinder().bindScope(annotationType, scope);
    }

    default <T> LinkedBindingBuilder<T> bind(Key<T> key) {
        return this.forwardedBinder().bind(key);
    }

    default <T> AnnotatedBindingBuilder<T> bind(TypeLiteral<T> typeLiteral) {
        return this.forwardedBinder().bind(typeLiteral);
    }

    default <T> AnnotatedBindingBuilder<T> bind(Class<T> type) {
        return this.forwardedBinder().bind(type);
    }

    default AnnotatedConstantBindingBuilder bindConstant() {
        return this.forwardedBinder().bindConstant();
    }

    default <T> void requestInjection(TypeLiteral<T> type, T instance) {
        this.forwardedBinder().requestInjection(type, instance);
    }

    default void requestInjection(Object instance) {
        this.forwardedBinder().requestInjection(instance);
    }

    default void requestStaticInjection(Class<?>... types) {
        this.forwardedBinder().requestStaticInjection(types);
    }

    default void install(Module module) {
        this.forwardedBinder().install(module);
    }

    default Stage currentStage() {
        return this.forwardedBinder().currentStage();
    }

    default void addError(String message, Object... arguments) {
        this.forwardedBinder().addError(message, arguments);
    }

    default void addError(Throwable t) {
        this.forwardedBinder().addError(t);
    }

    default void addError(Message message) {
        this.forwardedBinder().addError(message);
    }

    default <T> Provider<T> getProvider(Key<T> key) {
        return this.forwardedBinder().getProvider(key);
    }

    default <T> Provider<T> getProvider(Dependency<T> dependency) {
        return this.forwardedBinder().getProvider(dependency);
    }

    default <T> Provider<T> getProvider(Class<T> type) {
        return this.forwardedBinder().getProvider(type);
    }

    default <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> typeLiteral) {
        return this.forwardedBinder().getMembersInjector(typeLiteral);
    }

    default <T> MembersInjector<T> getMembersInjector(Class<T> type) {
        return this.forwardedBinder().getMembersInjector(type);
    }

    default void convertToTypes(Matcher<? super TypeLiteral<?>> typeMatcher, TypeConverter converter) {
        this.forwardedBinder().convertToTypes(typeMatcher, converter);
    }

    default void bindListener(Matcher<? super TypeLiteral<?>> typeMatcher, TypeListener listener) {
        this.forwardedBinder().bindListener(typeMatcher, listener);
    }

    default void bindListener(Matcher<? super Binding<?>> bindingMatcher, ProvisionListener... listeners) {
        this.forwardedBinder().bindListener(bindingMatcher, listeners);
    }

    default Binder withSource(Object source) {
        return this.forwardedBinder().withSource(source);
    }

    default Binder skipSources(Class... classesToSkip) {
        return this.forwardedBinder().skipSources(classesToSkip);
    }

    default PrivateBinder newPrivateBinder() {
        return this.forwardedBinder().newPrivateBinder();
    }

    default void requireExplicitBindings() {
        this.forwardedBinder().requireExplicitBindings();
    }

    default void disableCircularProxies() {
        this.forwardedBinder().disableCircularProxies();
    }

    default void requireAtInjectOnConstructors() {
        this.forwardedBinder().requireAtInjectOnConstructors();
    }

    default void requireExactBindingAnnotations() {
        this.forwardedBinder().requireExactBindingAnnotations();
    }

    default void scanModulesForAnnotatedMethods(ModuleAnnotatedMethodScanner scanner) {
        this.forwardedBinder().scanModulesForAnnotatedMethods(scanner);
    }

}