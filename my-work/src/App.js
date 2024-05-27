// src/App.js
import './App.css';
import Footer from './components/Footer/Footer';
import Header from './components/Header/Header';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainPageOrder from './MainPageOrder';
import { CoffeeProvider } from './CoffeeContext';
import Warehouse from './Warehouse';
import CoffePo from './CoffePo'

function App() {
  return (
    <div className="App">
      <Router>
      <CoffeeProvider>
            <Header />
            <Routes>
              
              <Route path="/" element={<CoffePo />} />
              <Route path="/ord" element={<MainPageOrder />} />
              <Route path="/war" element={<Warehouse />} />
            </Routes>
            <Footer />
            </CoffeeProvider>
      </Router>
    </div>
  );
}

export default App;
