package application;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class CacheService {
    private static CacheService instance;
    private final Map<String, String> cache = new HashMap<>();
    private final List<String> logs = new ArrayList<>();

    private CacheService() {}

    public static synchronized CacheService getInstance() {
        if (instance == null) {
            instance = new CacheService();
        }
        return instance;
    }

    public String get(String key) {
        logs.add("GET: " + key);
        return cache.get(key);
    }

    public void set(String key, String value) {
        logs.add("SET: " + key + " = " + value);
        cache.put(key, value);
    }

    public List<String> getLogs() {
        return logs;
    }
}
