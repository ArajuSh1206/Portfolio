require('dotenv').config();
const OpenAI = require("openai");
const path = require('path');

const openai = new OpenAI({
    apiKey: process.env.OPENAI_API_KEY,
    organization: "org-QXDOHeK2k3hb6nmcKJL0BgfK",
    project: "pinkChat", // Assuming no project-specific key is needed
});

async function generateResponse(prompt) {
    try {
        const response = await openai.complete({
            engine: "text-davinci-003",
            prompt: prompt,
            max_tokens: 100,
            n: 1,
            stop: null,
            temperature: 0.7,
        });

        return response.data.choices[0].text.trim();
    } catch (error) {
        console.error("Error generating response from OpenAI:", error);
        return "Sorry, I couldn't generate a response at the moment.";
    }
}

module.exports = { generateResponse };
