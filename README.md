# Payment-system
Payment System with SpringBoot to transfer money between cards

## Описание 
Этот проект представляет собой платежное приложение, которое позволяет пользователям создавать учетные записи и переводить деньги между счетами. 
Приложение создано с использованием `Java` и `Spring Boot`. Он использует базу данных `PostgreSQL` для хранения и связанной с ними информации. Проект состоит из API Gateway, Config Server, Discovery Server и двух микросервисов: `Account` и `PaymentCard`.

## Запуск
Чтобы запустить проект, выполните следующие действия:
* Клонируйте репозиторий: `git clone https://github.com/Bakbergenchick/payment-system.git`
* Перейдите в проект service-registry и дальше добавляете по порядку проекты:

<img width="179" alt="Screenshot 2023-07-15 201543" src="https://github.com/Bakbergenchick/payment-system/assets/79043496/b2f57e3f-da85-44c6-ac96-85c2b6cc27e1">


* Добавить таблицы `account` и `payment` в базу данных  postgres
* Экспортируйте файл json box в Postman, если это необходимо: <img width="213" alt="image" src="https://github.com/Bakbergenchick/payment-system/assets/79043496/29ec550a-047d-47a2-856a-21d9050bba74">
