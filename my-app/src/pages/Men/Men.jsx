import React, { useContext, useState, useEffect } from 'react';
import classes from './Men.module.css';
import lattte from './latte.svg';
import { CartContext } from '../../CartContext';

function FillMenu(props) {
    const { addToCart } = useContext(CartContext);

    const handleAddToCart = (event) => {
        event.preventDefault();
        addToCart(props);
    };
    const componentsArray = Object.entries(props.components).map(([key, value]) => (
        <p key={key}>{key}: {value}</p>
    ));
    
    return (
        <div className={classes.box}>
            <img src={lattte} alt="" />
            <div className={classes.content}>
                <h3>{props.name}</h3>
                {componentsArray}
                <span>{props.price}</span>
                <button className='btn' data-id={props.standart_recipe_id} onClick={handleAddToCart}>Нажми</button>
            </div>
        </div>
    );
}

export default function Men() {
    const [menuItems, setMenuItems] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/standard_recipes')
            .then(response => response.json())
            .then(data => setMenuItems(data));
    }, []);

    return (
        <> 
           <h1 className="heading">Меню<span>Выбери свой кофе</span></h1>
            <div className={classes.menu}>
                <div className={classes.box_container}>
                    {menuItems.map((item, index) => (
                        <FillMenu key={index} {...item} />
                    ))}
                </div>
            </div>
        </>
    );
}