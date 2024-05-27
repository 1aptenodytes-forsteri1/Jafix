import React, { createContext, useState } from 'react';

export const CoffeeContext = createContext();

export const CoffeeProvider = ({ children }) => {
    const coffeePointsData = [
        {
            id: 1,
            adress: 'Набережная 54Б',
        },
        {
            id: 2,
            adress: 'Слуцкая 18',
        },
        {
            id: 3,
            adress: 'г.Ошмяны Крачевская 3',
        },
        {
            id: 4,
            adress: 'Прилукская 8',
        },
        {
            id: 5,
            adress: 'Менеделеева 6',
        },
    ];

    const [selectedCoffeePoint, setSelectedCoffeePoint] = useState(null);

    return (
        <CoffeeContext.Provider value={{ coffeePoints: coffeePointsData, selectedCoffeePoint, setSelectedCoffeePoint }}>
            {children}
        </CoffeeContext.Provider>
    );
};
