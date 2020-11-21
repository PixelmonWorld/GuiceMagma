package world.pixelmon.inject;

import com.google.inject.Binder;

public interface ProtectedBinder extends ForwardingPrivateBinder {

    Binder publicBinder();

    default ProtectedBinder withSource(Object source) {
        return new ProtectedBinderImpl(this.publicBinder().withSource(source), this.forwardedBinder().withSource(source));
    }

    default ProtectedBinder skipSources(Class... classesToSkip) {
        return new ProtectedBinderImpl(this.publicBinder().skipSources(classesToSkip), this.forwardedBinder().skipSources(classesToSkip));
    }

    static ProtectedBinder newProtectedBinder(Binder parent) {
        return new ProtectedBinderImpl(parent, parent.newPrivateBinder());
    }

}