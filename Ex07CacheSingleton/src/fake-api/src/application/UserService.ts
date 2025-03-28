interface User {
    id: number;
    name: string;
    email: string;
  }
  
  export class UserService {
    private users: User[] = [];
  
    constructor() {
      // Dados iniciais para teste
      this.users = [
        { id: 1, name: "Alice", email: "alice@example.com" },
        { id: 2, name: "Bob", email: "bob@example.com" }
      ];
    }
  
    // Retorna todos os usuários
    getAllUsers(): User[] {
      return this.users;
    }
  
    // Busca um usuário pelo ID
    getUserById(id: number): User | undefined {
      return this.users.find(user => user.id === id);
    }
  
    // Adiciona um novo usuário
    addUser(user: User): User {
      user.id = this.users.length + 1;
      this.users.push(user);
      return user;
    }
  
    // Atualiza um usuário existente
    updateUser(id: number, updatedUser: Partial<User>): boolean {
      const index = this.users.findIndex(user => user.id === id);
      if (index === -1) {
        return false; // Usuário não encontrado
      }
  
      this.users[index] = { ...this.users[index], ...updatedUser };
      return true;
    }
  
    // Remove um usuário pelo ID
    deleteUser(id: number): boolean {
      const index = this.users.findIndex(user => user.id === id);
      if (index === -1) {
        return false; // Usuário não encontrado
      }
  
      this.users.splice(index, 1);
      return true;
    }
  }
  