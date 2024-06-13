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
    <style></style>
    <!-- CSS Files -->
    <link rel="stylesheet" href="<c:url value="assets/css/bootstrap.min.css"/> "/>
    <link rel="stylesheet" href="<c:url value="assets/css/plugins.min.css" />"/>
    <link rel="stylesheet" href="<c:url value="assets/css/kaiadmin.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="assets/css/bootstrap-datepicker.css"/>"/>
    <link rel="stylesheet" href="<c:url value="assets/css/rome.css"/>"/>
</head>
<body style="background-color: #f1f1f1">
<div class="wrapper">
    <div class="page-inner">
        <div
                class="d-flex align-items-left align-items-md-center flex-column flex-md-row pt-2 pb-4"
        >
            <div>
                <h3 class="fw-bold mb-3">Dashboard</h3>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="card card-round">
                    <div class="card-body">
                        <table>
                            <tr>
                                <td><h4>Tanggal</h4></td>
                                <td>
                                    <div class="form-group">
                                        <input
                                                type="text"
                                                class="form-control"
                                                id="input_from"
                                                placeholder="dd-mm-yyyy"
                                        />
                                    </div>
                                </td>
                                <td><h4>S/D</h4></td>
                                <td>
                                    <div class="form-group">
                                        <input
                                                type="text"
                                                class="form-control"
                                                id="input_to"
                                                placeholder="dd-mm-yyyy"
                                        />
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group">
                                        <button type="button" class="btn btn-info" id="searchBtn">
                                            Search
                                        </button>
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group">
                                        <button
                                                type="button"
                                                class="btn btn-info"
                                                hidden="hidden"
                                                id="backBtn"
                                        >
                                            back
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col"></div>
            <div class="col"></div>
        </div>

        <div class="row"><br/></div>
        <div class="row" id="countCard">
        </div>
        <div class="row" id="cart">
        </div>
    </div>
</div>

<!--   Core JS Files   -->
<script src="<c:url value="assets/js/core/jquery-3.7.1.min.js"/> "></script>
<script src="<c:url value="assets/js/core/popper.min.js"/>"></script>
<script src="<c:url value="assets/js/core/bootstrap.min.js"/>"></script>

<!-- Chart JS -->
<script src="<c:url value="assets/js/plugin/chart.js/chart.min.js"/>"></script>
<script src="<c:url value="assets/js/plugin/datepicker/bootstrap-datepicker.js"/>"></script>


<!-- jQuery Sparkline -->
<script src="<c:url value="assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"/>"></script>

<!-- Kaiadmin JS -->
<script src="<c:url value="assets/js/kaiadmin.min.js"/>"></script>

<!-- Date Picker -->
<script src="<c:url value="assets/js/popper.min.js"/>"></script>
<script src="<c:url value="assets/js/rome.js"/>"></script>
<script src="<c:url value="assets/js/main.js"/>"></script>

</body>
</html>