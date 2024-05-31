import React, { useState, useEffect, useContext} from 'react';
import axios from 'axios';
import classes from './MainPageOrder.module.css'; 
import { CoffeeContext } from './CoffeeContext';

export default function MainPageOrder() {
    const { selectedCoffeePoint } = useContext(CoffeeContext); // Получаем выбранную кофейню из контекста
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchOrders = async () => {
            if (!selectedCoffeePoint) return; // Добавляем проверку на selectedCoffeePoint
    
            try {
                const response = await axios.get('http://localhost:8080/active_order', {
                    params: { id: selectedCoffeePoint.id } 
                });
                setOrders(response.data); 
                setLoading(false);
            } catch (error) {
                setError('Ошибка при загрузке истории заказов');
                setLoading(false);
            }
        };
    
        fetchOrders();
    }, [selectedCoffeePoint]);
    

    const handleReorder = async (id) => {
        try {
            await axios.patch('http://localhost:8080/active_order', null, {
                params: { id }
            });
            setOrders(orders.map(order => 
                order.id === id ? { ...order, active: true } : order
            ));
            alert('Заказ повторно оформлен!');
        } catch (error) {
            console.error('Ошибка при повторном заказе:', error);
            alert('Произошла ошибка при повторном заказе. Попробуйте снова.');
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
            <h1>История заказов</h1>
            <div className={classes.ordersContainer}>
                {orders.map(order => (
                    <div key={order.id} className={classes.orderCard}>
                        <h2>Заказ №{order.id}</h2>
                        <p>Стоимость: {order.cost} BYN</p>
                        <p>Статус заказа: {order.active ? 'Активен' : 'Неактивен'}</p>
                        <h3>Продукты:</h3>
                        <ul>
                            {order.purchase.productOrders.map(product => (
                                <li key={product.id}>{product.product}</li>
                            ))}
                        </ul>
                        <h3>Стандартные рецепты:</h3>
                        <ul>
                            {order.order.standardRecipes.map(recipe => (
                                <li key={recipe.id}>
                                    <strong>{recipe.name}</strong> - Компоненты: {Object.entries(recipe.components).map(([key, value]) => `${key}: ${value}`).join(', ')}
                                </li>
                            ))}
                        </ul>
                        <h3>Пользовательские рецепты:</h3>
                        <ul>
                            {order.order.customRecipes.map(recipe => (
                                <li key={recipe.id}>
                                    <strong>{recipe.name}</strong> - Компоненты: {Object.entries(recipe.components).map(([key, value]) => `${key}: ${value}`).join(', ')}
                                </li>
                            ))}
                        </ul>
                        {!order.active && (
                            <button onClick={() => handleReorder(order.id)} className={classes.reorderButton}>
                                Заказать повторно
                            </button>
                        )}
                    </div>
                ))}
            </div>
        </>
    );
}
