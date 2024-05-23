// App.js
import './App.css';
import Footer from './components/Footer/Footer';
import Header from './components/Header/Header';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { CartProvider } from './CartContext';
import MenuPage from './pages/MenuPage/MenuPage';
import Recipe from './pages/Recipe/Recipe';
import Constructor from './pages/Constructor/Constructor';
import Account from './pages/Account/Account';
import Baskets from './pages/Baskets/Basets';
import Men from './pages/Men/Men';

function App() {
  return (
    <div className="App">
      <Router>
        <CartProvider >
          <Header />
          <Routes>
            <Route path="/" element={<MenuPage />} />
            <Route path="/menu" element={<Men />} />
            <Route path="/bas" element={<Baskets />} />
            <Route path="/rec" element={<Recipe />} />
            <Route path="/cos" element={<Constructor />} />
            <Route path="/acc" element={<Account />} />
          </Routes>
          <Footer />
        </CartProvider>
      </Router>
    </div>
  );
}

export default App;
