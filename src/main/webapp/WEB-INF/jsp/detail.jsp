<%--
  Created by IntelliJ IDEA.
  User: dtiaw
  Date: 6/14/2024
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@include file="UrlDefint.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Tap In and Tap Out</title>
    <meta
            content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
            name="viewport"
    />

    <!-- Fonts and icons -->
    <script src="<c:url value="assets/js/plugin/webfont/webfont.min.js"/> "></script>
    <script>
        WebFont.load({
            google: {families: ["Public Sans:300,400,500,600,700"]},
            custom: {
                families: [
                    "Font Awesome 5 Solid",
                    "Font Awesome 5 Regular",
                    "Font Awesome 5 Brands",
                    "simple-line-icons",
                ],
                urls: ["<c:url value="assets/css/fonts.min.css"/>"],
            },
            active: function () {
                sessionStorage.fonts = true;
            },
        });
    </script>
    <style>
        .thumbnail {
            max-width: 80px; /* Adjust the max-width as needed */
            max-height: 80px; /* Adjust the max-height as needed */
        }
    </style>
    <!-- CSS Files -->
    <link rel="stylesheet" href="<c:url value="assets/css/bootstrap.min.css"/> "/>
    <link rel="stylesheet" href="<c:url value="assets/css/plugins.min.css" />"/>
    <link rel="stylesheet" href="<c:url value="assets/css/kaiadmin.min.css"/>"/>
</head>
<body style="background-color: #f1f1f1">
<div class="wrapper">
    <div class="page-inner">
        <div
                class="d-flex align-items-left align-items-md-center flex-column flex-md-row pt-2 pb-4"
        >
            <div>
                <h3 class="fw-bold mb-3">Details</h3>
            </div>

        </div>

        <div class="row">
            <div class="col">
                <button type="button" class="btn btn-info" id="btnBack" onclick="window.location.href=dashboardUrl">
                    Back to Dashboard
                </button>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">${gate}</h4>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table
                                    id="multi-filter-select"
                                    class="display table table-striped table-hover"
                            >
                                <thead>
                                <tr>
                                    <th>Waktu</th>
                                    <th>Nomor Kartu</th>
                                    <th>Nama Pemilik kartu</th>
                                    <th>Perusahaan</th>
                                    <th>Departemen</th>
                                    <th>Status (in/out)</th>
                                    <th>Foto dan KTP Pemilik kartunya</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:if test="${not empty data}">
                                    <c:forEach var="vo" items="${data}">
                                        <tr>
                                            <td>${vo.waktu}</td>
                                            <td>${vo.noKartu}</td>
                                            <td>${vo.nama}</td>
                                            <td>${vo.perushaan}</td>
                                            <td>${vo.departement}</td>
                                            <td>${vo.status}</td>
                                            <td><img src="<c:url value="/images/${vo.foto}"/>" class="thumbnail"/></td>
                                        </tr>
                                    </c:forEach>

                                </c:if>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <form action="<c:url value="/download"/> " method="POST">
            <div class="row">
                <div class="col-4">
                    <div class="form-group">
                        <select class="form-select" id="exampleFormControlSelect1" name="fileType">
                            <option value="pdf">PDF</option>
                            <option value="xlsx">XLSX</option>
                        </select>
                    </div>
                </div>
                <div class="col-4">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">download</button>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>
<!--   Core JS Files   -->
<script src="<c:url value="assets/js/core/jquery-3.7.1.min.js"/> "></script>
<script src="<c:url value="assets/js/core/popper.min.js"/>"></script>
<script src="<c:url value="assets/js/core/bootstrap.min.js"/>"></script>

<!-- Chart JS -->
<script src="<c:url value="assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"/>"></script>

<!-- Kaiadmin JS -->
<script src="<c:url value="assets/js/kaiadmin.min.js"/>"></script>
<script src="<c:url value="assets/js/plugin/datatables/datatables.min.js"/>"></script>
<script src="<c:url value="assets/js/detail.js"/>"></script>
</body>
</html>

