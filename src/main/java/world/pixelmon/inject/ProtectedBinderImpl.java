package world.pixelmon.inject;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.PrivateBinder;

public class ProtectedBinderImpl implements ProtectedBinder {

    private static Class[] SKIP = new Class[]{ForwardingBinder.class, ForwardingPrivateBinder.class, ForwardingProtectedBinder.class, ProtectedBinder.class, ProtectedBinderImpl.class, ProtectedModule.class};
    private final Binder global;
    private final PrivateBinder local;
    private static final ThreadLocal<ProtectedBinderImpl> CURRENT = new ThreadLocal();

    ProtectedBinderImpl(Binder global, PrivateBinder local) {
        this.global = global.skipSources(SKIP);
        this.local = (local instanceof ProtectedBinder ? ((ProtectedBinder)local).forwardedBinder() : local).skipSources(SKIP);
    }

    public PrivateBinder forwardedBinder() {
        return this.local;
    }

    public Binder publicBinder() {
        return this.global;
    }

    public void install(Module module) {
        ProtectedBinderImpl prev = (ProtectedBinderImpl)CURRENT.get();
        CURRENT.set(this);

        try {
            this.local.install(module);
        } finally {
            CURRENT.set(prev);
        }

    }

    public static ProtectedBinder current(Binder binder) {
        if (binder instanceof ProtectedBinder) {
            return (ProtectedBinder)binder;
        } else {
            ProtectedBinderImpl current = (ProtectedBinderImpl)CURRENT.get();
            return current != null && current.local == binder ? current : null;
        }
    }
}