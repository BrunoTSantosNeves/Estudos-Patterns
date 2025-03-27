import fs from 'fs';
import path from 'path';

const DB_FILE = path.join(__dirname, '../../data/db.json');

export default class JsonDatabase {
    private loadDatabase(): any {
        try {
            const data = fs.readFileSync(DB_FILE, 'utf-8');
            return JSON.parse(data);
        } catch (error) {
            return {};
        }
    }

    private saveDatabase(data: any): void {
        fs.writeFileSync(DB_FILE, JSON.stringify(data, null, 2), 'utf-8');
    }

    getAll(collection: string): any[] {
        const db = this.loadDatabase();
        return db[collection] || [];
    }

    getById(collection: string, id: string): any | null {
        const db = this.loadDatabase();
        return db[collection]?.find((item: any) => item.id === id) || null;
    }

    insert(collection: string, data: any): void {
        const db = this.loadDatabase();
        if (!db[collection]) db[collection] = [];
        // Gerar um ID simples para o novo registro
        data.id = String(Date.now());
        db[collection].push(data);
        this.saveDatabase(db);
    }

    update(collection: string, id: string, newData: any): boolean {
        const db = this.loadDatabase();
        if (!db[collection]) return false;
        const index = db[collection].findIndex((item: any) => item.id === id);
        if (index === -1) return false;
        db[collection][index] = { ...db[collection][index], ...newData };
        this.saveDatabase(db);
        return true;
    }

    // Renomei o método de "delete" para "remove" para evitar conflito com a palavra reservada
    remove(collection: string, id: string): boolean {
        const db = this.loadDatabase();
        if (!db[collection]) return false;
        const initialLength = db[collection].length;
        db[collection] = db[collection].filter((item: any) => item.id !== id);
        this.saveDatabase(db);
        return db[collection].length < initialLength;
    }
}
