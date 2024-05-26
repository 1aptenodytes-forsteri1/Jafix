import React, { useContext, useState } from 'react';
import classes from './Constructor.module.css';
import { coffeeProducts } from '../Recipe/dataShop';
import { CartContext } from '../../CartContext';

function FillMenu({ id, title, ingredients, index, onCardClick }) {
    const { addToCart } = useContext(CartContext);

    const handleAddToCart = (event) => {
        event.preventDefault();
        addToCart({ title, ingredients });
        onCardClick({ id, title, ingredients });  // Pass the full recipe
    };

    const animationDelay = `calc((var(--rotate-speed) / var(--count)) * -${index}s)`;

    return (
        <li className={classes.card_item} style={{ animationDelay }} onClick={handleAddToCart}>
            <div className={classes.card}>
                <a href="/">
                    <span className={classes.model_name}>{title}</span>
                    <span>{ingredients}</span>
                </a>
            </div>
        </li>
    );
}

export default function Constructor() {
    const [selectedRecipes, setSelectedRecipes] = useState([]);
    const [recipeName, setRecipeName] = useState('');

    const handleCardClick = (recipe) => {
        setSelectedRecipes((prevRecipes) => [...prevRecipes, recipe]);
    };

    const handleRemoveRecipe = (id) => {
        setSelectedRecipes((prevRecipes) => prevRecipes.filter(recipe => recipe.id !== id));
    };

    const handleCreateRecipe = () => {
        if (recipeName && selectedRecipes.length) {
            const recipeData = {
                name: recipeName,
                ingredients: selectedRecipes,
            };
            // Send the recipe data to the server
            sendRecipeToFile(recipeData);
            // Optionally, clear the state after sending the data
            setRecipeName('');
            setSelectedRecipes([]);
        }
    };

    const sendRecipeToFile = async (recipeData) => {
        try {
            const response = await fetch('/save-recipe', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(recipeData),
            });

            if (!response.ok) {
                throw new Error('Failed to save recipe');
            }

            const result = await response.json();
            console.log(result.message);
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className={classes.consct}>
            <div className={classes.void} id="void">
                <div className={classes.crop}>
                    <ul className={classes.card_list} style={{ '--count': coffeeProducts.length }}>
                        {coffeeProducts.map((item, index) => (
                            <FillMenu key={item.id} index={index + 1} onCardClick={handleCardClick} {...item} />
                        ))}
                    </ul>
                    <div className={classes.last_circle}></div>
                    <div className={classes.second_circle}></div>
                </div>
                <div className={classes.mask}></div>
                <div className={classes.center_circle}></div>
            </div>
            <ul className={classes.create_recipe}>
                <li className={classes.create_recipe_items}><h1>Твой уникальный рецепт</h1></li>
                <li className={classes.create_recipe_items}>
                    <input 
                        type="text" 
                        className={classes.title_create_recipe} 
                        placeholder='Введи имя рецепта' 
                        value={recipeName} 
                        onChange={(e) => setRecipeName(e.target.value)} 
                    />
                </li>
                {selectedRecipes.map((recipe, index) => (
                    <li key={index} className={classes.create_recipe_items}>
                        <p className={classes.vv}>{`${recipe.title}: ${recipe.ingredients}`}</p>
                        <button className='ff' onClick={() => handleRemoveRecipe(recipe.id)}>удалить</button>
                    </li>
                ))}
                <li className={classes.create_recipe_items}>
                    <button className='btn' onClick={handleCreateRecipe}>Создать рецепт</button>
                </li>
            </ul>
        </div>
    );
}
