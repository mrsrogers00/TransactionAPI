# TransactionAPI
Kurulum Adımları Docker Kurulumu:

Projeyi çalıştırmak için öncelikle Docker'ı kurmanız gerekmektedir. Docker'ı resmi web sitesinden indirebilir ve kurabilirsiniz.

Docker Containerlarının Başlatılması:

Docker'ı başlatmak için aşağıdaki komutları terminal veya PowerShell'de çalıştırın:

docker run --name bookstore_db -e POSTGRES_USER=compose-postgres -e POSTGRES_PASSWORD=compose-postgres -p 8001:5432 -e POSTGRES_DB=bookstore postgres:13.1-alpine

docker run --name bookstore_pgadmin -p 5050:80 -e PGADMIN_DEFAULT_EMAIL=user@domain.com -e PGADMIN_DEFAULT_PASSWORD=compose-postgres -d dpage/pgadmin4

Not: PostgreSQL ve PgAdmin containerları sırasıyla 8001 ve 5050 portlarında çalışmaktadır.

Eğer containerların birbiri ile connectionında sorun varsa diyip connection sağlayabilirsiniz. bridgeden sonra da container ismi eklenmesi gerekir commande
docker inspect network bridge 

pgAdmine http://localhost:5050/browser/ üzerinden bağlanıp yeni server oluşturup bu server içinde gerekli connectionları sağlamanız gerekir(localhost üzerinden değil de subnet üzerinden bağlantı sağlayabilirsiniz)

projeyi ayağa kaldırdıktan sonra da jpa ile tablelar auto generate olur ve bağlabır birbirine foreign keylerle.

http://localhost:8000/swagger-ui/index.html# swaggera girebilirsiniz

