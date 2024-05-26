// src/authService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/users'; // Замените на URL вашего сервера

export const registerUser = async (userDetails) => {
  try {
    const response = await axios.post(`${API_URL}`, userDetails);
    return response.data;
  } catch (error) {
    console.error("Registration error:", error.response || error.message || error);
    throw new Error(error.response?.data?.message || 'Registration failed');
  }
};

export const loginUser = async (credentials) => {
  const response = await axios.get(`${API_URL}`, {
    params: credentials  // Передача данных в виде параметров запроса
  });
  return response.data;
};

