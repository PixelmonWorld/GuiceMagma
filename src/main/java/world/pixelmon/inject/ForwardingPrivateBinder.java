package world.pixelmon.inject;

import com.google.inject.Key;
import com.google.inject.PrivateBinder;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedElementBuilder;

public interface ForwardingPrivateBinder extends ForwardingBinder, PrivateBinder {

    PrivateBinder forwardedBinder();

    default void expose(Key<?> key) {
        this.forwardedBinder().expose(key);
    }

    default AnnotatedElementBuilder expose(Class<?> type) {
        return this.forwardedBinder().expose(type);
    }

    default AnnotatedElementBuilder expose(TypeLiteral<?> type) {
        return this.forwardedBinder().expose(type);
    }

    default PrivateBinder withSource(Object source) {
        return this.forwardedBinder().withSource(source);
    }

    default PrivateBinder skipSources(Class... classesToSkip) {
        return this.forwardedBinder().skipSources(classesToSkip);
    }

}