import express, { Application, Request, Response } from 'express';
import bodyParser from 'body-parser';
import JsonDatabase from '../infrascructure/JsonDatabase';

const app: Application = express();
const db = new JsonDatabase();
const PORT = 3000;

app.use(bodyParser.json());

// Buscar todos os usuários
app.get('/users', (req: Request, res: Response): void => {
    const users = db.getAll('users');
    res.json(users);
});

// Buscar um usuário por ID
app.get('/users/:id', (req: Request, res: Response): void => {
    const userId = req.params.id;
    const user = db.getById('users', userId);

    if (!user) {
        res.status(404).json({ error: 'Usuário não encontrado' });
        return;
    }
    res.json(user);
});

// Adicionar um novo usuário
app.post('/users', (req: Request, res: Response): void => {
    const newUser = req.body;
    db.insert('users', newUser);
    res.status(201).json({ message: 'Usuário criado com sucesso!' });
});

// Atualizar um usuário por ID
app.put('/users/:id', (req: Request, res: Response): void => {
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
app.delete('/users/:id', (req: Request, res: Response): void => {
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
    console.log(`🚀 Servidor rodando em http://localhost:${PORT}`);
});
