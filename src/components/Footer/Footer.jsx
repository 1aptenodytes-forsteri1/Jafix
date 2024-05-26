import classes from './Footer.module.css'
export default function Footer(){
    return(
        <>
        
        <section className={classes.footer}>
        <div className={classes.box_container}>
            <div className={classes.box}>
                <h3>our branches</h3>
                <a href="Https"><i className="fas fa-arrow-right"></i> india</a>
                <a href="Https"><i className="fas fa-arrow-right"></i> USA</a>
                <a href="Https"><i className="fas fa-arrow-right"></i> france</a>
                <a href="Https"><i className="fas fa-arrow-right"></i> africa</a>
                <a href="Https"><i className="fas fa-arrow-right"></i> japan</a>
            </div>

            <div className={classes.box}>
                <h3>contact info</h3>
                <a href="Https"><i className="fas fa-phone"></i> +123-456-7890</a>
                <a href="Https"><i className="fas fa-phone"></i> +111-222-3333</a>
                <a href="Https"><i className="fas fa-envelope"></i> coffee@gmail.com</a>
                <a href="Https"><i className="fas fa-envelope"></i> Perú, Lima</a>
            </div>

            <div className={classes.box}>
                <h3>contact info</h3>
                <a href="Https"><i className="fab fa-facebook-f"></i> facebook</a>
                <a href="Https"><i className="fab fa-twitter"></i> twitter</a>
                <a href="Https"><i className="fab fa-instagram"></i> instagram</a>
                <a href="Https"><i className="fab fa-linkedin"></i> linkedin</a>
                <a href="Https"><i className="fab fa-twitter"></i> twitter</a>
            </div>
        </div>
    </section>
        </>
    );
}