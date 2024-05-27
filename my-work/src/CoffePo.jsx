import React, { useContext } from 'react';
import { CoffeeContext } from './CoffeeContext'; // Импортируем контекст кофеен
import styles from './CoffePo.module.css'; // Импортируем модуль CSS для стилей

export default function CoffePo() {
    const { coffeePoints, selectedCoffeePoint, setSelectedCoffeePoint } = useContext(CoffeeContext);

    const handleSelectCoffeePoint = (id) => {
        setSelectedCoffeePoint(id === selectedCoffeePoint ? null : id); // Добавляем кофейню в контекст, если она не выбрана, иначе снимаем выбор
    };

    return (
        <>
            <h1>Выбор кофейни</h1>
            <div className={styles['coffee-points-container']}>
                {coffeePoints.map(coffeePoint => (
                    <div key={coffeePoint.id} onClick={() => handleSelectCoffeePoint(coffeePoint.id)} className={selectedCoffeePoint === coffeePoint.id ? `${styles['coffee-point']} ${styles.selected}` : styles['coffee-point']}>
                        <p>{coffeePoint.adress}</p>
                    </div>
                ))}
            </div>
            <p>Выбранный ID кофейни: {selectedCoffeePoint}</p> {/* Выводим выбранный ID */}
        </>
    );
}
