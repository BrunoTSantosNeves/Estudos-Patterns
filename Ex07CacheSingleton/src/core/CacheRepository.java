package core;

public interface CacheRepository {
    String get(String key);
    void set(String key, String value);
}