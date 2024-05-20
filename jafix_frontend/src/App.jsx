import Header from "./components/Header"
import main_photo from '/main_photo.svg'
import React from "react";
import { useForm } from "react-hook-form";
import axios from "axios";
import "./App.css";

function App() {
  const { register, handleSubmit, formState: { errors } } = useForm();

  const onSubmit = (data) => {
    // Определение адреса API для отправки данных
    const apiEndpoint = 'http://localhost:8080/user'; // Замените на корректный адрес API

    // Отправка POST запроса с данными формы на сервер
    axios.post(apiEndpoint, data)
      .then(response => {
        // Вывод данных из ответа сервера (если нужно)
        console.log('Успешное подключение к серверу');
        console.log('Ответ сервера:', response.data);
      })
      .catch(error => {
        // Обработка ошибки при подключении или запросе
        console.error('Ошибка при подключении к серверу:', error);
      });
  };

  return (
    <>
      <p className="title">Registration Form</p>

      <form className="App" onSubmit={handleSubmit(onSubmit)}>
        <input type="text" placeholder="Name" {...register("name")} />
        <input type="text" placeholder="Surname" {...register("surname")} />
        <input type="text" placeholder="Login" {...register("login")} />
        <input
          type="password"
          placeholder="Password"
          {...register("password")}
        />
        <input type={"submit"} style={{ backgroundColor: "#a1eafb" }} />
      </form>
    </>
  );
}

export default App;


/*export default function App() {
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
}*/

