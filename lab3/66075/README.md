# VeturiloBike

Jak uruchomić aplikację VeturiloBike
---

1. Uruchom `mvn clean install`, aby zbudować aplikację
2. Uruchom aplikację z `java -jar target / dropwizard.bike-1.0-SNAPSHOT.jar server config.yml`
3. Aby sprawdzić, czy aplikacja działa, wpisz adres URL `http: // localhost: 8080`


Jak uruchomić aplikację frontendową VeturiloBike
---

1. Przejdź do katalogu klienta
2. Uruchom „npm install”
3. Uruchom „npm start”
4. Aplikacja React powinna działać w `http: // localhost: 3000 /`

Aby poprawić działanie aplikacji (wyświetlanie stacji z wykresami) zalecane jest zainstalowanie i włączenie wtyczki do przeglądarki: Zezwól CORS: Access-Control-Allow-Origin.


Makieta widoku znajduje się w pliku mockOfView.jpg w katalogu głównym.
Ekran z widoku aplikacji jest dostępny w screenFromViewOfApp.jpg.

Opis
---
Aplikacja składa się z czterech stacji veturilo. Wyświetlamy nazwę użytkownika stacji, liczbę bezpłatnych rowerów dostępnych na niej, a także wykres, który pokazuje liczbę dostępnych rowerów od czasu. Po prawej stronie udostępniamy mapę użytkowników stacji z serwisu veturilo.
Widok aplikacji został zbudowany na React. Wykresy zostały zbudowane przy użyciu CanvasJS.
Udostępnianie danych frontendowi było obsługiwane przez backend poprzez cykliczne odpytywanie.
Stos zaplecza: Java, Dropwizard, PostgreSQL.

Uruchomiony próbny
---

1. Zaimportuj próbny przykładowo do SoapUI. Z katalogu veturilo \ client \ backend.
2. Uruchom to. Działa na porcie 8089. Zatem w veturilo \ client \ src \ StationsAPI.js zmień const api = "http: // localhost: 8080" na 8089.
3. Uruchom aplikację React. I zobacz wyniki na http: // localhost: 3000