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

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Tap In and Tap Out</title>
    <meta
            content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
            name="viewport"
    />
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
    <div class="sidebar" data-background-color="dark">
        <div class="sidebar-logo">
            <!-- Logo Header -->
            <div class="logo-header" data-background-color="dark">
                <button
                        class="navbar-toggler sidenav-toggler ms-auto"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="collapse"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                >
              <span class="navbar-toggler-icon">
                <i class="gg-menu-right"></i>
              </span>
                </button>
                <button class="topbar-toggler more">
                    <i class="icon-options-vertical"></i>
                </button>
                <div class="nav-toggle">
                    <button class="btn btn-toggle toggle-sidebar">
                        <i class="gg-menu-right"></i>
                    </button>
                    <button class="btn btn-toggle sidenav-toggler">
                        <i class="gg-menu-left"></i>
                    </button>
                </div>
            </div>
            <!-- End Logo Header -->
        </div>
        <div class="sidebar-wrapper scrollbar scrollbar-inner">
            <div class="sidebar-content">
                <ul class="nav nav-secondary">
                    <li class="nav-item">
                        <a href="<c:url value="/dashboard"/> ">
                            <i class="fas fa-home"></i>
                            <p>Dashboard</p>
                        </a>
                    </li>
                </ul>
                <!-- <ul class="nav nav-secondary">
                  <li class="nav-item">
                    <a href="karyawan.html">
                      <i class="fas fa-address-card"></i>
                      <p>Karyawan Data</p>
                    </a>
                  </li>
                </ul> -->
            </div>
        </div>
    </div>
    <!-- End Sidebar -->
    <div class="main-panel">
        <div class="main-header">
            <div class="main-header-logo">
                <!-- Logo Header -->
                <div class="logo-header" data-background-color="dark">
                    <div class="nav-toggle">
                        <button class="btn btn-toggle toggle-sidebar">
                            <i class="gg-menu-right"></i>
                        </button>
                        <button class="btn btn-toggle sidenav-toggler">
                            <i class="gg-menu-left"></i>
                        </button>
                    </div>
                    <button class="topbar-toggler more">
                        <i class="gg-more-vertical-alt"></i>
                    </button>
                </div>
                <!-- End Logo Header -->
            </div>
            <!-- Navbar Header -->
            <nav
                    class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom"
                    data-background-color="dark"
            >
                <div class="container-fluid">
                    <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
                        <li class="nav-item topbar-user dropdown hidden-caret">
                            <a
                                    class="dropdown-toggle profile-pic"
                                    data-bs-toggle="dropdown"
                                    href="#"
                                    aria-expanded="false"
                            >
                                <div class="avatar-sm">
                                    <img
                                            src="<c:url value="assets/img/user.png"/>"
                                            alt="..."
                                            class="avatar-img rounded-circle"
                                    />
                                </div>
                                <span class="profile-username">
                      <span class="op-7">Hi,</span>
                      <span class="fw-bold">Hizrian</span>
                    </span>
                            </a>
                            <ul class="dropdown-menu dropdown-user animated fadeIn">
                                <div class="dropdown-user-scroll scrollbar-outer">
                                    <li>
                                        <a class="dropdown-item" href="#">Logout</a>
                                    </li>
                                </div>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- End Navbar -->
        </div>
        <div class="container">
            <div class="page-inner">
                <div class="page-header">
                    <h3 class="fw-bold mb-3">Details</h3>
                </div>

                <div class="row">
                    <div class="col">
                        <button type="button" class="btn btn-info" id="btnBack"
                                onclick="window.location.href=dashboardUrl">
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
                                            <th>Photo</th>
                                            <th>Waktu</th>
                                            <th>Lokasi</th>
                                            <th>Status</th>
                                            <th>No. ID</th>
                                            <th>Kartu</th>
                                            <th>Nama</th>
                                            <th>Jabatan</th>
                                            <th>Zona</th>
                                            <th>Perusahan</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:if test="${not empty data}">
                                            <c:forEach var="vo" items="${data}">
                                                <tr>
                                                    <td><img src="<c:url value="/images/${vo.foto}"/>"
                                                             class="thumbnail"/>
                                                    </td>
                                                    <td>${vo.waktu}</td>
                                                    <td>${gate}</td>
                                                    <td>${vo.status}</td>
                                                    <td>${vo.noKartu}</td>
                                                    <td>${vo.kartu}</td>
                                                    <td>${vo.nama}</td>
                                                    <td>${vo.jabatan}</td>
                                                    <td>${vo.zona}</td>
                                                    <td>${vo.perushaan}</td>
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
    </div>

</div>
<%@include file="UrlDefint.jsp" %>
<script src="<c:url value="assets/js/plugin/webfont/webfont.min.js"/> "></script>
<!--   Core JS Files   -->
<script src="<c:url value="assets/js/core/jquery-3.7.1.min.js"/> "></script>
<script src="<c:url value="assets/js/core/popper.min.js"/>"></script>
<script src="<c:url value="assets/js/core/bootstrap.min.js"/>"></script>

<!-- Chart JS -->
<script src="<c:url value="assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"/>"></script>

<!-- jQuery Scrollbar -->
<script src="<c:url value="assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"/>"></script>
<!-- Kaiadmin JS -->
<script src="<c:url value="assets/js/kaiadmin.min.js"/>"></script>
<script src="<c:url value="assets/js/plugin/datatables/datatables.min.js"/>"></script>
<script src="<c:url value="assets/js/detail.js"/>"></script>

</body>
</html>

