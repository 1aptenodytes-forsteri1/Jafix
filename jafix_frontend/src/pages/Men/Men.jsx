import React, { useContext, useEffect, useState } from 'react';
import classes from './Men.module.css';
import lattte from './latte.svg';
import { CartContext } from '../../CartContext';

function FillMenu(props) {
    const { addToCart } = useContext(CartContext);

    const handleAddToCart = (event) => {
        event.preventDefault();
        addToCart(props);
    };

    return (
        <div className={classes.box}>
            <img src={lattte} alt="" />
            <div className={classes.content}>
                <h3>{props.name}</h3>
                <p>{props.components}</p>
                <span>{props.price}</span>
                <button className='btn' data-id={props.standart_recipe_id} onClick={handleAddToCart}>Нажми</button>
            </div>
        </div>
    );
}

export default function Men() {
    const [coffeeProducts, setCoffeeProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchCoffeeProducts = async () => {
            try {
                const response = await fetch('/api/coffee'); // Тут ссылку вставь
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                setCoffeeProducts(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchCoffeeProducts();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <>
            <h1 className="heading">Меню<span>Выбери свой кофе</span></h1>
            <div className={classes.menu}>
                <div className={classes.box_container}>
                    {coffeeProducts.map((item, index) => (
                        <FillMenu key={index} {...item} />
                    ))}
                </div>
            </div>
        </>
    );
}
