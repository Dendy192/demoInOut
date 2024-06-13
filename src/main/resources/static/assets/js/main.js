$(function () {
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
	const data1 = {
		labels: ["Karyawan Masuk", "Karyawan Keluar"],
		datasets: [
			{
				label: "Gate1",
				data: [1, 39],
				backgroundColor: ["#31ce36", "#f25961"],
				borderWidth: 0,
			},
		],
	};
	const options1 = {
		responsive: true,
		maintainAspectRatio: false,
		legend: {
			position: "bottom",
			labels: {
				fontColor: "rgb(154, 154, 154)",
				fontSize: 14,
				usePointStyle: true,
				padding: 20,
			},
		},
	};
	const data2 = {
		labels: ["Karyawan Masuk", "Karyawan Keluar"],
		datasets: [
			{
				label: "Gate2",
				data: [40, 0],
				backgroundColor: ["#31ce36", "#f25961"],
				borderWidth: 0,
			},
		],
	};
	const options2 = {
		responsive: true,
		maintainAspectRatio: false,
		legend: {
			position: "bottom",
			labels: {
				fontColor: "rgb(154, 154, 154)",
				fontSize: 14,
				usePointStyle: true,
				padding: 20,
			},
		},
	};
	const data3 = {
		labels: ["Karyawan Masuk", "Karyawan Keluar"],
		datasets: [
			{
				label: "Gate3",
				data: [40, 0],
				backgroundColor: ["#31ce36", "#f25961"],
				borderWidth: 0,
			},
		],
	};
	const options3 = {
		responsive: true,
		maintainAspectRatio: false,
		legend: {
			position: "bottom",
			labels: {
				fontColor: "rgb(154, 154, 154)",
				fontSize: 14,
				usePointStyle: true,
				padding: 20,
			},
		},
	};
	const config1 = {
		type: "pie",
		data: data1,
		options: options1,
	};
	const config2 = {
		type: "pie",
		data: data2,
		options: options2,
	};
	const config3 = {
		type: "pie",
		data: data3,
		options: options3,
	};

	new Chart(document.getElementById("gate1").getContext("2d"), config1);
	new Chart(document.getElementById("gate2").getContext("2d"), config2);
	new Chart(document.getElementById("gate3").getContext("2d"), config3);
});
