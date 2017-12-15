<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bjarne
  Date: 12/9/17
  Time: 6:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <c:set var="errMsg" value="${null}"/>
  <c:set var="displayForm" value="${true}"/>
  <c:if test="${\"POST\".equalsIgnoreCase(pageContext.request.method)
        && pageContext.request.getParameter(\"submit\") != null}">
      <jsp:useBean id="courseBean" class="bean.Course">
          <c:catch var="beanStorageException">
              <jsp:setProperty name="courseBean" property="*" />
          </c:catch>
      </jsp:useBean>

      <c:choose>
          <c:when test="${!courseBean.isValidCourse() || beanStorageException != null}">
              <c:set var="errMsg" value="Invalid course details. Please try again"/>
          </c:when>
          <c:otherwise>
              <c:catch var="addCourseException">
                  ${courseBean.addCourse()}
              </c:catch>
              <c:choose>
                  <c:when test="${addCourseException != null}">
                      <c:set var="errMsg" value="${addCourseException.message}" />
                  </c:when>
                  <c:otherwise>
                      <c:redirect url="listCourse.jsp" />
                  </c:otherwise>
              </c:choose>
          </c:otherwise>
      </c:choose>
  </c:if>

  <jsp:useBean id="teacherBean" class="bean.Teacher" />
  <c:catch var="teacherBeanErr">
      <c:set var="teachers" value="${teacherBean.getTeachers()}" />
  </c:catch>
  <c:if test="${teacherBeanErr != null}">
      <c:set var="errMsg" value="${err.message}" />
  </c:if>

  <h2>Add Course:</h2>
  <c:if test="${errMsg != null}">
    <span style="color: red;">
    <c:out value="${errMsg}"></c:out>
    </span>
  </c:if>
  <form method="post">
      Name: <input type="text" name="name"> <br>
      Credits : <input type="text" name="credits"> <br>
      Teacher:
      <select name="teacherID">
      <c:forEach items="${teachers}" var="teacher">
          <option value="${teacher.id}">${teacher.firstName}</option>
      </c:forEach> <br>
  </select>
      <button type="submit" name="submit">Add</button>
  </form>

  </body>
</html>
