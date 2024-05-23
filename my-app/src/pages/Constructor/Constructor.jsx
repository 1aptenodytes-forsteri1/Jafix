import React, { useContext } from 'react';
import classes from './Constructor.module.css'
import { coffeeProducts } from '../Recipe/dataShop'
import { CartContext } from '../../CartContext';
function FillMenu(props) {
    const { addToCart } = useContext(CartContext);

    const handleAddToCart = (event) => {
        event.preventDefault();
        addToCart(props);
    };

    return (
        <div>
<li className={classes.card_item}>
            <div className={classes.card}><a href="/"><span className={classes.model_name}>{props.title}</span>
            <span>{props.ingredients}</span></a></div></li>
            
        </div>
        
       
    );
}
export default function Constructor(){
    return(
        <>
        <div className={classes.consct}>
        <div className={classes.void} id="void">
	<div className={classes.crop}>
	<ul className={classes.card_list} >
		<li className={classes.card_item}><div className={classes.card}><a href="/"><span className={classes.model_name}>Gretel-ACTGAN</span><span>Model for generating highly dimensional, mostly numeric, tabular data</span></a></div></li>
		<li className={classes.card_item}><div className={classes.card}><a href="/"><span className={classes.model_name}>Gretel-ACTGAN</span><span>Model for generating highly dimensional, mostly numeric, tabular data</span></a></div></li>
		<li className={classes.card_item}><div className={classes.card}><a href="/"><span className={classes.model_name}>Gretel-ACTGAN</span><span>Model for generating highly dimensional, mostly numeric, tabular data</span></a></div></li>
		<li className={classes.card_item}><div className={classes.card}><a href="/"><span className={classes.model_name}>Gretel-ACTGAN</span><span>Model for generating highly dimensional, mostly numeric, tabular data</span></a></div></li>
		<li className={classes.card_item}><div className={classes.card}><a href="/"><span className={classes.model_name}>Gretel-ACTGAN</span><span>Model for generating highly dimensional, mostly numeric, tabular data</span></a></div></li>
        <li className={classes.card_item}><div className={classes.card}><a href="/"><span className={classes.model_name}>Gretel-ACTGAN</span><span>Model for generating highly dimensional, mostly numeric, tabular data</span></a></div></li>
        <li className={classes.card_item}><div className={classes.card}><a href="/"><span className={classes.model_name}>Gretel-ACTGAN</span><span>Model for generating highly dimensional, mostly numeric, tabular data</span></a></div></li>
        <li className={classes.card_item}><div className={classes.card}><a href="/"><span className={classes.model_name}>Gretel-ACTGAN</span><span>Model for generating highly dimensional, mostly numeric, tabular data</span></a></div></li>
        <li className={classes.card_item}><div className={classes.card}><a href="/"><span className={classes.model_name}>Gretel-ACTGAN</span><span>Model for generating highly dimensional, mostly numeric, tabular data</span></a></div></li>
		<li><div className="card"><a href="/"><span className={classes.model_name}>Gretel-ACTGAN</span><span>Model for generating highly dimensional, mostly numeric, tabular data</span></a></div></li>
	</ul>
	<div className={classes.last_circle}></div>
	<div className={classes.second_circle}></div>
	</div>
	<div className={classes.mask}></div>
	<div className={classes.center_circle}></div>
</div>
<ul className={classes.create_recipe}>
    <li className={classes.create_recipe_items}><h1>Твой уникальный рецепт</h1></li>
    <li className={classes.create_recipe_items}><input type="text" className={classes.title_create_recipe} placeholder='Введи имя рецепта'/></li>
    <li className={classes.create_recipe_items}><p className={classes.vv}>ghjkl;'</p></li>
    <li className={classes.create_recipe_items}><button className='btn'>Оформи заказ</button></li>
</ul>
        </div>


        
        </>
    )
}