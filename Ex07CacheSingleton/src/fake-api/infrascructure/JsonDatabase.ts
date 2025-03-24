import fs from 'fs';
import path from 'path';
import { User } from '../User';

const DATA_FILE = path.join(__dirname, '../data/db.json');

export class JsonDatabase {
    private readData(): User[] {
        if (!fs.existsSync(DATA_FILE)) {
            fs.writeFileSync(DATA_FILE, JSON.stringify([], null, 2));
        }
        const data = fs.readFileSync(DATA_FILE, 'utf-8');
        return JSON.parse(data) as User[];
    }

    private writeData(users: User[]): void {
        fs.writeFileSync(DATA_FILE, JSON.stringify(users, null, 2));
    }

    getAllUsers(): User[] {
        return this.readData();
    }

    getUserById(id: string): User | undefined {
        return this.readData().find(user => user.id === id);
    }

    addUser(user: User): void {
        const users = this.readData();
        users.push(user);
        this.writeData(users);
    }
}
