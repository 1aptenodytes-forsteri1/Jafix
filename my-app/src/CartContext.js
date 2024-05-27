import React, { createContext, useState } from 'react';

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
    const [cart, setCart] = useState([]);

    const addToCart = (item) => {
        // Проверяем наличие товара в корзине
        const existingItemIndex = cart.findIndex((cartItem) => cartItem.id === item.id && cartItem.whatIs === item.whatIs);
        if (existingItemIndex !== -1) {
            // Если товар уже есть в корзине, увеличиваем его количество на 1
            const updatedCart = [...cart];
            updatedCart[existingItemIndex].quantity += 1;
            updatedCart[existingItemIndex].totalPrice = (parseFloat(updatedCart[existingItemIndex].price) * updatedCart[existingItemIndex].quantity).toFixed(2);
            setCart(updatedCart);
        } else {
            // Если товара нет в корзине, добавляем его с начальным количеством 1
            setCart([...cart, { ...item, quantity: 1, totalPrice: item.price }]);
        }
    };

    const removeFromCart = (index) => {
        const newCart = [...cart];
        newCart.splice(index, 1);
        setCart(newCart);
    };

    const calculateTotalPrice = () => {
        return cart.reduce((total, item) => total + parseFloat(item.totalPrice), 0).toFixed(2);
    };

    return (
        <CartContext.Provider value={{ cart,setCart, addToCart, removeFromCart, calculateTotalPrice }}>
            {children}
        </CartContext.Provider>
    );
};
