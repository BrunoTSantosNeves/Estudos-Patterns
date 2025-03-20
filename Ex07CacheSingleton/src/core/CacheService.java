package core;

import java.util.HashMap;
import java.util.Map;

public class CacheService implements CacheRepository {
    private static CacheService instance;
    private final Map<String, String> cache;
    
    private CacheService() {
        this.cache = new HashMap<>();
    }
    
    public static synchronized CacheService getInstance() {
        if (instance == null) {
            instance = new CacheService();
        }
        return instance;
    }
    
    @Override
    public String get(String key) {
        return cache.get(key);
    }
    
    @Override
    public void set(String key, String value) {
        cache.put(key, value);
    }
}
