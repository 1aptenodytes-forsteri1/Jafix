import {Link} from "react-router-dom";
import classes from './Header.module.css';
export default function Header(){
    return(
<>

<header className={classes.header}>
        <div id="menu-btn" className="fas fa-bars"></div>

        <a href="hhtps:/" className={classes.logo}>Jafix <i></i></a>

        
        
<ul>
<Link to='/' className={classes.linkes}>Главная</Link>
              <Link to='/menu' className={classes.linkes}>Меню</Link>
              <Link to='/rec' className={classes.linkes}>Товары</Link>
              <Link to='/cos' className={classes.linkes}>Конструктор</Link>
              <Link to='/bas' className={classes.linkes}>Корзина</Link>
              
</ul>
<Link to='/acc' >Аккаунт</Link>
              
           
            
        <img src="" alt=""/>
    </header>
</>
    );
}