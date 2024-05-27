import React, { useContext, useState, useEffect } from 'react';
import classes from './Constructor.module.css';
import { CartContext } from '../../CartContext';
import { useAuth } from '../../AuthContext'; // Импортируем контекст аутентификации
import { fetchIngredients } from './ingredientService';
import axios from 'axios';


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
        </a>
      </div>
    </li>
  );
}

export default function Constructor() {
  const { addToCart } = useContext(CartContext);
  const { userId } = useAuth(); // Получаем userId из контекста аутентификации
  const [ingredients, setIngredients] = useState([]);
  const [selectedRecipes, setSelectedRecipes] = useState([]);
  const [recipeName, setRecipeName] = useState('');
  const [totalPrice, setTotalPrice] = useState(0);
  const [isServerConnected, setIsServerConnected] = useState(false);


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
    setSelectedRecipes((prevRecipes) => [...prevRecipes, { ...recipe, quantity: 1 }]);
  };

  const handleRemoveRecipe = (id) => {
    setSelectedRecipes((prevRecipes) => prevRecipes.filter(recipe => recipe.id !== id));
  };

  const handleQuantityChange = (id, newQuantity) => {
    setSelectedRecipes((prevRecipes) =>
      prevRecipes.map(recipe =>
        recipe.id === id ? { ...recipe, quantity: newQuantity } : recipe
      )
    );
  };
useEffect(() => {
    const checkServerConnection = async () => {
      try {
        const response = await fetch('http://localhost:8080/check-connection');
        if (response.ok) {
          setIsServerConnected(true);
        } else {
          setIsServerConnected(false);
        }
      } catch (error) {
        setIsServerConnected(false);
      }
    };

    checkServerConnection(); // Проверяем соединение при монтировании компонента
  }, []);

  useEffect(() => {
    const newTotalPrice = selectedRecipes.reduce((total, recipe) => {
      return total + (recipe.price * recipe.quantity);
    }, 0);
    setTotalPrice(newTotalPrice);
  }, [selectedRecipes]);

  const handleCreateRecipe = () => {
    if (recipeName && selectedRecipes.length) {
      const components = selectedRecipes.reduce((acc, recipe) => {
        acc[recipe.name] = `${recipe.quantity}}`;

        return acc;
      }, {});

      const recipeData = {
        userId,
        cost: totalPrice.toFixed(2),
        name: recipeName,
        components,
      };

      sendRecipeToFile(recipeData);
      setRecipeName('');
      setSelectedRecipes([]);
      setTotalPrice(0);
    }
  };

  const sendRecipeToFile = async (recipeData) => {
    try {
      const response = await axios.post('http://localhost:8080/custom_recipes', recipeData, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      console.log(response.data.message);
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
            <p className={classes.vv}>{`${recipe.name}`}</p>
            <input
              className="tttt"
              type="number"
              value={recipe.quantity}
              min="1"
              onChange={(e) => handleQuantityChange(recipe.id, parseInt(e.target.value) || 1)}
            />
            <button className='ff' onClick={() => handleRemoveRecipe(recipe.id)}>удалить</button>
          </li>
        ))}
        <li className={classes.create_recipe_items}>
          <button className='btn' onClick={handleCreateRecipe}>Создать рецепт</button>
        </li>
        <li className={classes.create_recipe_items}>
          <p className='final_price'>Итоговая цена: {totalPrice.toFixed(2)}</p>
        </li>
      </ul>
    </div>
  );
}
