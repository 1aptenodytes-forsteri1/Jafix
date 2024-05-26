import { createContext, useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [userId, setUserId] = useState(null);
  const navigate = useNavigate();

  const login = (id) => {
    setIsAuthenticated(true);
    setUserId(id);
    navigate('/');
  };

  const logout = () => {
    setIsAuthenticated(false);
    setUserId(null);
    navigate('/login');
  };

  const register = () => {
    setIsAuthenticated(true);
    navigate('/');
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout, register, userId }}>
      {children}
    </AuthContext.Provider>
  );
};
