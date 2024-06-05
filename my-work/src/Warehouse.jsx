import React, { useState, useEffect, useContext } from 'react';
import { CoffeeContext } from './CoffeeContext';
import classes from './MainPageOrder.module.css'; 
import axios from 'axios';

function FillMenu({ id, ingredient, amount, expiration, handleRefill }) {
    return (
        <div className={classes.box}>
            <div className={classes.content}>
                <h3>Ингредиент: {ingredient}</h3>
                <p>Количество: {amount}</p>
                <p>Срок годности: {expiration}</p>
                <button className='btn' onClick={() => handleRefill(id, ingredient)}>Пополнить</button>
            </div>
        </div>
    );
}

export default function Warehouse() {
    const { selectedCoffeePoint } = useContext(CoffeeContext); // Получаем выбранную кофейню из контекста
    const [productItems, setProductItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchProductItems = async () => {
            try {
                const response = await axios.get('http://localhost:8080/batches', {
                    params: { coffeeHouseId: selectedCoffeePoint } // Используем ID выбранной кофейни
                });
                setProductItems(response.data);
                setLoading(false);
            } catch (error) {
                setError('Ошибка при загрузке данных о продуктах');
                setLoading(false);
            }
        };

        fetchProductItems();
    }, [selectedCoffeePoint]);

    const handleRefill = async (productId, ingredient) => {
        try {
            const response = await axios.post('http://localhost:8080/batches', {
                coffee_house_id: selectedCoffeePoint,
                ingredient: ingredient
            });
            if (response.status === 200) {
                setProductItems(prevItems => prevItems.filter(item => item.id !== productId));
            } else {
                alert('Ошибка при пополнении.');
            }
        } catch (error) {
            console.error('Ошибка при пополнении:', error);
            alert('Произошла ошибка при пополнении. Попробуйте снова.');
        }
    };

    if (loading) {
        return <p>Загрузка...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <>
            <h1 className="heading">Склад</h1>
            <div className={classes.menu}>
                <div className={classes.box_container}>
                    {productItems.map((item, index) => (
                        <FillMenu 
                            key={index} 
                            id={item.id}
                            ingredient={item.ingredient}
                            amount={item.amount}
                            expiration={item.expiration}
                            handleRefill={handleRefill} 
                        />
                    ))}
                </div>
            </div>
        </>
    );
}
