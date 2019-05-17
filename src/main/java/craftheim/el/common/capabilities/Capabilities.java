package craftheim.el.common.capabilities;

import net.minecraftforge.common.capabilities.CapabilityManager;

import java.util.concurrent.Callable;

public final class Capabilities {
    private static class DefaultFactory<T> implements Callable<T> {
        private Class _cls;

        DefaultFactory(Class cls) {
            _cls = cls;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T call() throws Exception {
            return (T)_cls.newInstance();
        }
    }

    public static void init() {
        CapabilityManager.INSTANCE.register(ICapabilityBank.class, new CapabilityBank.DefaultStorage(), new DefaultFactory<>(CapabilityBank.class));
    }
}
