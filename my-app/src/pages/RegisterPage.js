// src/pages/RegisterPage.js
import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import { registerUser } from '../authService';

const RegisterPage = () => {
  const { register } = useAuth();
  const [userDetails, setUserDetails] = useState({ login: '', password: '' ,name: '', surname: '',});
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUserDetails((prev) => ({ ...prev, [name]: value }));
  };

  const handleRegister = async () => {
    try {
      await registerUser(userDetails);
      register();
      navigate('/');
    } catch (err) {
      setError('Registration failed: ' + err.message);
    }
  };

  return (
    <div>
      <h1>Register</h1>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      
      <input
        type="text"
        name="login"
        placeholder="Login"
        value={userDetails.username}
        onChange={handleInputChange}
      />
      <input
        type="password"
        name="password"
        placeholder="Password"
        value={userDetails.password}
        onChange={handleInputChange}
      />
      <input
        type="text"
        name="name"
        placeholder="Name"
        value={userDetails.name}
        onChange={handleInputChange}
      />
      <input
        type="text"
        name="surname"
        placeholder="Surname"
        value={userDetails.surname}
        onChange={handleInputChange}
      />
      <button onClick={handleRegister}>Register</button>
      <p>
        Already have an account? <Link to="/login">Login</Link>
      </p>
    </div>
  );
};

export default RegisterPage;
