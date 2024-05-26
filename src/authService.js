// src/authService.js
import axios from 'axios';

const API_URL = 'http://your-server-url.com'; // Замените на URL вашего сервера

export const registerUser = async (userDetails) => {
  const response = await axios.post(`${API_URL}/register`, userDetails);
  return response.data;
};

export const loginUser = async (credentials) => {
  const response = await axios.post(`${API_URL}/login`, credentials);
  return response.data;
};
