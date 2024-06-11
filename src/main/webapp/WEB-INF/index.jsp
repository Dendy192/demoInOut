<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body{
            margin: 30px;
        }
        .awalan {
            display: flex;
            justify-content: center;
        }

        .dalem {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="awalan">
        <div class="dalem">
            <h3>Tap in & Tap out</h3>
            <div class="row">
                <div class="col">
                    <div class="card mb-2">
                        <div class="card-header">
                            Tap In
                        </div>
                        <div class="card-body">
                            <h1 id="tapIn" class="justify-content-center align-items-center">
                                1
                            </h1>
                            <!-- <canvas id="myAreaChart" width="100%" height=""></canvas> -->
                        </div>
                    </div>
                </div>
                <div class="col">
                </div>
                <div class="col">
                    <div class="card mb-2">
                        <div class="card-header">
                            Tap Out
                        </div>
                        <div class="card-body">
                            <h1 id="tapOut" class="justify-content-center align-items-center">
                                1
                            </h1>
                            <!-- <canvas id="myAreaChart" width="100%" height=""></canvas> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    var getData = "<c:url value="/getData"/>";
    $(document).ready(function(){
        console.log(getData)
        function fetchData() {
            $.ajax({
                url: getData,
                method: 'GET',
                success: function(data) {
                    const tapIn = data.tapIn;
                    const tapOut = data.tapOut;
                    $('#tapIn').text(tapIn);
                    $('#tapOut').text(tapOut);
                }
            });
        }

        // Fetch data every 5 seconds
        setInterval(fetchData, 3000);

        // Initial fetch
        fetchData();
    });
</script>
</body>
</html>