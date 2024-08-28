import {Link} from "react-router-dom";
import React from 'react';
import classes from './Account.module.css'

export default function Account(){
    return(
        <>
        <div className={classes.rou}>
        <ul className={classes.acc_list}>
        <Link to='/' className={classes.linkes}>Главная</Link>
       
        <Link to='/personal_i' className={classes.linkes}>Личные данные</Link>
        <Link to='/order' className={classes.linkes}>История заказов</Link>
        <Link to='/recipe_yoy' className={classes.linkes}>Мои рецепты</Link>
        
              
</ul>
        </div>
        

        </>
    )
}