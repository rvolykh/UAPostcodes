# UAPostcodes
## REST: Ukrainian postal codes
## (Поштові індекси України)

Це RESTfull сервіс, за допомогою якого можна
отримати інформацію по поштових індексах України, а також
інформацію по областях, районах та населених пунктах. Передача
інформації здійснюється у JSON форматі. Дані взято із ресурсу: 
[http://ukrindex.ru](http://ukrindex.ru)

### Набір API
- [x] api/regions
- [x] api/districts?id={regionId}
- [x] api/cities?id={districtId}
- [x] api/region?id={regionId}
- [x] api/district?id={districtId}
- [x] api/city?id={cityId}
- [x] api/postalcode?id={cityId}
- [ ] api/region?name={regionName}
- [ ] api/district?name={districtName}
- [ ] api/city?name={cityName}
- [ ] api/region?code={regionCode}
- [ ] api/district?code={districtCode}
- [ ] api/city?code={cityCode}

> Openshift example :globe_with_meridians:: [http://ua-postalcodes.rhcloud.com](http://ua-postalcodes.rhcloud.com)
@rvolykh: :free: to use 


