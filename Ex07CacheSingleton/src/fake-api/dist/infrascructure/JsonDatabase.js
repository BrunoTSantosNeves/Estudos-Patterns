"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const fs_1 = __importDefault(require("fs"));
const path_1 = __importDefault(require("path"));
const DB_FILE = path_1.default.join(__dirname, '../../data/db.json');
class JsonDatabase {
    loadDatabase() {
        try {
            const data = fs_1.default.readFileSync(DB_FILE, 'utf-8');
            return JSON.parse(data);
        }
        catch (error) {
            return {};
        }
    }
    saveDatabase(data) {
        fs_1.default.writeFileSync(DB_FILE, JSON.stringify(data, null, 2), 'utf-8');
    }
    getAll(collection) {
        const db = this.loadDatabase();
        return db[collection] || [];
    }
    getById(collection, id) {
        var _a;
        const db = this.loadDatabase();
        return ((_a = db[collection]) === null || _a === void 0 ? void 0 : _a.find((item) => item.id === id)) || null;
    }
    insert(collection, data) {
        const db = this.loadDatabase();
        if (!db[collection])
            db[collection] = [];
        // Gerar um ID simples para o novo registro
        data.id = String(Date.now());
        db[collection].push(data);
        this.saveDatabase(db);
    }
    update(collection, id, newData) {
        const db = this.loadDatabase();
        if (!db[collection])
            return false;
        const index = db[collection].findIndex((item) => item.id === id);
        if (index === -1)
            return false;
        db[collection][index] = Object.assign(Object.assign({}, db[collection][index]), newData);
        this.saveDatabase(db);
        return true;
    }
    remove(collection, id) {
        const db = this.loadDatabase();
        if (!db[collection])
            return false;
        const initialLength = db[collection].length;
        db[collection] = db[collection].filter((item) => item.id !== id);
        this.saveDatabase(db);
        return db[collection].length < initialLength;
    }
}
exports.default = JsonDatabase;
