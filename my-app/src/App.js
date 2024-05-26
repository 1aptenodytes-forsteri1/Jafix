// src/App.js
import './App.css';
import Footer from './components/Footer/Footer';
import Header from './components/Header/Header';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { CartProvider } from './CartContext';
import { AuthProvider } from './AuthContext';
import MenuPage from './pages/MenuPage/MenuPage';
import Recipe from './pages/Recipe/Recipe';
import Constructor from './pages/Constructor/Constructor';
import Account from './pages/Account/Account';
import Baskets from './pages/Baskets/Basets';
import Men from './pages/Men/Men';
import Bonus from './pages/Account/Bonus/Bonus';
import PersonalInfo from './pages/Account/Personal_info/Personal_info';
import HistoryInfo from './pages/Account/History_info/History_info';
import MyRecipe from './pages/Account/My_recipe/My_recipe';
import SupportService from './pages/Account/Support_service/Support_service';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import ProtectedRoute from './components/ProtectedRoute';

function App() {
  return (
    <div className="App">
      <Router>
        <AuthProvider>
          <CartProvider>
            <Header />
            <Routes>
              <Route path="/login" element={<LoginPage />} />
              <Route path="/register" element={<RegisterPage />} />
              <Route path="/" element={<ProtectedRoute><MenuPage /></ProtectedRoute>} />
              <Route path="/menu" element={<ProtectedRoute><Men /></ProtectedRoute>} />
              <Route path="/bas" element={<ProtectedRoute><Baskets /></ProtectedRoute>} />
              <Route path="/rec" element={<ProtectedRoute><Recipe /></ProtectedRoute>} />
              <Route path="/cos" element={<ProtectedRoute><Constructor /></ProtectedRoute>} />
              <Route path="/acc" element={<ProtectedRoute><Account /></ProtectedRoute>} />
              <Route path="/bonus" element={<ProtectedRoute><Bonus /></ProtectedRoute>} />
              <Route path="/personal_i" element={<ProtectedRoute><PersonalInfo /></ProtectedRoute>} />
              <Route path="/order" element={<ProtectedRoute><HistoryInfo /></ProtectedRoute>} />
              <Route path="/recipe_yoy" element={<ProtectedRoute><MyRecipe /></ProtectedRoute>} />
              <Route path="/sup" element={<ProtectedRoute><SupportService /></ProtectedRoute>} />
            </Routes>
            <Footer />
          </CartProvider>
        </AuthProvider>
      </Router>
    </div>
  );
}

export default App;
