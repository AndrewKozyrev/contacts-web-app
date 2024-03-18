
# Веб приложение "список контактов"

Это Spring Boot веб приложение, работающее с БД.

Приложение написано со стеком:
* Spring Boot
* Thymeleaf
* JdbcTemplate
* Postgresql
* Docker


## Автор

- [@landsreyk](https://github.com/AndrewKozyrev)


## Установка

Для установки нужно склонировать проект, включить docker и запустить скрипт [local-run.bat](local-run.bat).

```bash
  git clone https://github.com/AndrewKozyrev/contacts-web-app.git
  cd contacts-web-app/
  ./local-run.bat
```
Данный скрипт данный скрипт запускает БД на порту `5432` с именем пользователя `postgres`, паролем `postgres`, именем базы данных `test_db`.
## Документация

В файле application.yml есть два свойства:

```
app:
  init-contacts-enabled: true
  init-contacts-count: 50
```
Первое указывает - включёно ли заполнение таблицы `contacts` случайными значениями.
Второе же указывает на количество генерируемых записей.

Для запуска приложения нужно открыть браузер и зайти на http://localhost:8080/contacts

Главная страница содержит интуитивно понятный интерфейс для работы с контактами.