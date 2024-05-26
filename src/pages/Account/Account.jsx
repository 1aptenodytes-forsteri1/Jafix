import {Link} from "react-router-dom";
import React from 'react';
import classes from './Account.module.css'

export default function Account(){
    return(
        <>
        <ul className={classes.acc_list}>
        <Link to='/' className="linkes">Главная</Link>
        <Link to='/bonus' className="linkes">Бонусы</Link>
        <Link to='/personal_i' className="linkes">Личные данные</Link>
        <Link to='/order' className="linkes">История заказов</Link>
        <Link to='/recipe_yoy' className="linkes">Мои рецепты</Link>
        <Link to='/sup' className="linkes">Служба поддержки</Link>
              
</ul>

        </>
    )
}