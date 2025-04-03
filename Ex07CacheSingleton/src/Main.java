package main;

import application.ApiService;

public class Main {
    public static void main(String[] args) {
        ApiService apiService = new ApiService();

        System.out.println("🔄 Buscando usuários da Fake API...");
        String usersJson = apiService.fetchUsers();
        
        System.out.println("✅ Dados recebidos:");
        System.out.println(usersJson);
    }
}
