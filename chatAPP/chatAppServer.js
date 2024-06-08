require('dotenv').config();
const express = require('express');
const http = require('http');
const socketio = require('socket.io');
const { generateResponse } = require('./bot');

const app = express();
const server = http.createServer(app);
const io = socketio(server);

const port = process.env.PORT || 3001;

// Serve static files from the current directory
app.use(express.static(__dirname));

// Define a route to serve the HTML file
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'chat.html'));
});

// Handle Socket.IO connections
io.on('connection', (socket) => {
  console.log('A user connected');

  // Handle incoming messages
  socket.on('message', async (data) => {
    const botResponse = await generateResponse(data.message); // Generate bot response
    io.emit('message', { name: 'AI Bot', message: botResponse }); // Emit bot response to all clients
  });

  // Handle user disconnection
  socket.on('disconnect', () => {
    console.log('A user disconnected');
  });
});

// Start the server
server.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});
