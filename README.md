# BeatdevUserInfoAPI
Project for BeatDev

* Проект для работы с информацией пользователя.
* По ТЗ был создан фильтр с искусственной задержкой в 5 секунд для всех эндпоинтов сервера !!!
* В приложении присутствует swagger2 Open Api для документации ендпоинтов приложения.
* Так же в приложении присутствует Checkstyle plugin для анализа чистоты кода. При попытке сборки проекта, который не соответствует правилам Checkstyle, проект не будет собран.
* Было реализовано 3 эндпоинта:
1) Создание нового пользователя(возвращается id нового пользователя, если при создании данные были валидные, в противном  случаевернется 404 код ошибки)
2) Поиск пользователя по id(возвращается DTO с информацией пользователя, если был введён верный id, в противном случае вернется 404 код ошибки)
3) Обновление статуса пользователя(возвращается DTO с id пользователя, текущем статусом, предыдущем статусом, в противном случае возможны 400 и 404 коды ошибок)

	<h2>Запуск приложения (1 Вариант):</h2>
ПРИЛОЖЕНИЕ МОЖЕТ ЗАПУСКАТЬСЯ ДОЛГО ИЗ-ЗА ИСКУССТВЕННОЙ ЗАДЕРЖКИ !!!

1. Перейти в корневую папку приложения через консоль
2. Запустить docker-compose.yml файл через команду в консоли: docker-compose up
3. Протестировать эндпоинты и посмотреть основную документацию проекта можно в swagger2 Open Api по ссылке: http://localhost:8080/swagger-ui/


	Примечания:
User status сделал enam, а не boolean для масштабирования в будущем(вдруг добавят статус «не на месте»  и тд.)

