import React, { useContext, useState } from 'react';
import classes from './Baskets.module.css';
import coffebas from './coffee.svg';
import recipeImage from './cross_baskets.svg';
import productImage from './imageProduct.svg';
import delete_card from './cross_baskets.svg';
import { CartContext } from '../../CartContext';
import CoffeePoints from './CoffeePoints';
import axios from 'axios';
import { useAuth } from '../../AuthContext';

export default function Baskets() {
    const { cart, setCart, calculateTotalPrice, removeFromCart } = useContext(CartContext);
    const { userId } = useAuth();  // Replace with the actual user ID
    const [showCoffeePoints, setShowCoffeePoints] = useState(false);

    const handleIncreaseQuantity = (index) => {
        const newCart = [...cart];
        newCart[index].quantity += 1;
        newCart[index].totalPrice = (parseFloat(newCart[index].price) * newCart[index].quantity).toFixed(2);
        setCart(newCart); 
    };

    const handleDecreaseQuantity = (index) => {
        const newCart = [...cart];
        if (newCart[index].quantity === 1) {
            removeFromCart(index);
        } else {
            newCart[index].quantity -= 1;
            newCart[index].totalPrice = (parseFloat(newCart[index].price) * newCart[index].quantity).toFixed(2);
            setCart(newCart); 
        }
    };

    const handleOrderClick = () => {
        setShowCoffeePoints(true);
    };

    const handleCloseCoffeePoints = () => {
        setShowCoffeePoints(false);
    };

    const handleSelectCoffeePoint = async (coffeeHouseId) => {
        const totalCost = calculateTotalPrice();

        const productOrders = cart.filter(item => item.whatIs === 'product');
        const standardRecipes = cart.filter(item => item.whatIs === 'standard_recipe');
        const customRecipes = cart.filter(item => item.whatIs === 'custom_recipe');

        const orderData = {
            cost: totalCost,
            coffeeHouseId,
            userId,
            purchase: {
                productOrders: productOrders.map(item => ({ productId: item.id }))
            },
            order: {
                standardRecipes: standardRecipes.map(item => ({ id: item.id })),
                customRecipes: customRecipes.map(item => ({ id: item.id }))
            }
        };

        try {
            await axios.post('http://localhost:8080/purchase-order', orderData);

            alert('Заказ успешно оформлен!');
            setCart([]);
            setShowCoffeePoints(false);
        } catch (error) {
            console.error('Ошибка при оформлении заказа:', error);
            alert('Произошла ошибка при оформлении заказа. Попробуйте снова.');
        }
    };


    return (
        <>
            <h1 className="heading">Корзина<span>Сделай покупку</span></h1>
            <div className="cards">
                {cart.map((item, index) => (
                    <div key={index} className={classes.card}>
                        <img className={classes.img_baskets} src={getImage(item.whatIs)} alt="" />
                        <h2 className='card_des'>{item.title}</h2>
                        <div className="amount_coffee">
                            <button className="minus_amount" onClick={() => handleDecreaseQuantity(index)}>-</button>
                            <p className='card_amount_coffee'>{item.quantity}</p>
                            <button className="plus_amount" onClick={() => handleIncreaseQuantity(index)}>+</button>
                        </div>
                        <p className='card_price'>{item.price}</p>
                        <button className={classes.card_delete} onClick={() => removeFromCart(index)}>
                            <img src={delete_card} alt="" />
                        </button>
                    </div>
                ))}
                <div className={classes.total}>
                    <h2>Total Price: {calculateTotalPrice()}</h2>
                    <button className={classes.adPoitn} onClick={handleOrderClick}>Оформить заказ</button>
                </div>
            </div>
            {showCoffeePoints && <CoffeePoints onClose={handleCloseCoffeePoints} onSelect={handleSelectCoffeePoint} />}
        </>
    );
}

const getImage = (whatIs) => {
    switch (whatIs) {
        case 'standard_recipe':
            return coffebas; 
        case 'custom_recipe':
            return recipeImage; 
        case 'product':
            return productImage; 
        default:
            return ''; 
    }
};
