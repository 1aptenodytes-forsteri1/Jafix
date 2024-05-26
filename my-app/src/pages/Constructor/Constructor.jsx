import React, { useContext, useState, useEffect } from 'react';
import classes from './Constructor.module.css';

import { CartContext } from '../../CartContext';
import { fetchIngredients } from './ingredientService';


function FillMenu({ id, name, description, price, units, index, onCardClick }) {
    const { addToCart } = useContext(CartContext);
  
    const handleAddToCart = (event) => {
      event.preventDefault();
      addToCart({ id, name, description, price, units });
      onCardClick({ id, name, description, price, units });
    };
  
    const animationDelay = `calc((var(--rotate-speed) / var(--count)) * -${index}s)`;
  
    return (
      <li className={classes.card_item} style={{ animationDelay }} onClick={handleAddToCart}>
        <div className={classes.card}>
          <a href="/">
            <span className={classes.model_name}>{name}</span>
            <span>{description}</span>
            <span>{`Price: ${price}`}</span>
            <span>{`Units: ${units}`}</span>
          </a>
        </div>
      </li>
    );
  }
  

export default function Constructor() {
    const { addToCart } = useContext(CartContext);
    const [ingredients, setIngredients] = useState([]);
    const [selectedRecipes, setSelectedRecipes] = useState([]);
    const [recipeName, setRecipeName] = useState('');
  
    useEffect(() => {
      const getIngredients = async () => {
        try {
          const data = await fetchIngredients();
          setIngredients(data);
        } catch (error) {
          console.error('Failed to fetch ingredients:', error);
        }
      };
  
      getIngredients();
    }, []);
  
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
          description: selectedRecipes,
        };
        sendRecipeToFile(recipeData);
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
              <ul className={classes.card_list} style={{ '--count': ingredients.length }}>
                {ingredients.map((item, index) => (
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
                <p className={classes.vv}>{`${recipe.title}: ${recipe.description.map(ing => ing.name).join(', ')}`}</p>
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