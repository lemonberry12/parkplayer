const express = require('express');
const fs = require('fs');
const path = require('path');
const app = express();
const PORT = 3000;

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// 파일 목록 가져오기
app.get('/admin/files', (req, res) => {
    const files = fs.readdirSync('./files'); // 파일이 저장된 디렉토리 경로
    res.json(files);
});

// 파일 내용 가져오기
app.get('/admin/fileContent', (req, res) => {
    const fileName = req.query.fileName;
    const filePath = path.join(__dirname, 'files', fileName);

    fs.readFile(filePath, 'utf8', (err, data) => {
        if (err) {
            return res.status(404).send('File not found');
        }
        res.send(data);
    });
});

// 파일 수정
app.post('/admin/modify', (req, res) => {
    const { fileName, fileContent } = req.body;
    const filePath = path.join(__dirname, 'files', fileName);

    fs.writeFile(filePath, fileContent, 'utf8', (err) => {
        if (err) {
            return res.status(500).send('Error saving file');
        }
        res.send('File updated successfully');
    });
});

// 정적 파일 제공
app.use(express.static('public'));

// 서버 시작
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
