$(document).ready( function () {
    var table = $('#defectsTable').DataTable({
        "sAjaxSource": "/getAllFault",
        "sAjaxDataProp": "",
        "stateSave": "true",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData":  "faultId"},
            { "mData": "email" },
            { "mData": "description" },
            { "mData": "department.departmentName" }
        ]
    })

    $('#defectsTable').on('click', 'tbody td', function(){
        window.location.href = '/defect/' + $(this).closest('tr').find('td:eq(0)').html();
    });
});