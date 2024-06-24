$(document).ready(function () {
    $("#multi-filter-select").DataTable({
        initComplete: function () {
            this.api()
                .columns()
                .every(function () {
                    var column = this;
                    var select = $(
                        '<select class="form-select"><option value=""></option></select>'
                    )
                        .appendTo($(column.footer()).empty())
                        .on("change", function () {
                            var val = $.fn.dataTable.util.escapeRegex($(this).val());

                            column.search(val ? "^" + val + "$" : "", true, false).draw();
                        });

                    column
                        .data()
                        .unique()
                        .sort()
                        .each(function (d, j) {
                            select.append('<option value="' + d + '">' + d + "</option>");
                        });
                });
        },
    });

    $("#multi-filter-select tbody").on('click ', 'tr', function () {
        let check = $("#multi-filter-select").DataTable().row(this).data();
        let param = "?id=" + check[4];
        window.location.href = karyawanDetail + param;
    })

    $('<input>').attr({
        type: 'hidden',
        id: 'searchValue',
        name: 'searchValue'
    }).appendTo('form');

    $('#multi-filter-select_filter input').on('input', function () {
        $('#searchValue').val(this.value);
    });
});


