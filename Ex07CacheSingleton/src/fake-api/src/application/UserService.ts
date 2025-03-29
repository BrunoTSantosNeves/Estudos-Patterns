import JsonDatabase from "../../infrastructure/JsonDatabase";

interface User {
  id: number;
  name: string;
  email: string;
}

export class UserService {
  private db: JsonDatabase;
  private collection: string = "users";

  constructor() {
    this.db = new JsonDatabase();
  }

  getAllUsers(): User[] {
    return this.db.getAll(this.collection);
  }

  getUserById(id: number): User | null {
    return this.db.getById(this.collection, id.toString());
  }

  addUser(user: Omit<User, "id">): User {
    const users = this.getAllUsers();
    const newUser: User = { id: users.length + 1, ...user };
    this.db.insert(this.collection, newUser);
    return newUser;
  }

  updateUser(id: number, updatedData: Partial<Omit<User, "id">>): boolean {
    return this.db.update(this.collection, id.toString(), updatedData);
  }

  deleteUser(id: number): boolean {
    return this.db.remove(this.collection, id.toString());
  }
}
