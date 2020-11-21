package world.pixelmon.inject;

import com.google.inject.Binder;
import com.google.inject.Module;

public abstract class ProtectedModule implements Module, ForwardingProtectedBinder {

    private final Object moduleKey;
    private ProtectedBinder binder;

    protected void configure() {
    }

    protected ProtectedModule(Object moduleKey) {
        this.moduleKey = moduleKey;
    }

    protected ProtectedModule() {
        this((Object)null);
    }

    public int hashCode() {
        return this.moduleKey != null ? this.moduleKey.hashCode() : super.hashCode();
    }

    public boolean equals(Object obj) {
        if (this.moduleKey == null) {
            return super.equals(obj);
        } else {
            return obj != null && this.getClass().equals(obj.getClass()) && this.moduleKey.equals(((ProtectedModule)obj).moduleKey);
        }
    }

    public final ProtectedBinder forwardedBinder() {
        return this.binder();
    }

    protected final ProtectedBinder binder() {
        if (this.binder == null) {
            throw new IllegalStateException("Binder is only usable during configuration");
        } else {
            return this.binder;
        }
    }

    public final void configure(Binder binder) {
        ProtectedBinder old = this.binder;
        this.binder = ProtectedBinderImpl.current(binder);

        try {
            if (this.binder != null) {
                this.configure();
            } else {
                binder.skipSources(new Class[]{ProtectedModule.class}).addError(ProtectedModule.class.getSimpleName() + " must be installed with a " + ProtectedBinder.class.getSimpleName(), new Object[0]);
            }
        } finally {
            this.binder = old;
        }

    }
}