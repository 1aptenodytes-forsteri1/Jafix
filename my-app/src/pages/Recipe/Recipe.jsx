import React, { useContext } from 'react';
import classes from './Recipe.module.css';
import itemProduct from './imageProduct.svg'
import { coffeeProducts } from './dataShop';
import { CartContext } from '../../CartContext';
function FillMenu(props) {
    const { addToCart } = useContext(CartContext);

    const handleAddToCart = (event) => {
        event.preventDefault();
        addToCart(props);
    };

    return (
        <div className={classes.box}>
            <img src={itemProduct} alt="" />
            <div className={classes.content}>
                <h3>{props.title}</h3>
                <p>{props.ingredients}</p>
                <span>{props.price}</span>
                <button className='btn' data-id={props.id} onClick={handleAddToCart}>Нажми</button>
            </div>
        </div>
    );
}
export default function Recipe(){
    return(
        <>
        <h1 className="heading">Магазин<span>Купи уникальный товар</span></h1>
            <div className={classes.menu}>
                <div className={classes.box_container}>
                    {coffeeProducts.map((item, index) => (
                        <FillMenu key={index} {...item} />
                    ))}
                </div>
            </div>
        </>
    )
}