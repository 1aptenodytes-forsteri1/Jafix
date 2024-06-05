import React from 'react';
import classes from './CoffeePoints.module.css';
import { coffeePoitn } from './coffeePoint';

const CoffeePoints = ({ onClose, onSelect }) => {
    const handleSelect = (point) => {
        onSelect(point.id);
        onClose(); // Закрыть модальное окно после выбора
    };

    return (
        <div className={classes.overlay}>
            <div className={classes.modal}>
                <button className={classes.closeButton} onClick={onClose}>X</button>
                <h2>Выберите кофейню</h2>
                <ul>
                    {coffeePoitn.map(point => (
                        <li key={point.id} onClick={() => handleSelect(point)} className={classes.clickable}>
                            {point.adress}
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default CoffeePoints;
