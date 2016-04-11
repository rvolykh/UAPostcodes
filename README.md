# UAPostcodes
## REST: Ukrainian postal codes 
### *(Поштові індекси України)*

Це RESTfull сервіс, за допомогою якого можна
отримати інформацію по поштових індексах України, а також
інформацію по областях, районах та населених пунктах. Передача
інформації здійснюється у JSON форматі. Дані взято із ресурсу: 
[http://ukrindex.ru](http://ukrindex.ru)

### Набір API
- [x] api/regions - *отримати список областей*
- [x] api/districts?id={regionId} - *отримати список районів*
- [x] api/cities?id={districtId} - *отримати список населених пунктів*
- [x] api/postalcode?id={cityId} - *отримати конкретний населений пункт*

- [x] api/region?id={regionId} - *отримати конкретну область*
- [ ] api/region?name={regionName} - *отримати конкретну область*
- [ ] api/region?code={regionCode} - *отримати конкретну область*

- [x] api/district?id={districtId} - *отримати конкретний регіон*
- [ ] api/district?name={districtName} - *отримати конкретний регіон*
- [ ] api/district?code={districtCode} - *отримати конкретний регіон*

- [x] api/city?id={cityId} - *отримати конкретний населений пункт*
- [ ] api/city?name={cityName} - *отримати конкретний населений пункт*
- [ ] api/city?code={cityCode} - *отримати конкретний населений пункт*

> Openshift example :globe_with_meridians:: [http://ua-postalcodes.rhcloud.com](http://ua-postalcodes.rhcloud.com)

@rvolykh :free: to use 


