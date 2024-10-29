# Goblin's Nightmare

## Описание

Goblin's Nightmare — это текстовая игра на Java, в которой игрок сражается с врагом, выбирая различные стратегии атаки и выполняя действия, такие как атака, защита и исцеление. 

## Функциональность

- Выбор стратегии атаки (ближний бой, дальний бой, магическая атака).
- Возможность выполнять действия: атака, защита, исцеление.
- Простая логика боя между героем и врагом.

## Установка

1. Клонируйте репозиторий:
   ```bash
   git clone <[URL_РЕПОЗИТОРИЯ](https://github.com/Adilforest/Assignment_5_SDP.git)>
Перейдите в директорию проекта:

bash
Copy code
cd Goblin's Nightmare
Скомпилируйте проект:

bash
Copy code
mkdir -p out
javac -d out src/**/*.java
Запуск
Запустите игру с помощью следующей команды:

bash
Copy code
java -cp out game.SimpleGameLoop
Использование
Введите имя для вашего героя, когда игра запустится.
Выберите стратегию атаки.
Выбирайте действия в ходе боя с врагом.
Достигайте победы, снижая здоровье врага до нуля!
Примеры
Пример взаимодействия с игрой:

markdown
Copy code
Choose your attack strategy:
1. Melee Attack
2. Ranged Attack
3. Magic Attack
markdown
Copy code
--- Your turn ---
Choose an action:
1. Attack
2. Defend
3. Heal
