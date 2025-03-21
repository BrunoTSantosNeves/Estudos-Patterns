package infraestructure;

import core.CacheRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache implements CacheRepository {
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private final long ttl;

    public InMemoryCache(long ttl) {
        this.ttl = ttl;
        startCleanupThread();
    }

    @Override
    public String get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry != null && !isExpired(entry)) {
            return entry.getValue();
        }
        cache.remove(key);
        return null;
    }

    @Override
    public void set(String key, String value) {
        cache.put(key, new CacheEntry(value, System.currentTimeMillis()));
    }

    private boolean isExpired(CacheEntry entry) {
        return System.currentTimeMillis() - entry.getTimestamp() > ttl;
    }

    // Thread para limpar entradas expiradas periodicamente
    private void startCleanupThread() {
        Thread cleanupThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(ttl); // Roda na frequência do TTL
                    cache.entrySet().removeIf(entry -> isExpired(entry.getValue()));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        cleanupThread.setDaemon(true);
        cleanupThread.start();
    }

    private static class CacheEntry {
        private final String value;
        private final long timestamp;

        public CacheEntry(String value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }

        public String getValue() {
            return value;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
