import React, { useContext, useEffect } from 'react';
import { CoffeeContext } from './CoffeeContext'; // Импортируем контекст кофеен
import axios from 'axios';

export default function MainPageOrder() {
    const { selectedCoffeePoint } = useContext(CoffeeContext);

    useEffect(() => {
        if (selectedCoffeePoint) {
            // Отправляем ID выбранной кофейни на сервер при загрузке страницы
            axios.post('http://localhost:8080/selected-coffee-point', { selectedCoffeePoint })
                .then(response => {
                    console.log('ID выбранной кофейни отправлен на сервер:', response.data);
                })
                .catch(error => {
                    console.error('Ошибка при отправке ID выбранной кофейни на сервер:', error);
                });
        }
    }, [selectedCoffeePoint]);

    return (
        <>
            <h1>Заказ</h1>
        </>
    );
}
