import Header from "./components/Header"
import main_photo from '/main_photo.svg'
import axios from 'axios';
// Определение адреса API
// Определение адреса API
fetch('http://localhost:8080/message')
  .then(response => response.json())
  .then(data => console.log("Успех успешный"));

const apiEndpoint = 'http://localhost:8080/message';

// Отправка GET запроса к серверу
axios.get(apiEndpoint)
  .then(response => {
    // Вывод данных из ответа сервера
    console.log('Успешное подключение к серверу');
    console.log('Данные с сервера:', response.data);
  })
  .catch(error => {
    // Обработка ошибки при подключении или запросе
    console.error('Ошибка при подключении к серверу:', error);
  });


export default function App() {
  return (
  
      <div>
        <Header/>
        <main>
          <div className="container__main">
            <div className="text_content_main">
            <h1 className="titleBig">Наслаждайтесь<br/>утренним кофе
        </h1>
        <p className="title_mini">Уютная кофейня в центре Минска с атмосферным интерьером английского паба. Романтика у окна или фастдэйт в центре зала. Приятное времяпрепровождение. </p>
       <button className="buttonn"><span>Сделать заказ</span></button>
            </div>
            
       <img src={main_photo} alt=''className='main_photo'/>
       </div>
        
        </main>
        
      </div>

  )
}

