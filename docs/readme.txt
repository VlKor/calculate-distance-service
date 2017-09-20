1. База данных накатывается скриптом db-dump.sql, username/password: root/root
2. Точки и примеры формата запросов:
2.1. GET /city
	 Content-Type: application/xml

2.2. POST /calculation
		Content-Type: application/xml
		Body:
		<collection>
			<request>
				<type>CROWFLIGHT</type>
				<fromCity>San Francisco</fromCity>
				<toCity>Los Angeles</toCity>
			</request>
			<request>
				<type>DISTANCE_MATRIX</type>
				<fromCity>Dallas</fromCity>
				<toCity>Jacksonville</toCity>
			</request>
		</collection>
		
2.3. POST /upload
		Content-Type:multipart/form-data
		Body:
		<dataToUpload>
			<city>
				<latitude>40.7127837</latitude>
				<longitude>-74.0059413</longitude>
				<name>New York</name>
			</city>
			<city>
				<latitude>34.0522342</latitude>
				<longitude>-118.2436849</longitude>
				<name>Los Angeles</name>
			</city>
			<distance>
				<fromCity>Jacksonville</fromCity>
				<toCity>Fort Worth</toCity>
				<distance>12345</distance>
			</distance>
			<distance>
				<fromCity>El Paso</fromCity>
				<toCity>Fort Worth</toCity>
				<distance>5646</distance>
			</distance>
		</dataToUpload>
		
3. Демо-файл для загрузки данных в БД - upload.xml. Файл может содержать одновременно сущности городов и дистанций, а также может содержать только сущности одного типа.
4. API приложения тестировалось с помощью Postman (https://www.getpostman.com/)
