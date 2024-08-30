Feature: Создание продуктов

  Scenario: Создание дебетовой карты
    Given Открыт аккаунт с номером "ACC1234" и профилем "Иванов Иван"
    When Создать дебетовую карту с именем "Моя дебетовая карта", валютой "RUB", балансом 5000.0
    Then Дебетовая карта должна быть создана с именем "Моя дебетовая карта"
    And Баланс дебетовой карты должен быть 5000.0

  Scenario: Создание валютной карты
    Given Открыт аккаунт с номером "ACC5678" и профилем "Петров Петр"
    When Создать валютную карту с именем "Моя валютная карта", валютой "USD", балансом 1000.0
    Then Валютная карта должна быть создана с именем "Моя валютная карта"
    And Баланс валютной карты должен быть 1000.0

  Scenario: Создание кредитной карты
    Given Открыт аккаунт с номером "ACC9101" и профилем "Сидоров Сидор"
    When Создать кредитную карту с именем "Моя кредитная карта", валютой "RUB", балансом 2000.0 и процентной ставкой 0.2
    Then Кредитная карта должна быть создана с именем "Моя кредитная карта"
    And Баланс кредитной карты должен быть 2000.0
    And Процентная ставка кредитной карты должна быть 0.2

  Scenario: Создание вклада
    Given Открыт аккаунт с номером "ACC1122" и профилем "Алексеев Алексей"
    When Создать вклад с именем "Мой вклад", валютой "RUB", балансом 10000.0, сроком 12 месяцев и процентной ставкой 0.05
    Then Вклад должен быть создан с именем "Мой вклад"
    And Баланс вклада должен быть 10000.0
    And Срок вклада должен быть 12 месяцев
    And Процентная ставка вклада должна быть 0.05
