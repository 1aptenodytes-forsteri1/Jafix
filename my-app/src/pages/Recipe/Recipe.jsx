import React, { useContext, useState, useEffect } from 'react';
import classes from './Recipe.module.css';
import itemProduct from './imageProduct.svg'
import { CartContext } from '../../CartContext';
function FillMenu(props) {
    const { addToCart } = useContext(CartContext);

    const handleAddToCart = (event) => {
        event.preventDefault();
        addToCart({ ...props,title: props.name, whatIs: 'product' });

    };
    const componentsArray = Object.entries(props.components).map(([key, value]) => (
        <p key={key}>{key}: {value}</p>
    ));
    return (
        <div className={classes.box}>
        <img src={itemProduct} alt="" />
        <div className={classes.content}>
            <h3>{props.name}</h3>
            {componentsArray}
            <span>{props.price} BYN</span>
            <button className='btn' data-id={props.id} onClick={handleAddToCart}>Нажми</button>
        </div>
    </div>
    );
}
export default function Recipe(){
    const [productItems, setMenuItems] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/products')
            .then(response => response.json())
            .then(data => setMenuItems(data));
    }, []);

    return(
        <>
        <h1 className="heading">Меню<span>Купи уникальный товар</span></h1>
            <div className={classes.menu}>
                <div className={classes.box_container}>
                    {productItems.map((item, index) => (
                        <FillMenu key={index} {...item} />
                    ))}
                </div>
            </div>
        </>
    )
}
