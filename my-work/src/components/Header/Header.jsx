import {Link} from "react-router-dom";
import classes from './Header.module.css';
export default function Header(){
    return(
<>

<header className={classes.header}>
        <div id="menu-btn" className="fas fa-bars"></div>

        <a href="hhtps:/" className={classes.logo}>Jafix <i></i></a>

        
        
<ul>
<Link to='/' className={classes.linkes}>Кофейня</Link>
<Link to='/ord' className={classes.linkes}>Заказ</Link>
              <Link to='/war' className={classes.linkes}>Склад</Link>
              
              
</ul>

    </header>
</>
    );
}