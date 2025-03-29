"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const UserService_1 = require("../application/UserService");
const router = (0, express_1.Router)();
const userService = new UserService_1.UserService();
// Buscar todos os usuários
router.get("/users", (req, res) => {
    res.json(userService.getAllUsers());
});
// Buscar um usuário por ID
router.get("/users/:id", (req, res) => {
    const userId = parseInt(req.params.id, 10);
    const user = userService.getUserById(userId);
    if (!user) {
        res.status(404).json({ error: "Usuário não encontrado" });
        return;
    }
    res.json(user);
});
// Criar um novo usuário
router.post("/users", (req, res) => {
    const { name, email } = req.body;
    if (!name || !email) {
        res.status(400).json({ error: "Nome e email são obrigatórios" });
        return;
    }
    const newUser = userService.addUser({ name, email });
    res.status(201).json(newUser);
});
// Atualizar um usuário existente
router.put("/users/:id", (req, res) => {
    const userId = parseInt(req.params.id, 10);
    const { name, email } = req.body;
    const success = userService.updateUser(userId, { name, email });
    if (!success) {
        res.status(404).json({ error: "Usuário não encontrado" });
        return;
    }
    res.json({ message: "Usuário atualizado com sucesso!" });
});
// Remover um usuário
router.delete("/users/:id", (req, res) => {
    const userId = parseInt(req.params.id, 10);
    const success = userService.deleteUser(userId);
    if (!success) {
        res.status(404).json({ error: "Usuário não encontrado" });
        return;
    }
    res.json({ message: "Usuário removido com sucesso!" });
});
exports.default = router;
