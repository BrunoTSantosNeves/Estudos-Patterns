"use strict";
const __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const body_parser_1 = __importDefault(require("body-parser"));
const JsonDatabase_1 = __importDefault(require("../infrascructure/JsonDatabase"));
const app = (0, express_1.default)();
const db = new JsonDatabase_1.default();
const PORT = 3001;
app.use(body_parser_1.default.json());
// Buscar todos os usuários
app.get('/users', (req, res) => {
    const users = db.getAll('users');
    res.json(users);
});
// Buscar um usuário por ID
app.get('/users/:id', (req, res) => {
    const userId = req.params.id;
    const user = db.getById('users', userId);
    if (!user) {
        res.status(404).json({ error: 'Usuário não encontrado' });
        return;
    }
    res.json(user);
});
// Adicionar um novo usuário
app.post('/users', (req, res) => {
    const newUser = req.body;
    db.insert('users', newUser);
    res.status(201).json({ message: 'Usuário criado com sucesso!' });
});
// Atualizar um usuário por ID
app.put('/users/:id', (req, res) => {
    const userId = req.params.id;
    const updatedUser = req.body;
    const success = db.update('users', userId, updatedUser);
    if (!success) {
        res.status(404).json({ error: 'Usuário não encontrado' });
        return;
    }
    res.json({ message: 'Usuário atualizado com sucesso!' });
});
// Remover um usuário por ID
app.delete('/users/:id', (req, res) => {
    const userId = req.params.id;
    const success = db.remove('users', userId);
    if (!success) {
        res.status(404).json({ error: 'Usuário não encontrado' });
        return;
    }
    res.json({ message: 'Usuário removido com sucesso!' });
});
// Iniciar o servidor
app.listen(PORT, () => {
    console.log(` Servidor rodando em http://localhost:${PORT}`);
});
