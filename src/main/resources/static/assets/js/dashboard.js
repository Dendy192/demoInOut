let previousData = null;
let intervalId;

function cardClicked(gateName) {
    const today = new Date();
    const yyyy = today.getFullYear();
    let mm = today.getMonth() + 1; // Months start at 0!
    let dd = today.getDate();

    if (dd < 10) dd = '0' + dd;
    if (mm < 10) mm = '0' + mm;

    let formattedToday = dd + '-' + mm + '-' + yyyy;
    let start = document.getElementById('input_from').value;
    let end = document.getElementById("input_to").value;

    if(start =="" || start == null ){
        start = formattedToday;
    }if(end == "" || end == null){
        end = formattedToday;
    }
    let urlParam = "?gateName="+gateName+"&startDate="+start+"&endDate="+end+"";
    console.log(gateName)
    window.location.href =detailUrl+urlParam;
}


const options = {
    responsive: true,
    maintainAspectRatio: false,
    legend: {
        minSize: 1,
        position: "bottom",
        labels: {
            fontColor: "rgb(154, 154, 154)",
            fontSize: 14,
            usePointStyle: true,
            padding: 20,
        },
    },
};

function generateCard(gateName, tapIn, tapOut) {
    var countCardData = '<div class="col">' +
        '    <div class="card card-round" onclick="cardClicked(\'' + gateName + '\')">' +
        '        <div class="card-header">' +
        '            <div class="card-head-row">' +
        '                <div class="card-title">' + gateName + '</div>' +
        '            </div>' +
        '        </div>' +
        '        <div class="card-body">' +
        '            <div class="row">' +
        '                <div class="col">' +
        '                    <div class="card card-stats card-success card-round">' +
        '                        <div class="card-body">' +
        '                            <div class="row">' +
        '                                <div class="col-5">' +
        '                                    <div class="icon-big text-center">' +
        '                                        <i class="fas fa-check-circle"></i>' +
        '                                    </div>' +
        '                                </div>' +
        '                                <div class="col-7 col-stats">' +
        '                                    <div class="numbers">' +
        '                                        <p class="card-category">Karyawan Masuk</p>' +
        '                                        <h4 class="card-title">' + tapIn + '</h4>' +
        '                                    </div>' +
        '                                </div>' +
        '                            </div>' +
        '                        </div>' +
        '                    </div>' +
        '                </div>' +
        '                <div class="col">' +
        '                    <div class="card card-stats card-danger card-round">' +
        '                        <div class="card-body">' +
        '                            <div class="row">' +
        '                                <div class="col-5">' +
        '                                    <div class="icon-big text-center">' +
        '                                        <i class="fas fa-times-circle"></i>' +
        '                                    </div>' +
        '                                </div>' +
        '                                <div class="col-7 col-stats">' +
        '                                    <div class="numbers">' +
        '                                        <p class="card-category">Karyawan Keluar</p>' +
        '                                        <h4 class="card-title">' + tapOut + '</h4>' +
        '                                    </div>' +
        '                                </div>' +
        '                            </div>' +
        '                        </div>' +
        '                    </div>' +
        '                </div>' +
        '            </div>' +
        '        </div>' +
        '    </div>' +
        '</div>';
    return countCardData;
}

function generateCart(gateName) {
    let myHtml = '<div class="col">' +
        '    <div class="card">' +
        '        <div class="card-body">' +
        '            <div class="chart-container">' +
        '                <canvas id="' + gateName + '" style="width: 50%; height: 50%"></canvas>' +
        '            </div>' +
        '        </div>' +
        '    </div>' +
        '</div>';
    return myHtml;
}

function setDataCart(gateName, tapIn, tapOut) {
    const data = {
        labels: ["Karyawan Masuk", "Karyawan Keluar"],
        datasets: [
            {
                label: gateName,
                data: [tapIn, tapOut],
                backgroundColor: ["#31ce36", "#f25961"],
                borderWidth: 0,
            },
        ],
    };
    return data;
}


rome(input_from, {
    dateValidator: rome.val.beforeEq(input_to),
    inputFormat: "DD-MM-YYYY",
    time: false,
});

