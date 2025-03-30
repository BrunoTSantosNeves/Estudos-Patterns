"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const UserService_1 = require("../src/application/UserService");
const userService = new UserService_1.UserService();
const users = [
    { name: "Carlos Almeida", email: "carlos@email.com" },
    { name: "Fernanda Costa", email: "fernanda@email.com" },
    { name: "Lucas Pereira", email: "lucas@email.com" }
];
users.forEach(user => {
    const newUser = userService.addUser(user);
    console.log(`Usuário adicionado: ${JSON.stringify(newUser)}`);
});
console.log("Seeding finalizado!");
