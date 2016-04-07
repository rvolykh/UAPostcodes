<%@ page language="java" contentType="text/html; UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>UA Postalcodes</title>
<link href="css/style.css" type="text/css" rel="stylesheet">
<link
  href='https://fonts.googleapis.com/css?family=Didact+Gothic&subset=latin,cyrillic-ext'
  rel='stylesheet' type='text/css'>
</head>
<body>
  <header>
    <a href="."><div id="logo"></div></a>
    <div id="slogan">ПОШТОВІ ІНДЕКСИ УКРАЇНИ</div>
  </header>
  <nav>
      <ul>
          <a href="index.html"><li>Головна</li></a>
          <a href="example"><li class="selected-nav">Поштові індекси</li></a>
      </ul>
  </nav>

  <main>
  <h1>Поштові індекси населених пунктів України</h1>
  <div class="text_wrapper">
    <table class="cities_table">
      <tr>
        <th>№</th>
        <th>Населений пункт</th>
        <th>Поштовий індекс</th>
      </tr>
      <c:forEach items="${cities.source}" var="city">
        <tr>
          <td>${city.id}</td>
          <td>${city.name}</td>
          <td>${city.postcode}</td>
        </tr>
      </c:forEach>
    </table>
    <div class="pages">
      <a class="btn-page" href="example?page=0">First</a>
      <c:if test="${cities.page>0}">
        <a class="btn-page" href="example?page=${cities.page-1}">${cities.page-1}</a>
      </c:if>
      <a class="btn-page selected-btn" href="#">${cities.page}</a>
      <c:if test="${cities.lastPage-1>=cities.page}">
        <a class="btn-page" href="example?page=${cities.page+1}">${cities.page+1}</a>
      </c:if>
      <a class="btn-page" href="example?page=${cities.lastPage}">Last</a>
    </div>
  </div>
  </main>

  <footer>
    (c) 2016, Волих Роман<br>Feel free: <a
      href="https://github.com/rvolykh/UAPostcodes">Github: UA
      Postcodes</a>
  </footer>
</body>

</html>