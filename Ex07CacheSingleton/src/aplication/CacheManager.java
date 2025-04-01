package application;

import core.CacheRepository;
import infra.InMemoryCache;
import infra.MySQLCache;
import database.DatabaseAdapter;
import service.ApiService;

public class CacheManager {
    private final CacheRepository inMemoryCache;
    private final CacheRepository mySQLCache;
    private final ApiService apiService;
    private final DatabaseAdapter databaseAdapter;

    public CacheManager(long ttl, DatabaseAdapter databaseAdapter, ApiService apiService) {
        this.inMemoryCache = new InMemoryCache(ttl);
        this.mySQLCache = new MySQLCache(ttl);
        this.databaseAdapter = databaseAdapter;
        this.apiService = apiService;
    }

    public String getData(String key) {
        
        String data = inMemoryCache.get(key);
        if (data != null) {
            System.out.println(" Dados obtidos do InMemoryCache");
            return data;
        }

        
        data = mySQLCache.get(key);
        if (data != null) {
            System.out.println(" Dados obtidos do MySQLCache. Salvando no InMemoryCache...");
            inMemoryCache.set(key, data);
            return data;
        }

        
        System.out.println(" Dados não encontrados no cache. Buscando na Fake API...");
        data = apiService.fetchDataFromApi(key);
        if (data != null) {
            
            inMemoryCache.set(key, data);
            mySQLCache.set(key, data);
            databaseAdapter.saveData(key, data);
            return data;
        }

        return null; // Nenhum dado encontrado
    }
}
