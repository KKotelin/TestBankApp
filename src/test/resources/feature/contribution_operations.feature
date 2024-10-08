Feature: Операции с вкладом

  Scenario: Пополнение вклада
    Given Открыт аккаунт с номером "ACC1122" и профилем "Алексеев Алексей"
    When Создать вклад с именем "Мой вклад", валютой "RUB", балансом 10000.0, сроком 12 месяцев и процентной ставкой 0.05
    And Пополнить вклад на сумму 2000.0
    Then Баланс вклада должен быть 12000.0

  Scenario: Расчет суммы вклада
    Given Открыт аккаунт с номером "ACC1122" и профилем "Алексеев Алексей"
    When Создать вклад с именем "Мой вклад", валютой "RUB", балансом 10000.0, сроком 12 месяцев и процентной ставкой 0.05
    And Рассчитать сумму вклада на весь срок
    Then Баланс вклада , с учетом процентов должен быть 10511.62

  Scenario: Запрос баланса вклада
    Given Открыт аккаунт с номером "ACC1122" и профилем "Алексеев Алексей"
    When Создать вклад с именем "Мой вклад", валютой "RUB", балансом 10000.0, сроком 12 месяцев и процентной ставкой 0.05
    Then Баланс вклада должен быть 10000.0

  Scenario: Закрытие вклада
    Given Открыт аккаунт с номером "ACC1122" и профилем "Алексеев Алексей"
    When Создать вклад с именем "Мой вклад", валютой "RUB", балансом 10000.0, сроком 12 месяцев и процентной ставкой 0.05
    When Закрыть вклад
    Then Вклад должен быть закрыт
