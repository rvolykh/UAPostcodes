<%@ page language="java" contentType="text/html; UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
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
    <div id="logo"></div>
    <div id="slogan">ПОШТОВІ ІНДЕКСИ УКРАЇНИ</div>
  </header>

  <main>
  <h1>Сервіс</h1>
  <div class="text_wrapper">
    <table>
      <tr>
        <th>№</th>
        <th>City</th>
        <th>Postal code</th>
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
      <a href="example?page=0">First</a>
      <c:if test="${cities.page>0}">
        <a href="example?page=${cities.page-1}">${cities.page-1}</a>
      </c:if>
      <a id="selected_page" href="example?page=${cities.page}">${cities.page}</a>
      <c:if test="${cities.lastPage-1>=cities.page}">
        <a href="example?page=${cities.page+1}">${cities.page+1}</a>
      </c:if>
      <a href="example?page=${cities.lastPage}">Last</a>
    </div>
  </div>
  </main>

  <footer> (c) 2016, Волих Роман </footer>
</body>

</html>