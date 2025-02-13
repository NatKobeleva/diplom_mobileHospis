## Запуск автотестов

**1. Установка Android Studio**

- Скачать и установить Android Studio с официального сайта
- Открыть Android Studio после установки.

**2. Клонирование репозитория**

- Открыть терминал и выполнить команду git clone

**3. Открытие проекта в Android Studio**

- Открыть проект fmh_android в Android Studio

**4. Настройка эмулятора**

- Открыть AVD Manager в Android Studio
- Создать виртуальное устройство Pixel 8 Pro API 29.
- Запустить эмулятор

**5. Запуск тестов**

- Переключиться с Android на Project
- Кликнуть по каталогу app правой кнопкой мыши и выбрать Run 'All Tests'

## Подготовка отчета

1. Скачать Allure
2. Результаты тестов найти в Device Explorer -> data -> data -> /data/data/ru.iteco.fmhandroid -> files -> build -> allure-results
3. Сохранить результаты в корневой папке проекта
4. Запустить в терминале команду allure serve
5. Посмотреть отчет в браузере
