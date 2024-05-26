import React, { useContext } from 'react';
import classes from './Baskets.module.css';
import coffebas from './coffee.svg';
import otherProductImage from './imageProduct.svg'; // Замените на путь к вашему изображению для другого товара
import delete_card from './cross_baskets.svg';
import { CartContext } from '../../CartContext';


export default function Baskets() {
    const { cart, setCart, calculateTotalPrice, removeFromCart } = useContext(CartContext);

    const handleIncreaseQuantity = (index) => {
        const newCart = [...cart];
        newCart[index].quantity += 1;
        newCart[index].totalPrice = (parseFloat(newCart[index].price) * newCart[index].quantity).toFixed(2);
        setCart(newCart); // Используем addToCart для обновления состояния корзины
    };

    const handleDecreaseQuantity = (index) => {
        const newCart = [...cart];
        if (newCart[index].quantity === 1) {
            removeFromCart(index);
        } else {
            newCart[index].quantity -= 1;
            newCart[index].totalPrice = (parseFloat(newCart[index].price) * newCart[index].quantity).toFixed(2);
            setCart(newCart); // Используем addToCart для обновления состояния корзины
        }
    };

    return (
        <>
        <h1 className="heading">Корзина<span>Сделай покупку</span></h1>
            <div className="cards">
                {cart.map((item, index) => (
                    <div key={index} className={classes.card}>
                        <img className={classes.img_baskets} src={item.type === 'coffee' ? coffebas : otherProductImage} alt="" />
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
                </div>
            </div>
        </>
    );
}
