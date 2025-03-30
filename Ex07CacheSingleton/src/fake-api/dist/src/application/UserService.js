"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.UserService = void 0;
const JsonDatabase_1 = __importDefault(require("../../infrastructure/JsonDatabase"));
class UserService {
    constructor() {
        this.collection = "users";
        this.db = new JsonDatabase_1.default();
    }
    getAllUsers() {
        return this.db.getAll(this.collection);
    }
    getUserById(id) {
        return this.db.getById(this.collection, id.toString());
    }
    addUser(user) {
        const users = this.getAllUsers();
        const newUser = Object.assign({ id: users.length + 1 }, user);
        this.db.insert(this.collection, newUser);
        return newUser;
    }
    updateUser(id, updatedData) {
        return this.db.update(this.collection, id.toString(), updatedData);
    }
    deleteUser(id) {
        return this.db.remove(this.collection, id.toString());
    }
}
exports.UserService = UserService;
