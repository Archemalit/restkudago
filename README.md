# RESTful Web Service

```
Дивиров Арсен, КФУ (ИТИС)

Мои контакты: 
Telegram - @kiz_zyaka
Почта - ArsenDivirov@yandex.ru
```

Веб-сервис, который получает данные от апишки `https://kudago.com/public-api/v1.4`, после чего записывает их в `CrudRepository` для дальнейшей работы с данными.

## Основной функционал:
1. `/api/v1/places/categories`
    - **GET** - получение всех категорий в формате `json`.
    - **GET** `/{categoryId}` - получение категории по `id` в формате `json`.
    - **POST** - создание новой категории (данные новой категории передаются в формате `json`).
   Пример ввода:
    ```
    {
    "slug" : "cinema",
    "name" : "Park-Сinema"
    }
    ```
   - **PUT** `/{categoryId}` - обновление категории по `id`.
   - **DELETE** `/{categoryId}` - удаление категории по `id`.


2. `/api/v1/locations`
    - **GET** - получение всех локаций в формате `json`.
    - **GET** `/{locationId}` - получение локации по `id` в формате `json`.
    - **POST** - создание новой локации (данные новой категории передаются в формате `json`).
     Пример ввода:
    ```
    {
    "slug": "kazan",
    "name": "Kazan",
    "timezone": "Europe/Moscow",
        "coords": {
            "lat": 55.57242562,
            "lon": 49.07435358
        },
        "language": "ru"
    }
    ```
    - **PUT** `/{locationId}` - обновление локации по `id`.
    - **DELETE** `/{locationId}` - удаление локации по `id`.