1 - скачать и установить базу из msi 

2 - создать а диске с: структуру
  c:\mongodb\data
  c:\mongodb\log

  создать файл mongo.config
  c:\mongodb\mongo.config
   в него вставить содержимое :
   	
   	##store data
	dbpath=C:\mongodb\data
 
	##Log File
	logpath=C:\mongodb\log\mongo.log
 
	##log read and write operations
	diaglog=3

3 - открыть терминал из папки bin инсталяционной директории mongo 
	(в моём случае -> C:\Program Files\MongoDB\Server\3.2\bin>)
	и выполнить: mongod.exe --config c:\mongodb\mongo.config

4 - открыть ещё одну консоль из того же места и выполнить 
	mongo.exe
	затем выполнить 
	use admin  (должно ответить -> switched to db admin)
	db.createUser({ user:"admin", pwd: "admin", roles: [] }) 
	(должно ответить Successfully added user: { "user" : "admin", "roles" : [ ] })