rome(input_to, {
    dateValidator: rome.val.afterEq(input_from),
    inputFormat: "DD-MM-YYYY",
    time: false,
});
$(document).ready(function () {
    let intervalId;
    $('#backBtn').click(function () {
        window.location.href = dashboardUrl;
    });
    $('#searchBtn').click(function () {
        let start = document.getElementById('input_from').value;
        let end = document.getElementById("input_to").value;
        if (start == '' || end == '') {
            alert("Choose Date ")
        } else {
            stopAutoRefresh();
            $('#backBtn').removeAttr('hidden');
            let data1 = {
                start: start,
                end: end
            }
            // var encodedData = encodeURIComponent(JSON.stringify(data1));

            $.ajax({
                url: getDataByDate,
                data:data1,
                method: 'GET',
                contentType: 'application/json',
                success: function (data) {
                    let data1 = data.data;
                    let setDataCarts = [];
                    let gatePull = [];
                    let countCard = null;
                    let cartHtml = null;
                    let newData = [];
                    for (let i = 0; i < data1.length; i++) {
                        let gateName = data1[i].gateName;
                        gatePull.push(gateName);
                        const tapIn = data1[i].tapIn;
                        const tapOut = data1[i].tapOut;
                        let countCardData1 = generateCard(gateName, tapIn, tapOut);
                        if (countCard == null) {
                            countCard = countCardData1;
                        } else {
                            countCard += countCardData1;
                        }
                        let cartData = generateCart(gateName);
                        if (cartHtml == null) {
                            cartHtml = cartData;
                        } else {
                            cartHtml += cartData;
                        }
                        let setCart = setDataCart(gateName, tapIn, tapOut);

                        setDataCarts.push(setCart);
                        newData.push({
                            gateName: gateName,
                            tapIn: tapIn,
                            tapOut: tapOut
                        });
                    }
                    if (JSON.stringify(newData) !== JSON.stringify(previousData)) {
                        previousData = newData;
                        document.getElementById("cart").innerHTML = cartHtml;
                        document.getElementById("countCard").innerHTML = countCard;
                        for (let a = 0; a < setDataCarts.length; a++) {
                            let dataCart = setDataCarts[a];

                            let config = {
                                type: "pie",
                                data: dataCart,
                                options: options,
                            };

                            new Chart(document.getElementById(gatePull[a]).getContext("2d"), config);
                        }
                    }


                },
                error: function () {
                    alert("Error Fech Data");
                }
            });
        }

    });

    function fetchData() {
        $.ajax({
            url: getData,
            method: 'GET',
            success: function (data) {
                let data1 = data.data;
                let setDataCarts = [];
                let gatePull = [];
                let countCard = null;
                let cartHtml = null;
                let newData = [];
                for (let i = 0; i < data1.length; i++) {
                    let gateName = data1[i].gateName;
                    gatePull.push(gateName);
                    const tapIn = data1[i].tapIn;
                    const tapOut = data1[i].tapOut;
                    let countCardData1 = generateCard(gateName, tapIn, tapOut);
                    if (countCard == null) {
                        countCard = countCardData1;
                    } else {
                        countCard += countCardData1;
                    }
                    let cartData = generateCart(gateName);
                    if (cartHtml == null) {
                        cartHtml = cartData;
                    } else {
                        cartHtml += cartData;
                    }
                    let setCart = setDataCart(gateName, tapIn, tapOut);

                    setDataCarts.push(setCart);
                    newData.push({
                        gateName: gateName,
                        tapIn: tapIn,
                        tapOut: tapOut
                    });
                }
                if (JSON.stringify(newData) !== JSON.stringify(previousData)) {
                    previousData = newData;
                    document.getElementById("cart").innerHTML = cartHtml;
                    document.getElementById("countCard").innerHTML = countCard;
                    for (let a = 0; a < setDataCarts.length; a++) {
                        let dataCart = setDataCarts[a];

                        let config = {
                            type: "pie",
                            data: dataCart,
                            options: options,
                        };

                        new Chart(document.getElementById(gatePull[a]).getContext("2d"), config);
                    }
                }


            },
            error: function () {
                alert("Error Fech Data");
            }
        });
    }


    // Function to start auto-refresh
    function startAutoRefresh() {
        intervalId = setInterval(fetchData, 5000);
    }
    function stopAutoRefresh() {
        if (intervalId) {
            clearInterval(intervalId);
            intervalId = null; // Clear the interval ID
        }
    }

    // Function to stop auto-refresh


        fetchData();

        // Start auto-refresh
        startAutoRefresh();

    // Initial fetch


    // Event listener for the button
    // $('#stopButton').click(function() {
    // 	stopAutoRefresh();
    // 	alert('Auto-refresh stopped.');
    // });


});
