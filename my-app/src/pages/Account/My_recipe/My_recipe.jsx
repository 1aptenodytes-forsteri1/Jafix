import React, { useContext, useState, useEffect } from 'react';
import axios from 'axios'; // Импортируем Axios
import classes from './My_recipe.module.css';
import lattte from '../../Men/latte.svg';
import { CartContext } from '../../../CartContext';
import podl from './qwerty.jpg'
import { useAuth } from '../../../AuthContext'; // Импортируем контекст аутентификации

function FillMenu(props) {
    const { addToCart } = useContext(CartContext);

    const handleAddToCart = (event) => {
        event.preventDefault();
        addToCart({ ...props, title: props.name, price: props.cost, whatIs: 'custom_recipe' });

    };

    const componentsArray = Object.entries(props.components).map(([key, value]) => (
        <p key={key}>{key}: {value}</p>
    ));

    return (
        <div className={classes.box}>
            <img className={classes.imgLog} src={lattte} alt="" />
            <div className={classes.content}>
                <h3>{props.name}</h3>
                {componentsArray}
                <span>{props.cost} BYN</span>
                <button className='btn' data-id={props.id} onClick={handleAddToCart}>Нажми</button>
            </div>
        </div>
    );
}

export default function My_recipe() {
    const { userId } = useAuth(); // Получаем userId из контекста аутентификации
    const [menuItems, setMenuItems] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/custom_recipes?userId=${userId}`);
                setMenuItems(response.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        if (userId) {
            fetchData();
        }
    }, [userId]);

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
