// src/pages/LoginPage.js
import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import { loginUser } from '../authService';

const LoginPage = () => {
  const { login } = useAuth();
  const [credentials, setCredentials] = useState({ login: '', password: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCredentials((prev) => ({ ...prev, [name]: value }));
  };


  const handleLogin = async () => {
    try {
      const data = await loginUser(credentials);
      if (data.id !== 0) {
        login(data.id);  // Сохраняем ID пользователя в контексте при авторизации
        navigate('/');
      } else {
        setError('Login failed: Invalid credentials');
      }
    } catch (err) {
      setError('Login failed: ' + err.message);
    }
  };
  return (
    <div>
      <h1>Login</h1>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <input
        type="text"
        name="login"
        placeholder="Login"
        value={credentials.login}
        onChange={handleInputChange}
      />
      <input
        type="password"
        name="password"
        placeholder="Password"
        value={credentials.password}
        onChange={handleInputChange}
      />
      <button onClick={handleLogin}>Login</button>
      <p>
        Don't have an account? <Link to="/register">Register</Link>
      </p>
    </div>
  );
};

export default LoginPage;
