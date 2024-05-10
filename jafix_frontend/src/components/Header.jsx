import logo from '/logo.svg'
import akk from '/akk.svg'
export default function Header(){
    return (
  <header> 
    <img src={logo} alt=''className='logo'/>
      <ul>
        <li>Товар</li>
        <li>Меню</li>
        <li>Конструктор</li>
        
      </ul>
      <a href=""><img src={akk} alt=''className='akk'/></a>
          </header>
    )
  }