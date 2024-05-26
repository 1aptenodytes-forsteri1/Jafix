// src/services/ingredientService.js
import axios from 'axios';

const API_URL = 'http://your-server-url.com'; // Замените на URL вашего сервера

export const fetchIngredients = async () => {
  try {
    const response = await axios.get(`${API_URL}/ingredients`);
    return response.data;
  } catch (error) {
    console.error('Error fetching ingredients:', error);
    throw error;
  }
};
