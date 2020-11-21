package world.pixelmon.inject;

import com.google.inject.Binder;

public interface ForwardingProtectedBinder extends ProtectedBinder, ForwardingPrivateBinder {

    ProtectedBinder forwardedBinder();

    default Binder publicBinder() {
        return this.forwardedBinder().publicBinder();
    }

    default ProtectedBinder withSource(Object source) {
        return this.forwardedBinder().withSource(source);
    }

    default ProtectedBinder skipSources(Class... classesToSkip) {
        return this.forwardedBinder().skipSources(classesToSkip);
    }

}