# bookstore-web-api
Bookstore backend as not reactive

https://github.com/mrsrogers00/bookstore-reactive isimli branchte de bahsettiğim gibi security kısmını reactive web app te yapamadım bu sebeple burada sadece user servisi ile jwt kullandım. 
Herhangi bir sorunuz veya geri bildiriminiz varsa, lütfen bana ulaşmaktan çekinmeyin.


Proje Adı: Bookstore API

Kurulum Adımları
Docker Kurulumu:

Projeyi çalıştırmak için öncelikle Docker'ı kurmanız gerekmektedir. Docker'ı resmi web sitesinden indirebilir ve kurabilirsiniz.

Docker Containerlarının Başlatılması:

Docker'ı başlatmak için aşağıdaki komutları terminal veya PowerShell'de çalıştırın:

docker run --name bookstore_db -e POSTGRES_USER=compose-postgres -e POSTGRES_PASSWORD=compose-postgres -p 8001:5432 -e POSTGRES_DB=bookstore postgres:13.1-alpine

docker run --name bookstore_pgadmin -p 5050:80 -e PGADMIN_DEFAULT_EMAIL=user@domain.com -e PGADMIN_DEFAULT_PASSWORD=compose-postgres -d dpage/pgadmin4

Not: PostgreSQL ve PgAdmin containerları sırasıyla 8001 ve 5050 portlarında çalışmaktadır.

Database Bağlantısı:

PgAdmin üzerinden aşağıdaki bilgilerle giriş yapabilirsiniz:

URL: http://localhost:5050
Kullanıcı Adı: user@domain.com
Şifre: compose-postgres
Tabloların Oluşturulması:

Lisans kısıtlamaları nedeniyle Intellij Idea'nın Ultimate sürümünü kullanamadığımdan, otomatik olarak veritabanını oluşturamadım. Bu nedenle, tabloları elle oluşturmanız gerekmektedir.

Postman ile İstek Atma:

Artık Postman veya tercih ettiğiniz diğer API test araçları üzerinden isteklerinizi gönderebilirsiniz.

Proje Ortamı ve Bağımlılıklar
İşletim Sistemi: Windows 10

CPU: 13th Gen Intel(R) Core(TM) i7-13700F @ 2.10 GHz

RAM: 32.0 GB

Docker Containerları:

PostgreSQL: bookstore_db
PgAdmin: bookstore_pgadmin
Veritabanı: PostgreSQL 13.1

Geliştirme Ortamı:

IntelliJ IDEA Ultimate sürümü kullanılamamıştır.
Diğer Gereksinimler:

Postman veya benzeri bir API test aracı.
Bu readme dosyası, Bookstore API projesini çalıştırmak için gerekli adımları içermektedir. Herhangi bir sorunuz veya geri bildiriminiz varsa, lütfen bana ulaşmaktan çekinmeyin.
