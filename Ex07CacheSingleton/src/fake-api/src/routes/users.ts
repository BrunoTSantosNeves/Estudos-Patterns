import { Router, Request, Response } from "express";
import { UserService } from "../application/UserService";

const router: Router = Router();
const userService = new UserService();

// Buscar todos os usuários
router.get("/users", (req: Request, res: Response): void => {
  res.json(userService.getAllUsers());
});

// Buscar um usuário por ID
router.get("/users/:id", (req: Request, res: Response): void => {
  const userId = parseInt(req.params.id, 10);
  const user = userService.getUserById(userId);
  if (!user) {
    res.status(404).json({ error: "Usuário não encontrado" });
    return; // garantia de retorno void
  }
  res.json(user);
});

// Criar um novo usuário
router.post("/users", (req: Request, res: Response): void => {
  const { name, email } = req.body;
  if (!name || !email) {
    res.status(400).json({ error: "Nome e email são obrigatórios" });
    return;
  }
  const newUser = userService.addUser({ id: 0, name, email });
  res.status(201).json(newUser);
});

// Atualizar um usuário existente
router.put("/users/:id", (req: Request, res: Response): void => {
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
router.delete("/users/:id", (req: Request, res: Response): void => {
  const userId = parseInt(req.params.id, 10);
  const success = userService.deleteUser(userId);
  if (!success) {
    res.status(404).json({ error: "Usuário não encontrado" });
    return;
  }
  res.json({ message: "Usuário removido com sucesso!" });
});

export default router;
