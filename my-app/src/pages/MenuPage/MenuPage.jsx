import React from 'react';
import classes from './MenuPage.module.css';
import home1 from './image/home-img-1.png'
import home2 from './image/home-img-2.png'
import home3 from './image/home-img-3.png'
import about from './image/about-img.png'
import about1 from './image/about-icon-1.png'
import about2 from './image/about-icon-2.png'
import about3 from './image/about-icon-3.png'
import menu1 from './image/menu-1.png'
import menu2 from './image/menu-2.png'
import menu3 from './image/menu-3.png'
import menu4 from './image/menu-4.png'
import menu5 from './image/menu-5.png'
import menu6 from './image/menu-6.png'
export default function MenuPage(){
    return(
<>
<section className={classes.home} id="home">
        <div className={classes.row}>
            <div className={classes.content}>
                <h3>fresh coffee in the morning</h3>
                <a href="/" className="btn">buy one now</a>
            </div>

            <div className={classes.image}>
                <img src={home1} className={classes.main_home_image} alt=""/>
            </div>
        </div>

        <div className={classes.image_slider}>
            <img src={home1} className={classes.choosing} alt=""/>
            <img src={home2} className={classes.choosing} alt=""/>
            <img src={home3} className={classes.choosing} alt=""/>
        </div>
    </section>

    <section className={classes.about} id="about">
        <h1 className="heading">about us <span>why choose us</span></h1>

        <div className={classes.row}>
            <div className={classes.image}>
                <img src={about} alt=""/>
            </div>

            <div className={classes.content}>
                <h3 className={classes.title}>ake our coffee special!</h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Vel rerum laboriosam reprehenderit ipsa id
                    repellat odio illum, voluptas, necessitatibus assumenda adipisci. Hic, maiores iste? Excepturi illo
                    dolore mollitia qui quia.</p>
                <a href="/" className="btn">read more</a>
                <div className={classes.icons_container}>
                    <div className={classes.icons}>
                        <img src={about1} alt=""/>
                        <h3>quality coffee</h3>
                    </div>
                    <div className={classes.icons}>
                        <img src={about2} alt=""/>
                        <h3>our branches</h3>
                    </div>
                    <div className={classes.icons}>
                        <img src={about3} alt=""/>
                        <h3>free delivery</h3>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <section className={classes.menu} id="menu">
        <h1 className="heading">our menu <span>popular menu</span></h1>

        <div className={classes.box_container}>
            <a href="/" className={classes.box}>
                <img src={menu1} alt=""/>
                <div className={classes.content}>
                    <h3>our special coffee</h3>
                    <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Tenetur, sed.</p>
                    <span>$8.99</span>
                </div>
            </a>

            <a href="/" className={classes.box}>
                <img src={menu2} alt=""/>
                <div className={classes.content}>
                    <h3>our special coffee</h3>
                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Vel, fugit.</p>
                    <span>$8.99</span>
                </div>
            </a>

            <a href="/" className={classes.box}>
                <img src={menu3} alt=""/>
                <div className={classes.content}>
                    <h3>our special coffee</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatibus, recusandae.</p>
                    <span>$8.99</span>
                </div>
            </a>

            <a href="/" className={classes.box}>
                <img src={menu4} alt=""/>
                <div className={classes.content}>
                    <h3>our special coffee</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Esse, quas.</p>
                    <span>$8.99</span>
                </div>
            </a>

            <a href="/" className={classes.box}>
                <img src={menu5} alt=""/>
                <div className={classes.content}>
                    <h3>our special coffee</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Officia, vitae.</p>
                    <span>$8.99</span>
                </div>
            </a>

            <a href="/" className={classes.box}>
                <img src={menu6} alt=""/>
                <div className={classes.content}>
                    <h3>our special coffee</h3>
                    <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Unde, expedita!</p>
                    <span>$8.99</span>
                </div>
            </a>
        </div>
    </section>


    
</>
    );
}