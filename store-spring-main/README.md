# Zadania do samodzielnego wykonania

## Zadanie 1: Utworzenie obiektu `Item`

- **Opis:** Utwórz klasę encji, która będzie reprezentować model danych przedmiotu do zakupu `Item`. Klasa powinna
  zawierać pola takie jak `id`, `name`, `quantity`. Dodatkowo, utwórz wszystkie inne obiekty, które będą niezbędne do
  zrealizowania funkcjonalności analogicznej do tej w przypadku koszyka `Basket`. Finalnie, w kontrolerze powinny
  znaleźć się metody analogiczne do tych, które są obecne dla koszyka `Basket`.
- **Wskazówki:**
    - Wzoruj się na obiekcie koszyka.

## Zadanie 2: Zmodyfikowanie obiektu koszyka `Basket`

- **Opis:** Zmodyfikuj obiekt koszyka `Basket`, tak aby przechowywał odniesienia do obiektów `Item`.
- **Wskazówki:**
    - Zamodeluj odpowiednią relację w klasie encji.
    - Zmodyfikuj pozostałe miejsca w kodzie, aby wszystkie funkcjonalności działały poprawnie.

## Zadanie 3: Rozszerzenie funkcjonalności obiektu `Item`

- **Opis:** Zmodyfikuj obiekt `Item`, dodając do niego nowe pole z ceną. Zaprojektuj nową funkcjonalność w REST API, tak
  aby można było pobrać całkowitą wartość pieniężną wszystkich elementów znajdujących się w koszyku.
- **Wskazówki:**
    - Zmodyfikuj klasę encji `Item` i dodaj do niej nowe pole.
    - Dodaj nowy endpoint REST do pobrania wartości koszyka.
    - Upewnij się, że logika biznesowa znajduje się w klasach serwisowych.
    - Pamiętaj o obsłudze wyjątków.

## Zadanie 4: Dodanie funkcjonalności "Akcja promocyjna"

- **Opis:** Dodaj funkcjonalność "Akcja promocyjna", która umożliwi wprowadzenie wartości procentowej promocji dla koszyka. Zrealizuj endpoint REST, który pozwoli na ustawienie procentowej wartości promocji. Jeśli promocja jest ustawiona, jej wartość powinna być uwzględniana przy obliczaniu całkowitej wartości koszyka. W przypadku braku promocji, wartość koszyka powinna pozostać bez zmian.
- **Wskazówki:**
  - Utwórz nową encję, np. `Promotion`, która będzie przechowywała wartość procentową promocji.
  - Dodaj odpowiednie repozytorium do zarządzania promocjami.
  - Zaimplementuj logikę biznesową w klasach serwisowych, która będzie odpowiedzialna za uwzględnianie promocji w wartości koszyka.
  - Dodaj odpowiedni model DTO do przekazywania danych między warstwami.
  - Utwórz endpoint REST w kontrolerze do ustawiania promocji.

## Zadanie 5: Dodanie obiektu `User` jako właściciela koszyka

- **Opis:** Utwórz klasę encji `User`, która będzie reprezentować właściciela koszyka `Basket`. Klasa powinna zawierać pola takie jak `id`, `name`, `surname`, `login`, `email`. Dodaj odpowiednie repozytorium, serwis oraz klasy DTO, aby umożliwić wykorzystanie obiektu `User` w aplikacji. Zrealizuj funkcjonalność REST API z metodami umożliwiającymi dodawanie, usuwanie, modyfikowanie oraz pobieranie użytkowników, zarówno pojedynczych, jak i w formie listy. Relacja między `User` a `Basket` powinna być relacją jeden-do-jeden, co oznacza, że każdy koszyk jest przypisany do konkretnego użytkownika.
- **Wskazówki:**
  - Skonfiguruj relację między `User` a `Basket` w klasie encji.
  - Upewnij się, że logika biznesowa znajduje się w warstwie serwisu.
  - Zaimplementuj pełne wsparcie dla operacji CRUD w kontrolerze REST.
  - Dodaj obsługę wyjątków, np. w przypadku prób pobrania użytkownika o nieistniejącym `id`.

## Zadanie 6: Dodanie walidacji dla obiektu `User`

- **Opis:** Dodaj walidację pól w obiekcie `User`, aby zapewnić poprawność danych przekazywanych w ramach aplikacji. Walidacja powinna obejmować pięć różnych reguł na polach obiektu. Upewnij się, że wszystkie walidacje są uwzględniane podczas operacji tworzenia oraz modyfikowania użytkownika w REST API.
- **Wymagania:**
  - Dla pola `name`:
    - **Reguła:** Imię użytkownika nie może być puste.
  - Dla pola `surname`:
    - **Reguła:** Nazwisko użytkownika musi mieć co najmniej 2 znaki.
  - Dla pola `login`:
    - **Reguła:** Login użytkownika powinien być unikalny oraz zawierać tylko litery i cyfry.
  - Dla pola `email`:
    - **Reguła:** Adres e-mail powinien być w poprawnym formacie (np. `example@example.com`).
  - Dla pola `id`:
    - **Reguła:** Identyfikator użytkownika nie może być modyfikowany po jego utworzeniu.
- **Wskazówki:**
  - Skorzystaj z adnotacji walidacyjnych takich jak `@NotBlank`, `@Size`, `@Pattern`, `@Email`, `@Immutable` (lub podobnych) na obiekcie DTO.
  - Dodaj obsługę błędów walidacyjnych kontrolerze REST z użyciem we właściwym miejscu adnotacji `@Valid`. Wykorzystaj istniejący już `@ExceptionHandler` (klasa wyjątku to: `MethodArgumentNotValidException.class`).
  - W przypadku unikalności loginu, weryfikuj istniejące wartości w bazie danych w warstwie serwisowej.