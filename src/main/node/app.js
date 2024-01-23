"use strict"

import express from 'express';
import morgan from 'morgan';
import path from 'path';
import cookieParser from 'cookie-parser';
import { fileURLToPath } from 'url';
import * as api from './api.js';
import multer from 'multer';

const upload = multer({ dest: "uploads/" });

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const app = express();

app.use(cookieParser());
app.use(express.json());
app.use(morgan('tiny'));
app.get('/api/bandServer/users', api.getUsers);
app.get('/api/bandServer/user', api.getUser);
app.get('/api/bandServer/getInstruments', api.getInstrument);
app.get('/api/bandServer/getEquipment', api.getEquipment);
app.get('/api/bandServer/getInstrumentInfo', api.getInstrumentInfo);
app.get('/api/bandServer/getMouthpieceInfo', api.getMouthpieceInfo);
app.post('/api/bandServer/newuser', api.createUser);
app.post('/api/bandServer/CheckLogin', api.loginUser);
app.post('/api/bandServer/changePassword', api.changePassword);
app.post('/api/bandServer/processEquipment', api.processEquipment);
app.post('/api/bandServer/upload', upload.single("file"), api.upload);

const webapp_path = path.join(__dirname, '../webapp');
app.use('/', express.static(webapp_path));

app.listen('3000', () => {
  console.log('Server started on port 3000');
});